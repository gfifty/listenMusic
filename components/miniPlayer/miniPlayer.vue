<template>
  <!-- 播放控制条 -->
      <div  class="player-bar" >
          <div class="song-info">
              <img :src="player.currentTrack.cover" class="song-thumb" alt="当前播放">
              <div class="song-details">
                  <div class="song-name">{{player.currentTrack.name}}</div>
                  <div class="song-artist">{{player.currentTrack.artist}}</div>
              </div>
          </div>
          <div class="player-controls">
              <i class="controls-i" @click="player.prev">⋘</i>
              <div class="play-btn">
                 <i @click="player.togglePlay" class=" controls-stop">
                         {{ playButtonIcon }}
                       </i>
              </div>
              <i class="controls-i" @click="player.next">⋙</i>
          </div>
      </div>
</template>

<script setup>
import { usePlayerStore } from "@/store/player";	
import { computed } from "vue";
import { storeToRefs } from 'pinia'

const player = usePlayerStore();
const { progress, duration } = storeToRefs(player);
const playButtonIcon = computed(()=>{
	if(player.playing == null){
		return '▶';
	}
	return player.playing ? '▮' : '▶'
})

const onSeek = (e) => {
  player.seek(Number(e.detail.value))
}

function toPlayPage() {
  uni.navigateTo({
    url: `/pages/play/play?music=${encodeURIComponent(
      JSON.stringify(player.currentSong)
    )}`,
  });
}
function togglePlay(){
	player.to
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
            font-size: 20px;
            color: #9cdf7e;
            cursor: pointer;
            transition: all 0.2s ease;
        }
		.controls-stop{
			font-size: 20px;
			color: #5e3a78;
			cursor: pointer;
			transition: all 0.2s ease;
		}
        
        .player-controls .controls-i:hover {
            color: #5e3a78;
            transform: scale(1.2);
        }
        
        .play-btn {
            background: #9cdf7e;
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
