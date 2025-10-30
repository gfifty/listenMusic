"use strict";
const common_vendor = require("../../common/vendor.js");
const store_data = require("../../store/data.js");
const _sfc_main = {
  __name: "singerPage",
  setup(__props) {
    const useSearchRESStore = store_data.useSearchresStore();
    const ls = common_vendor.ref(useSearchRESStore.searchRes);
    const searchResults = common_vendor.ref([]);
    common_vendor.onMounted(() => {
      console.log(ls);
      common_vendor.index.request({
        url: "http://localhost:8080/singer/searchSinger",
        method: "POST",
        data: {
          singerName: ls.value
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
    return (_ctx, _cache) => {
      return {
        a: common_vendor.f(searchResults.value, (item, index, i0) => {
          return {
            a: item.singerHead,
            b: common_vendor.t(item.singerName),
            c: index
          };
        })
      };
    };
  }
};
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/components/singerPage/singerPage.vue"]]);
wx.createComponent(Component);
