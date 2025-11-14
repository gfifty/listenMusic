"use strict";
const common_vendor = require("../../common/vendor.js");
const store_data = require("../../store/data.js");
if (!Math) {
  miniPlayer();
}
const miniPlayer = () => "../miniPlayer/miniPlayer1.js";
const _sfc_main = {
  __name: "albumPage",
  setup(__props) {
    const useSearchRESStore = store_data.useSearchresStore();
    const ls = common_vendor.ref(useSearchRESStore.searchRes);
    const searchResults = common_vendor.ref([]);
    common_vendor.onMounted(() => {
      console.log(ls);
      common_vendor.index.request({
        url: "http://localhost:8080/album/searchAlbum",
        method: "POST",
        data: {
          albumName: ls.value
        },
        success: (res) => {
          if (res.data.status == 200) {
            searchResults.value = res.data.data;
            console.log(searchResults.value);
          } else {
            console.log("没找到");
          }
        },
        default: (res) => {
          console.log("失败了");
        }
      });
    });
    function openSinglist(albumId) {
      common_vendor.index.navigateTo({
        url: `/pages/albumDetail/albumDetail?albumId=${albumId}`
      });
      console.log(1);
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.f(searchResults.value, (p, index, i0) => {
          return {
            a: p.albumHead,
            b: common_vendor.t(p.albumName),
            c: common_vendor.t(p.albumNum),
            d: index,
            e: common_vendor.o(($event) => openSinglist(p.albumId), index)
          };
        })
      };
    };
  }
};
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/components/albumPage/albumPage.vue"]]);
wx.createComponent(Component);
