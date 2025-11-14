"use strict";
const common_vendor = require("../../common/vendor.js");
const store_player = require("../../store/player.js");
const _sfc_main = {
  __name: "miniPlayer1",
  setup(__props) {
    const player = store_player.usePlayerStore();
    const { currentTrack, playing, progress, duration, lyrics, currentLyricIndex, showPlaylist, mode } = common_vendor.storeToRefs(player);
    const prev = () => {
      player.prev();
    };
    const next = () => {
      player.next();
    };
    const togglePlay = () => {
      player.togglePlay();
    };
    function toPlayPage() {
      if (!player.currentTrack || !player.currentTrack.musicName) {
        common_vendor.index.showToast({
          title: "当前没有播放中的歌曲",
          icon: "none"
        });
        return;
      }
      common_vendor.index.navigateTo({
        url: `/pages/play/play`
      });
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.unref(currentTrack).cover,
        b: common_vendor.t(common_vendor.unref(currentTrack).musicName || "未播放"),
        c: common_vendor.t(common_vendor.unref(currentTrack).singerName || "未知歌手"),
        d: common_vendor.o(prev),
        e: common_vendor.t(common_vendor.unref(playing) ? "||" : "▶"),
        f: common_vendor.o(togglePlay),
        g: common_vendor.o(next),
        h: common_vendor.o(toPlayPage)
      };
    };
  }
};
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/components/miniPlayer/miniPlayer1.vue"]]);
wx.createComponent(Component);
