"use strict";
const common_vendor = require("../../common/vendor.js");
const store_data = require("../../store/data.js");
if (!Array) {
  const _component_uni_list_item = common_vendor.resolveComponent("uni-list-item");
  const _component_uni_list = common_vendor.resolveComponent("uni-list");
  (_component_uni_list_item + _component_uni_list)();
}
if (!Math) {
  (pageHead + searchresult + single + singer + playlist + album)();
}
const searchresult = () => "../../components/searchResult/searchResult.js";
const single = () => "../../components/singlePage/singlePage.js";
const singer = () => "../../components/singerPage/singerPage.js";
const playlist = () => "../../components/playlistPage/playlistPage.js";
const album = () => "../../components/albumPage/albumPage.js";
const pageHead = () => "../../components/pageHead/pageHead.js";
const _sfc_main = {
  __name: "search",
  setup(__props) {
    const searchRESStore = store_data.useSearchresStore();
    const searchRES = common_vendor.ref(searchRESStore.searchRES);
    const selectedButton = common_vendor.ref(1);
    const pageType = common_vendor.ref("single");
    common_vendor.onMounted(() => {
      searchRES.value = searchRESStore.searchRES;
    });
    function selectType(type) {
      pageType.value = type;
    }
    function changeColor(num) {
      selectedButton.value = num;
    }
    return (_ctx, _cache) => {
      return common_vendor.e({
        a: common_vendor.p({
          searchdata: searchRES.value
        }),
        b: selectedButton.value === 1 ? 1 : "",
        c: common_vendor.o(($event) => {
          selectType("single");
          changeColor(1);
        }),
        d: selectedButton.value === 2 ? 1 : "",
        e: common_vendor.o(($event) => {
          selectType("singer");
          changeColor(2);
        }),
        f: selectedButton.value === 3 ? 1 : "",
        g: common_vendor.o(($event) => {
          selectType("playlist");
          changeColor(3);
        }),
        h: selectedButton.value === 4 ? 1 : "",
        i: common_vendor.o(($event) => {
          selectType("album");
          changeColor(4);
        }),
        j: pageType.value === "single"
      }, pageType.value === "single" ? {} : {}, {
        k: pageType.value === "singer"
      }, pageType.value === "singer" ? {} : {}, {
        l: pageType.value === "playlist"
      }, pageType.value === "playlist" ? {} : {}, {
        m: pageType.value === "album"
      }, pageType.value === "album" ? {} : {});
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/search/search.vue"]]);
wx.createPage(MiniProgramPage);
