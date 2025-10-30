let bgAudioManager = null;

export function getPlayer() {
  if (!bgAudioManager) {
    bgAudioManager = uni.getBackgroundAudioManager();

    // 可在这里绑定一次性的全局事件
    bgAudioManager.onPlay(() => console.log("播放开始"));
    bgAudioManager.onPause(() => console.log("播放暂停"));
    bgAudioManager.onEnded(() => console.log("播放结束"));
    bgAudioManager.onTimeUpdate(() => {
      // 这里可以在 store 中更新进度
    });
  }
  return bgAudioManager;
}
