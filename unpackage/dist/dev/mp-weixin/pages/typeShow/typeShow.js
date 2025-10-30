"use strict";
const common_vendor = require("../../common/vendor.js");
if (!Array) {
  const _component_uni_list_item = common_vendor.resolveComponent("uni-list-item");
  const _component_uni_list = common_vendor.resolveComponent("uni-list");
  (_component_uni_list_item + _component_uni_list)();
}
const _sfc_main = {
  __name: "typeShow",
  setup(__props) {
    const pageType = common_vendor.ref("");
    const pageMusic = common_vendor.ref([]);
    common_vendor.onLoad((option) => {
      pageType.value = option.type;
      common_vendor.index.request({
        url: "http://localhost:8080/Music/getMusicType",
        method: "POST",
        data: {
          musicType: pageType.value
        },
        success: (res) => {
          if (res.data.status == 200) {
            pageMusic.value = res.data.data;
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
      common_vendor.index.navigateTo({
        url: `/pages/play/play?singlist=${encodeURIComponent(JSON.stringify(pageMusic.value))}&music=${encodeURIComponent(JSON.stringify(p))}&index=${index}`
      });
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.t(pageType.value),
        b: common_vendor.f(pageMusic.value, (p, index, i0) => {
          return {
            a: common_vendor.t(index + 1),
            b: common_vendor.t(p.musicName),
            c: common_vendor.t(p.singerName),
            d: common_vendor.o(($event) => jumpTo(p, index), index),
            e: index,
            f: "2d9a4419-1-" + i0 + ",2d9a4419-0"
          };
        })
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/typeShow/typeShow.vue"]]);
wx.createPage(MiniProgramPage);
