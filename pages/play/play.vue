<template>
  <view class="player-bar">
    <!-- 封面 -->
    <view class="cover">
      <image :src="currentTrack.cover" mode="aspectFill" />
    </view>
    <!-- 歌曲信息 -->
    <view class="info">
      <view class="title">{{ currentTrack.name || '未播放' }}</view>
      <view class="artist">{{ currentTrack.artist || '未知歌手' }}</view>
      <view class="progress">
        <text>{{ formatTime(progress) }}</text>
        <slider
          :value="progress"
          :max="duration"
          @change="onSeek"
        />
        <text>{{ formatTime(duration) }}</text>
      </view>
    </view>

    <!-- 控制按钮 -->
    <view class="controls">
      <button class="btn" @click="prev">⏮</button>
      <button class="btn" @click="togglePlay">
        {{ playing ? '⏸' : '▶️' }}
      </button>
      <button class="btn" @click="next">⏭</button>
      <button class="btn" @click="toggleMode">
        {{ modeIcon }}
      </button>
      <button class="btn" @click="togglePlaylist">📃</button>
    </view>

    <!-- 歌词 -->
    <scroll-view class="lyrics" scroll-y>
      <view
        v-for="(line, i) in lyrics"
        :key="i"
        :class="['lyric-line', { active: i === currentLyricIndex }]"
      >
        {{ line.text }}
      </view>
    </scroll-view>

    <!-- 播放列表弹窗 -->
    <view v-if="showPlaylist" class="playlist">
      <view
        v-for="(track, i) in playlist"
        :key="track.id"
        class="playlist-item"
        :class="{ active: track.id === currentTrack.id }"
        @click="playTrack(track)"
      >
        {{ i + 1 }}. {{ track.name }} - {{ track.artist }}
      </view>
    </view>
  </view>
</template>

<script setup>
import { compute,computed,watch } from 'vue';
import { usePlayerStore } from '@/store/player';
import { onLoad } from "@dcloudio/uni-app";

onLoad(()=>{
	const player = usePlayerStore();
	
	const playlist = computed(() => player.playlist);
	const index = computed(()=>player.index);
	
	player.setPlaylist(playlist,index);
	player.playTrack(playlist[index]);
})

// 播放状态
const currentTrack = computed(() => player.currentTrack || {})
const playing = computed(() => player.playing)
const progress = computed(() => player.progress)
const duration = computed(() => player.duration)
const lyrics = computed(() => player.lyrics)
const currentLyricIndex = computed(() => player.currentLyricIndex)
const showPlaylist = computed(() => player.showPlaylist)
const mode = computed(() => player.mode)



// 按钮对应图标（用 emoji 替代）
const modeIcon = computed(() => {
  if (mode.value === 'order') return '🔁'
  if (mode.value === 'shuffle') return '🔀'
  if (mode.value === 'single') return '🔂'
  return '🔁'
})

// 控制函数
const playTrack = (track) => player.playTrack(track)
const togglePlay = () => player.togglePlay()
const next = () => player.next()
const prev = () => player.prev()
const toggleMode = () => player.toggleMode()
const togglePlaylist = () => player.showPlaylist = !player.showPlaylist
const onSeek = (e) => player.seek(e.detail.value)

// 工具函数
const formatTime = (sec) => {
  if (!sec) return '00:00'
  const m = Math.floor(sec / 60).toString().padStart(2, '0')
  const s = Math.floor(sec % 60).toString().padStart(2, '0')
  return `${m}:${s}`
}
</script>

<style scoped>
.player-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: #1e1e1e;
  color: #fff;
  display: flex;
  flex-direction: column;
  padding: 10px;
}

.cover image {
  width: 60px;
  height: 60px;
  border-radius: 8px;
}

.info {
  flex: 1;
  padding: 0 10px;
}

.title {
  font-size: 16px;
  font-weight: bold;
}

.artist {
  font-size: 14px;
  color: #aaa;
}

.progress {
  display: flex;
  align-items: center;
}

.progress text {
  font-size: 12px;
  width: 40px;
  text-align: center;
}

.controls {
  display: flex;
  justify-content: space-around;
  margin-top: 5px;
}

.btn {
  font-size: 20px;
  background: transparent;
  color: #fff;
}

.lyrics {
  max-height: 120px;
  margin-top: 5px;
  font-size: 14px;
  color: #aaa;
}

.lyric-line {
  text-align: center;
  padding: 2px 0;
}

.lyric-line.active {
  color: #fff;
  font-weight: bold;
}

.playlist {
  position: fixed;
  bottom: 70px;
  left: 0;
  right: 0;
  max-height: 300px;
  background: #222;
  color: #fff;
  overflow-y: auto;
  border-top: 1px solid #444;
}

.playlist-item {
  padding: 10px;
  border-bottom: 1px solid #333;
}

.playlist-item.active {
  background: #333;
  color: #1db954;
}
</style>
