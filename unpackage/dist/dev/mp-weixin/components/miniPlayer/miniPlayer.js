"use strict";
const common_vendor = require("../../common/vendor.js");
const store_player = require("../../store/player.js");
const _sfc_main = {
  __name: "miniPlayer",
  setup(__props) {
    const player = store_player.usePlayerStore();
    common_vendor.storeToRefs(player);
    const playButtonIcon = common_vendor.computed(() => {
      if (player.playing == null) {
        return "▶";
      }
      return player.playing ? "▮" : "▶";
    });
    return (_ctx, _cache) => {
      return {
        a: common_vendor.unref(player).currentTrack.cover,
        b: common_vendor.t(common_vendor.unref(player).currentTrack.name),
        c: common_vendor.t(common_vendor.unref(player).currentTrack.artist),
        d: common_vendor.o((...args) => common_vendor.unref(player).prev && common_vendor.unref(player).prev(...args)),
        e: common_vendor.t(common_vendor.unref(playButtonIcon)),
        f: common_vendor.o((...args) => common_vendor.unref(player).togglePlay && common_vendor.unref(player).togglePlay(...args)),
        g: common_vendor.o((...args) => common_vendor.unref(player).next && common_vendor.unref(player).next(...args))
      };
    };
  }
};
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/components/miniPlayer/miniPlayer.vue"]]);
wx.createComponent(Component);
