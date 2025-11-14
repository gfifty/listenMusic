<template>
	<image :src="currentTrack.cover" mode="aspectFill" class="musicCover"/>
  <view class="player-bar">
    <!-- 封面 -->
    <view class="cover">
    </view>
    <!-- 歌曲信息 -->
    <view class="info">
      <view class="title">{{ currentTrack.musicName || '未播放' }}</view>
      <view class="artist">{{ currentTrack.singerName || '未知歌手' }}</view>
      <view class="progress">
        <text>{{ formatTime(progress) }}</text>
        <slider
		 class="musicSlider"
          :value="progress"
          :max="duration"
		  @changing="onChanging"
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
        :key="track.musicId"
        class="playlist-item"
        :class="{ active: track.musicId === currentTrack.musicId }"
        @click="playTrack(track)"
      >
        {{ i + 1 }}. {{ track.musicName }} - {{ track.singerName }}
      </view>
    </view>
  </view>
</template>

<script setup>
import { computed,watch } from 'vue';
import { usePlayerStore } from '@/store/player';
import { onLoad } from "@dcloudio/uni-app";
import { storeToRefs } from 'pinia';


const player = usePlayerStore();
const {currentTrack,playing,progress,duration,lyrics,currentLyricIndex,showPlaylist,mode} =storeToRefs(player)
const playlist = computed(() => player.playlist);
const index = computed(()=>player.index);

// 页面加载时
onLoad(()=>{
	if (playlist.value?.length > 0) {
	player.setPlaylist(playlist.value,index.value);
	// player.playTrack(playlist[index.value]);
	}
})

// 按钮对应图标（用 emoji 替代）
const modeIcon = computed(() => {
	switch (mode.value) {
		case 'order' : return '🔁'
		case 'shuffle' : return '🔀'
		default: return '🔁'
	}
})

// 控制函数
const playTrack = (track) => player.playTrack(track)
const togglePlay = () => player.togglePlay()
const next = () => {player.next()}
const prev = () => player.prev()
const toggleMode = () => player.toggleMode()
const togglePlaylist = () => player.togglePlaylist() //切换播放列表
const onChanging =(e) =>{ 
	console.log('onChanging:' ,e.detail.value);
	progress.value = e.detail.value}
const onSeek = (e) => {
	console.log('onSeek：' ,e.detail.value);
	player.seek(e.detail.value)
	}

// 工具函数
const formatTime = (sec) => {
  if (!sec) return '00:00'
  const m = Math.floor(sec / 60).toString().padStart(2, '0')
  const s = Math.floor(sec % 60).toString().padStart(2, '0')
  return `${m}:${s}`
}
// 确保切歌后 UI 刷新
watch(currentTrack, (newTrack) => {
  console.log('🎵 当前播放歌曲:', newTrack)
})
</script>

<style scoped>
.musicCover{
	width: 500rpx;
	height: 500rpx;
	position: absolute;
	top: 100rpx;
	left: 110rpx;
	border-radius: 40rpx;
	line-height: 60rpx;
}
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
.musicSlider{
	width: 400rpx;
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
