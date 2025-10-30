"use strict";
const common_vendor = require("../../common/vendor.js");
const store_data = require("../../store/data.js");
if (!Array) {
  const _component_uni_list_item = common_vendor.resolveComponent("uni-list-item");
  const _component_uni_list = common_vendor.resolveComponent("uni-list");
  (_component_uni_list_item + _component_uni_list)();
}
const _sfc_main = {
  __name: "playlistPage",
  setup(__props) {
    const useSearchRESStore = store_data.useSearchresStore();
    const singleList = common_vendor.ref([]);
    const ls = common_vendor.ref(useSearchRESStore.searchRes);
    common_vendor.onMounted(() => {
      common_vendor.index.request({
        url: "http://localhost:8080/singlist/secrchSinglist",
        method: "POST",
        data: {
          singlistName: ls.value
        },
        success: (res) => {
          if (res.data.status == 200) {
            singleList.value = res.data.data;
          } else {
            console.log("没找到");
          }
        },
        default: (res) => {
          console.log("失败了");
        }
      });
    });
    function openSinglist(singlistId) {
      common_vendor.index.navigateTo({
        url: `/pages/singlistDetail/singlistDetail?singlistId=${singlistId}`
      });
      console.log(1);
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.f(singleList.value, (p, index, i0) => {
          return {
            a: p.singlistHead,
            b: common_vendor.t(p.singlistName),
            c: index,
            d: common_vendor.o(($event) => openSinglist(p.singlistId), index),
            e: "53a28651-1-" + i0 + ",53a28651-0"
          };
        })
      };
    };
  }
};
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/components/playlistPage/playlistPage.vue"]]);
wx.createComponent(Component);
