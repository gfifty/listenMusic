"use strict";
const common_vendor = require("../../common/vendor.js");
const store_logindata = require("../../store/logindata.js");
if (!Array) {
  const _component_uni_list_item = common_vendor.resolveComponent("uni-list-item");
  const _component_uni_list = common_vendor.resolveComponent("uni-list");
  (_component_uni_list_item + _component_uni_list)();
}
const _sfc_main = {
  __name: "attention",
  setup(__props) {
    const userResStore = store_logindata.useUserResStore();
    const userlist = common_vendor.ref([]);
    common_vendor.ref({});
    common_vendor.index.request({
      url: "http://localhost:8080/attention/getAttention",
      method: "POST",
      data: {
        userId: userResStore.userRes.userId
      },
      success: (res) => {
        if (res.data.status == 200) {
          userlist.value = res.data.data;
        } else {
          console.log("没找到");
        }
      },
      fail: (err) => {
        console.log(err);
      }
    });
    common_vendor.onTabItemTap((e) => {
      if (!userResStore.isLogined) {
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
        a: common_vendor.f(userlist.value, (p, index, i0) => {
          return {
            a: p.userHead,
            b: common_vendor.t(p.username),
            c: index,
            d: "2780f6f7-1-" + i0 + ",2780f6f7-0"
          };
        })
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/attention/attention.vue"]]);
wx.createPage(MiniProgramPage);
