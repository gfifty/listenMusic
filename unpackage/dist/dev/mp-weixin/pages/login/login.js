"use strict";
const common_vendor = require("../../common/vendor.js");
const store_logindata = require("../../store/logindata.js");
if (!Array) {
  const _component_H1 = common_vendor.resolveComponent("H1");
  _component_H1();
}
const _sfc_main = {
  __name: "login",
  setup(__props) {
    const UserResStore = store_logindata.useUserResStore();
    common_vendor.ref({});
    const username = common_vendor.ref("");
    const password = common_vendor.ref("");
    const tologin = () => {
      if (!username.value || !password.value) {
        common_vendor.index.showToast({
          "title": "用户名或密码不能为空",
          icon: "none"
        });
      } else {
        common_vendor.index.request({
          url: `http://localhost:8080/user/login`,
          method: "POST",
          header: {
            "Content-Type": "application/json"
            // 发送 JSON 数据
          },
          data: JSON.stringify({
            username: username.value,
            password: password.value
          }),
          success: (res) => {
            if (res.data.status == 200) {
              UserResStore.setUserRes(res.data.data);
              UserResStore.setIsLogined(true);
              common_vendor.index.switchTab({
                url: "/pages/index/index"
              });
            } else {
              common_vendor.index.showToast({
                title: "用户名或密码错误",
                icon: "error"
              });
            }
          },
          fail: (err) => {
            console.log(err);
          }
        });
      }
    };
    const toRegister = () => {
      common_vendor.index.navigateTo({
        url: "/pages/register/register"
      });
    };
    return (_ctx, _cache) => {
      return {
        a: username.value,
        b: common_vendor.o(($event) => username.value = $event.detail.value),
        c: password.value,
        d: common_vendor.o(($event) => password.value = $event.detail.value),
        e: common_vendor.o(tologin),
        f: common_vendor.o(toRegister)
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/login/login.vue"]]);
wx.createPage(MiniProgramPage);
