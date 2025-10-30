"use strict";
const common_vendor = require("../../common/vendor.js");
const store_data = require("../../store/data.js");
const _sfc_main = {
  __name: "searchResult",
  setup(__props) {
    const searchRESStore = store_data.useSearchresStore();
    const searchRES = common_vendor.ref("");
    searchRES.value = searchRESStore.searchRes;
    return (_ctx, _cache) => {
      return common_vendor.e({
        a: common_vendor.t(searchRES.value),
        b: searchRES.value
      }, searchRES.value ? {} : {});
    };
  }
};
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/components/searchResult/searchResult.vue"]]);
wx.createComponent(Component);
