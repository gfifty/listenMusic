// stores/player.js
import { defineStore } from 'pinia'

export const usePlayerStore = defineStore('player', {
  state: () => ({
    backgroundAudioManager: null,
	index: 0,
    currentTrack: null,   // 当前播放的歌曲对象
    playlist: [],         // 播放列表
    playing: false,       // 播放状态
    progress: 0,          // 当前播放进度 (秒)
    duration: 0,          // 总时长 (秒)
    mode: 'order',         // 播放模式 order | shuffle
    showLyrics: false,    // 是否显示歌词
	showPlaylist: false,  // 是否显示播放列表
    lyrics: [],           // 当前歌词
    currentLyricIndex: 0, // 当前歌词行
  }),

  actions: {
    initAudioManager() {
      if (!this.backgroundAudioManager) {
        this.backgroundAudioManager = uni.getBackgroundAudioManager()

        // 播放事件监听
        this.backgroundAudioManager.onPlay(() => {
          this.playing = true
        })

        this.backgroundAudioManager.onPause(() => {
          this.playing = false
        })

        this.backgroundAudioManager.onStop(() => {
          this.playing = false
        })

        this.backgroundAudioManager.onEnded(() => {
          this.next()
        })
		// 监听时间更新事件，更新播放进度和歌词
        this.backgroundAudioManager.onTimeUpdate(() => {
          this.progress = this.backgroundAudioManager.currentTime
          this.duration = this.backgroundAudioManager.duration
          this.updateLyricIndex()// 更新当前歌词索引
        })
      }
    },
/**
 * 播放指定歌曲
 */
    playTrack(track) {
      this.initAudioManager()
      this.currentTrack = track // 设置当前歌曲 
      this.backgroundAudioManager.src = track.musicUrl || track.url // 音频源地址
      this.backgroundAudioManager.title = track.musicName || track.name
      this.backgroundAudioManager.singer = track.singerName || track.artist
      this.backgroundAudioManager.coverImgUrl = track.cover
      this.playing = true
    },
	
/**
 * 继续播放
 */
    play() {
      if (this.backgroundAudioManager) {
        this.backgroundAudioManager.play()
      }
    },
/**
 * 暂停播放
 */
    pause() {
      if (this.backgroundAudioManager) {
        this.backgroundAudioManager.pause()
      }
    },
/**
 * 切换播放/暂停状态
 */
    togglePlay() {
      this.playing ? this.pause() : this.play()
    },
/**
 * 播放下一首歌曲
 * 根据当前播放模式决定下一首歌曲
 */
    next() {
      if (this.playlist.length === 0) return
	  
	  // 查找当前歌曲在播放列表中的索引
      const idx = this.playlist.findIndex(t => String(t.musicId) === String(this.currentTrack.musicId))
      let nextIdx = idx + 1
	  
	  // ====== 播放模式逻辑 ======
	  // 根据播放模式计算下一首索引
      if (this.mode === 'shuffle') {
		  
		  // 随机模式：随机选择一首
		if(this.playlist.length === 1){
			nextIdx = 0
		}else {
			do {
				nextIdx = Math.floor(Math.random() * this.playlist.length)
			} while (nextIdx === idx)
		}

      }  else if (nextIdx >= this.playlist.length) {
		  
		  // 循环模式：播放到最后一首后回到第一首
        nextIdx = 0
      }
	  
	  // 播放下一首歌曲
      this.playTrack(this.playlist[nextIdx])
    },
/**
 * 播放上一首歌曲
 */
    prev() {
      if (this.playlist.length === 0) return
      const idx = this.playlist.findIndex(t => t.musicId === this.currentTrack.musicId)
      let prevIdx = idx - 1
	  // 如果已经是第一首，则跳到最后一首
      if (prevIdx < 0) prevIdx = this.playlist.length - 1
      this.playTrack(this.playlist[prevIdx])
	  this.index =prevIdx
    },
/**
 * 跳转到指定播放位置
 */
    seek(position) {
      if (this.backgroundAudioManager) {
        this.backgroundAudioManager.seek(position)
        this.progress = position
      }
    },
/**
 * 设置播放列表并开始播放
 */
    setPlaylist(list, startIndex) {
      this.playlist = list
	  this.index =startIndex
      this.playTrack(list[startIndex])
	  console.log("在此展示",list[startIndex]);
    },
/**
 * 加载歌词文本并解析
 */
    loadLyrics(lyricText) {
      this.lyrics = this.parseLyrics(lyricText)
      this.currentLyricIndex = 0
    },
/**
 * 解析歌词文本为结构化数据
 */
    parseLyrics(text) {
      const lines = text.split('\n')
      return lines.map(line => {
        const match = line.match(/\[(\d+):(\d+).(\d+)\](.*)/)
        if (match) {
          const min = parseInt(match[1])
          const sec = parseInt(match[2])
          const ms = parseInt(match[3])
          return { time: min * 60 + sec + ms / 100, text: match[4] }
        }
        return null
      }).filter(Boolean)
    },
/**
 * 根据当前播放进度更新歌词索引
 * 找到当前应该显示的歌词行
 */
    updateLyricIndex() {
      if (!this.lyrics.length) return
      const current = this.progress
      for (let i = 0; i < this.lyrics.length; i++) {
        if (current < this.lyrics[i].time) {
          this.currentLyricIndex = Math.max(0, i - 1)
          break
        }
      }
    },
	/**
	 * 切换播放列表显示状态 
	*/
	togglePlaylist() {
	      this.showPlaylist = !this.showPlaylist
	},
	/** 
	 * 切换播放模式 
	 */
	toggleMode() {
	    const modes = ['order', 'shuffle']
	    const i = modes.indexOf(this.mode)
	    this.mode = modes[(i + 1) % modes.length]
	},
  }
})
