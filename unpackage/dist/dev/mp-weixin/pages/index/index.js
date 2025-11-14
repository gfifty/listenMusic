"use strict";
const common_vendor = require("../../common/vendor.js");
const hook_req = require("../../hook/req.js");
const store_player = require("../../store/player.js");
const store_data = require("../../store/data.js");
const store_logindata = require("../../store/logindata.js");
if (!Math) {
  (pageHead + miniPlayer)();
}
const miniPlayer = () => "../../components/miniPlayer/miniPlayer1.js";
const pageHead = () => "../../components/pageHead/pageHead.js";
const _sfc_main = {
  __name: "index",
  setup(__props) {
    const searchRESStore = store_data.useSearchresStore();
    store_logindata.useUserResStore();
    searchRESStore.searchRes;
    searchRESStore.setSearchRES;
    const originalList = common_vendor.ref([]);
    common_vendor.inject("$root");
    const albumList = common_vendor.ref([]);
    const categories = common_vendor.ref([
      {
        id: 1,
        name: "推荐",
        icon: "fas fa-star"
      },
      {
        id: 2,
        name: "排行榜",
        icon: "fas fa-list-ol"
      },
      {
        id: 3,
        name: "歌手",
        icon: "fas fa-user"
      },
      {
        id: 4,
        name: "分类",
        icon: "fas fa-th-large"
      }
    ]);
    hook_req.req("http://localhost:8080/singlist/getHotList").then((result) => {
      if (result.data.status == 200) {
        albumList.value = result.data.data;
      }
    });
    function openSinglist(singlistId) {
      common_vendor.index.navigateTo({
        url: `/pages/singlistDetail/singlistDetail?singlistId=${singlistId}`
      });
    }
    hook_req.req("http://localhost:8080/Music/hotMusic").then((result) => {
      if (result.data.status == 200) {
        console.log(result.data.data);
        originalList.value = result.data.data;
      }
    });
    function jumpTo(p, index) {
      const playerStore = store_player.usePlayerStore();
      console.log(originalList.value);
      playerStore.setPlaylist(originalList.value, index);
      common_vendor.index.navigateTo({
        url: `/pages/play/play`
      });
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.f(categories.value, (cat, k0, i0) => {
          return {
            a: common_vendor.n(cat.icon + " icon-category"),
            b: common_vendor.t(cat.name),
            c: cat.id
          };
        }),
        b: common_vendor.f(albumList.value, (p, index, i0) => {
          return {
            a: p.singlistHead,
            b: common_vendor.t(p.singlistName),
            c: common_vendor.t(p.singlistTime.slice(0, 10)),
            d: common_vendor.t(p.singlistLike),
            e: index,
            f: common_vendor.o(($event) => openSinglist(p.singlistId), index)
          };
        }),
        c: common_vendor.f(originalList.value, (p, index, i0) => {
          return {
            a: p.cover,
            b: common_vendor.t(p.musicName),
            c: common_vendor.t(p.singerName),
            d: common_vendor.t(p.musicDuration),
            e: index,
            f: common_vendor.o(($event) => jumpTo(p, index), index)
          };
        })
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__scopeId", "data-v-1cf27b2a"], ["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/index/index.vue"]]);
wx.createPage(MiniProgramPage);
