"use strict";
const common_vendor = require("../../common/vendor.js");
const store_player = require("../../store/player.js");
const store_data = require("../../store/data.js");
if (!Array) {
  const _component_P = common_vendor.resolveComponent("P");
  const _component_uni_list_item = common_vendor.resolveComponent("uni-list-item");
  const _component_uni_list = common_vendor.resolveComponent("uni-list");
  (_component_P + _component_uni_list_item + _component_uni_list)();
}
if (!Math) {
  miniPlayer();
}
const miniPlayer = () => "../miniPlayer/miniPlayer1.js";
const _sfc_main = {
  __name: "singlePage",
  setup(__props) {
    const useSearchRESStore = store_data.useSearchresStore();
    const singleList = common_vendor.ref([]);
    const ls = common_vendor.ref(useSearchRESStore.searchRes);
    common_vendor.onMounted(() => {
      common_vendor.index.request({
        url: "http://localhost:8080/Music/searchMusic",
        method: "POST",
        data: {
          musicName: ls.value
        },
        success: (res) => {
          if (res.data.status == 200) {
            singleList.value = res.data.data;
          } else {
            console.log(res.data, "没找到");
          }
        },
        fail: (err) => {
          console.log(err);
        }
      });
    });
    function jumpTo(p, index) {
      const playerStore = store_player.usePlayerStore();
      playerStore.setPlaylist(singleList.value, index);
      common_vendor.index.navigateTo({
        url: `/pages/play/play`
      });
    }
    function likeThisMusic() {
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.f(singleList.value, (p, index, i0) => {
          return {
            a: common_vendor.t(index + 1),
            b: p.cover,
            c: common_vendor.t(p.musicName),
            d: common_vendor.o(likeThisMusic, index),
            e: "3a98a111-2-" + i0 + "," + ("3a98a111-1-" + i0),
            f: common_vendor.t(p.singerName),
            g: index,
            h: common_vendor.o(($event) => jumpTo(p, index), index),
            i: "3a98a111-1-" + i0 + ",3a98a111-0"
          };
        })
      };
    };
  }
};
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/components/singlePage/singlePage.vue"]]);
wx.createComponent(Component);
