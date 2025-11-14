"use strict";
const common_vendor = require("../../../common/vendor.js");
const store_logindata = require("../../../store/logindata.js");
const _sfc_main = {
  __name: "mine1",
  setup(__props) {
    common_vendor.ref({
      name: "音乐爱好者",
      desc: "热爱生活，热爱音乐",
      avatar: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?auto=format&fit=crop&w=200&h=200&q=80"
    });
    const playlists = common_vendor.ref([
      {
        name: "旅行歌单",
        count: 32,
        cover: "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?auto=format&fit=crop&w=200&h=200&q=80"
      }
    ]);
    const UserResStore = store_logindata.useUserResStore();
    common_vendor.ref(1);
    common_vendor.ref("personmusic");
    common_vendor.onTabItemTap((e) => {
      if (!UserResStore.isLogined) {
        common_vendor.index.redirectTo({
          url: "/pages/login/login"
        });
        common_vendor.index.showToast({
          title: "请先进行登录",
          icon: "none"
        });
      }
    });
    return (_ctx, _cache) => {
      return {
        a: common_vendor.unref(UserResStore).userRes.userHead,
        b: common_vendor.t(common_vendor.unref(UserResStore).userRes.username),
        c: common_vendor.t(playlists.value.length),
        d: common_vendor.f(playlists.value, (playlist, index, i0) => {
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
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__scopeId", "data-v-29bb7c73"], ["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/mine/mine1/mine1.vue"]]);
wx.createPage(MiniProgramPage);
