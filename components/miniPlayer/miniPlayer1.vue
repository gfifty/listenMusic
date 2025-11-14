<template>
  <!-- 播放控制条 -->
      <view class="player-bar" @click="toPlayPage">
          <view class="song-info">
              <img :src="currentTrack.cover" class="song-thumb" alt="当前播放">
              <view class="song-details">
                  <view class="song-name">{{ currentTrack.musicName || '未播放' }}</view>
                  <view class="song-artist">{{ currentTrack.singerName || '未知歌手' }}</view>
              </view>
          </view>
          <view class="player-controls">
              <i class="controls-i" @click.stop="prev">⋘</i>
              <view class="play-btn">
                  <i class="controls-i" @click.stop="togglePlay">{{ playing ? '||' : '▶' }}</i>
              </view>
              <i class="controls-i" @click.stop="next">⋙</i>
          </view>
      </view>
</template>

<script setup>
import { usePlayerStore } from "@/store/player";
import { onMounted } from "vue";
import { storeToRefs } from 'pinia';


const player = usePlayerStore();
const {currentTrack,playing,progress,duration,lyrics,currentLyricIndex,showPlaylist,mode} =storeToRefs(player)

const prev = () =>{player.prev()}
const next = () =>{player.next()}
const togglePlay = () =>{player.togglePlay()}


function toPlayPage() {
	if (!player.currentTrack || !player.currentTrack.musicName) {
	    uni.showToast({
	      title: '当前没有播放中的歌曲',
	      icon: 'none'
	    })
	    return
	  }
	uni.navigateTo({
		        url: `/pages/play/play`,
		    });
}
</script>

<style>

.player-bar {
            position: fixed;
            bottom: 0;
            left: 0;
            right: 0;
            background: rgba(255, 255, 255, 0.9);
            padding: 12px 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            backdrop-filter: blur(20px);
            border-top: 1px solid #ffcce6;
            box-shadow: 0 -4px 15px rgba(158, 223, 126, 0.3);
        }
        
        .song-info {
            display: flex;
            align-items: center;
            gap: 12px;
        }
        
        .song-thumb {
            width: 40px;
            height: 40px;
            border-radius: 8px;
            object-fit: cover;
        }
        
        .song-details {
            display: flex;
            flex-direction: column;
        }
        
        .song-name {
            font-weight: 600;
            font-size: 14px;
            color: #5e3a78;
        }
        
        .song-artist {
            font-size: 12px;
            color: #bd98ce;
        }
        
        .player-controls {
            display: flex;
            gap: 15px;
            align-items: center;
        }
        
        .player-controls .controls-i {
            font-size: 18px;
            color: #618a4e;
            cursor: pointer;
            transition: all 0.2s ease;
        }
        
        .player-controls .controls-i:hover {
            color: #5e3a78;
            transform: scale(1.2);
        }
        
        .play-btn {
            background: #9cdf7e;
			padding-left: 5rpx;
            width: 36px;
            height: 36px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            box-shadow: 0 4px 10px rgba(156, 223, 126, 0.5);
        }
</style>
