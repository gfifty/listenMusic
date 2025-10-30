"use strict";
const common_vendor = require("../../common/vendor.js");
const store_data = require("../../store/data.js");
const store_logindata = require("../../store/logindata.js");
const _sfc_main = {
  __name: "pageHead",
  setup(__props) {
    common_vendor.ref("");
    const searchRESStore = store_data.useSearchresStore();
    const searchRES = common_vendor.ref("");
    const userResStore = store_logindata.useUserResStore();
    common_vendor.ref("");
    const isLogined = common_vendor.ref(userResStore.isLogined);
    console.log(isLogined.value);
    common_vendor.onShow(() => {
      console.log("页面显示了");
      isLogined.value = userResStore.isLogined;
      console.log("用户登录状态：", isLogined.value);
    });
    const toSearch = () => {
      if (searchRES.value.trim()) {
        searchRESStore.setSearchRES(searchRES.value);
        common_vendor.index.navigateTo({
          url: `/pages/search/search`
        });
        console.log(searchRES);
      } else {
        common_vendor.index.showToast({
          title: "请输入搜索内容",
          icon: "none"
        });
      }
    };
    return (_ctx, _cache) => {
      return {
        a: common_vendor.o(toSearch),
        b: searchRES.value,
        c: common_vendor.o(($event) => searchRES.value = $event.detail.value)
      };
    };
  }
};
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/components/pageHead/pageHead.vue"]]);
wx.createComponent(Component);
