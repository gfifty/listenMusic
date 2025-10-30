"use strict";
const common_vendor = require("../../common/vendor.js");
const store_logindata = require("../../store/logindata.js");
if (!Array) {
  const _component_H1 = common_vendor.resolveComponent("H1");
  _component_H1();
}
const _sfc_main = {
  __name: "register",
  setup(__props) {
    const UserResStore = store_logindata.useUserResStore();
    const username = common_vendor.ref("");
    const password = common_vendor.ref("");
    const repassword = common_vendor.ref("");
    const toRegister = () => {
      if (password.value != repassword.value) {
        common_vendor.index.showToast({
          title: "两个输入的密码不同",
          icon: "none"
        });
      } else {
        common_vendor.index.request({
          url: "http://localhost:8080/user/addOne",
          method: "POST",
          data: {
            username: username.value,
            password: password.value
          },
          success: (res) => {
            if (res.data.status == 200) {
              UserResStore.setUserRes(res.data);
              common_vendor.index.navigateTo({
                url: "/pages/login/login"
              });
            } else {
              console.log("错误");
            }
          }
        });
      }
    };
    return (_ctx, _cache) => {
      return {
        a: username.value,
        b: common_vendor.o(($event) => username.value = $event.detail.value),
        c: password.value,
        d: common_vendor.o(($event) => password.value = $event.detail.value),
        e: repassword.value,
        f: common_vendor.o(($event) => repassword.value = $event.detail.value),
        g: common_vendor.o(toRegister)
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/register/register.vue"]]);
wx.createPage(MiniProgramPage);
