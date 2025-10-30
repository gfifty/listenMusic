<template>
  <!-- 播放控制条 -->
      <div class="player-bar">
          <div class="song-info">
              <img src="https://picsum.photos/id/65/300/300" class="song-thumb" alt="当前播放">
              <div class="song-details">
                  <div class="song-name">夏日微风</div>
                  <div class="song-artist">周杰伦</div>
              </div>
          </div>
          <div class="player-controls">
              <i class="fas fa-step-backward controls-i"></i>
              <div class="play-btn">
                  <i class="fas fa-play controls-i"></i>
              </div>
              <i class="fas fa-step-forward controls-i"></i>
          </div>
      </div>
</template>

<script setup>
import { usePlayerStore } from "@/store/player";
import { onMounted } from "vue";

const player = usePlayerStore();

onMounted(() => {
  player.initListeners();
});

function toPlayPage() {
  uni.navigateTo({
    url: `/pages/play/play?music=${encodeURIComponent(
      JSON.stringify(player.currentSong)
    )}`,
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
            color: #9cdf7e;
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
