<template>
  <view>
    <image :src="song.cover" mode="" class="cover"></image>
    <text class="name">{{ song.musicName }}</text>
    <view class="lovetab">
      <text class="singer">{{ song.singerName }}</text>
      <text class="love">❤</text>
    </view>

    <!-- 进度条控制区域 -->
    <view class="ProgressArea">
      <text class="startTimeText">{{ currentTimeText }}</text>
      <slider
        class="Progress"
        @change="handleSliderChange"
        :value="sliderIndex"
        :max="maxSliderIndex"
        activeColor="#ea588e"
        backgroundColor="#b9c2c5"
        block-color="#fff"
        block-size="12"
      />
      <text class="endTimeText">{{ totalTimeText }}</text>
    </view>

    <view class="pauseTab">
      <image src="/static/组件/上一项.png" class="tab" @click="handlePrev"></image>
      <image src="/static/组件/暂停.png" class="tab" v-if="isplay" @click="onPauseAudio"></image>
      <image src="/static/组件/开始.png" class="tab" v-else @click="onPlayAudio"></image>
      <image src="/static/组件/下一项.png" class="tab" @click="handleNext"></image>
    </view>
  </view>
</template>

<script setup>
import { ref } from "vue";
import moment from "moment";
import { onLoad, onUnload } from "@dcloudio/uni-app";
import { getPlayer } from "@/utils/player";

const song = ref({});
const audioList = ref([]);
const index = ref(0);

const sliderIndex = ref(0);
const maxSliderIndex = ref(100);
const currentTimeText = ref("00:00");
const totalTimeText = ref("00:00");
const isplay = ref(true);

const player = getPlayer();

// 页面加载时初始化
onLoad((options) => {
	console.log(options);
  audioList.value = JSON.parse(decodeURIComponent(options.singlist));
  song.value = JSON.parse(decodeURIComponent(options.music));
  index.value = parseInt(options.index);
  

  playCurrentSong(song.value);
});

// 播放当前歌曲
function playCurrentSong(music) {
  player.title = music.musicName;
  player.singer = music.singerName;
  player.coverImgUrl = music.cover;
  player.src = music.musicUrl; // 设置 src 会自动播放
  isplay.value = true;
}

// 上一首
function handlePrev() {
  if (index.value > 0) {
    index.value--;
    song.value = audioList.value[index.value];
    playCurrentSong(song.value);
  }
}

// 下一首
function handleNext() {
  if (index.value < audioList.value.length - 1) {
    index.value++;
    song.value = audioList.value[index.value];
    playCurrentSong(song.value);
  }
}

// 暂停
function onPauseAudio() {
  player.pause();
  isplay.value = false;
}

// 播放
function onPlayAudio() {
  player.play();
  isplay.value = true;
}

// 进度条拖动
function handleSliderChange(e) {
  const value = e.detail.value;
  player.seek(value);
  sliderIndex.value = value;
  currentTimeText.value = moment(value * 1000).format("mm:ss");
}

// ===== 绑定全局播放器事件 =====
player.onTimeUpdate(() => {
  sliderIndex.value = player.currentTime;
  currentTimeText.value = moment(player.currentTime * 1000).format("mm:ss");
  if (player.duration) {
    totalTimeText.value = moment(player.duration * 1000).format("mm:ss");
    maxSliderIndex.value = player.duration;
  }
});

player.onEnded(() => {
  handleNext();
});
</script>

<style>
body {
  background-color: #eeeee1;
}
.cover {
  margin: 0rpx 60rpx 0rpx 60rpx;
  width: 650rpx;
  height: 650rpx;
}
.name {
  display: block;
  font-size: 70rpx;
  margin-left: 60rpx;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.singer {
  width: 270rpx;
  margin-right: 200rpx;
  font-size: larger;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.lovetab {
  margin-top: 10rpx;
}
.Progress {
  display: inline-block;
  width: 430rpx;
}
.startTimeText {
  margin-left: 30rpx;
}
.ProgressArea {
  margin-top: 30rpx;
}
.pauseTab {
  font-size: 80rpx;
  margin-top: 70rpx;
  text-align: center;
}
.tab {
  display: inline-block;
  text-align: center;
  width: 90rpx;
  height: 90rpx;
  line-height: 95rpx;
  padding: 15rpx;
  border-radius: 700rpx;
  margin: 0rpx 20rpx 0 30rpx;
}
</style>
