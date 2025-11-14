"use strict";
const common_vendor = require("../../../common/vendor.js");
const _sfc_main = {};
function _sfc_render(_ctx, _cache) {
  return {
    a: _ctx.UserResStore.userRes.userHead,
    b: common_vendor.t(_ctx.UserResStore.userRes.username),
    c: common_vendor.t(_ctx.playlists.length),
    d: common_vendor.f(_ctx.playlists, (playlist, index, i0) => {
      return {
        a: playlist.cover,
        b: common_vendor.t(playlist.name),
        c: common_vendor.t(playlist.count),
        d: index,
        e: common_vendor.o(($event) => _ctx.openPlaylist(playlist.name), index)
      };
    }),
    e: common_vendor.o((...args) => _ctx.createMusic && _ctx.createMusic(...args))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-1645046e"], ["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/components/personMusic/personmusic1/personmusic1.vue"]]);
wx.createPage(MiniProgramPage);
