"use strict";
const common_vendor = require("../../common/vendor.js");
const store_logindata = require("../../store/logindata.js");
const _sfc_main = {
  __name: "changehuman",
  setup(__props) {
    const UserResStore = store_logindata.useUserResStore();
    const radioItems = common_vendor.ref([{ name: 0, value: "女" }, { name: "1", value: "男", checked: "true" }]);
    function sexChange(e) {
      common_vendor.index.request({
        url: "http://localhost:8080/user/updateSex",
        method: "POST",
        data: {
          userId: UserResStore.userRes.userId,
          username: UserResStore.userRes.username,
          password: UserResStore.userRes.password,
          sex: e.target.value
        },
        success: (res) => {
          if (res.data.status == 200) {
            console.log("");
            common_vendor.index.showToast({
              title: "修改成功",
              icon: "success"
            });
          }
        }
      });
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.f(radioItems.value, (item, index, i0) => {
          return {
            a: item.name,
            b: item.name,
            c: item.checked,
            d: common_vendor.t(item.value),
            e: item.name,
            f: index
          };
        }),
        b: common_vendor.o(sexChange)
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/changehuman/changehuman.vue"]]);
wx.createPage(MiniProgramPage);
