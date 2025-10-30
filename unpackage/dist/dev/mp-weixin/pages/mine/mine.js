"use strict";
const common_vendor = require("../../common/vendor.js");
const store_logindata = require("../../store/logindata.js");
if (!Array) {
  const _component_uni_list_item = common_vendor.resolveComponent("uni-list-item");
  const _component_uni_list = common_vendor.resolveComponent("uni-list");
  (_component_uni_list_item + _component_uni_list)();
}
if (!Math) {
  personMusic();
}
const personMusic = () => "../../components/personMusic/personMusic.js";
const _sfc_main = {
  __name: "mine",
  setup(__props) {
    const UserResStore = store_logindata.useUserResStore();
    const selectedButton = common_vendor.ref(1);
    const toSelect = common_vendor.ref("personmusic");
    function unLogin() {
      UserResStore.setIsLogined(false);
      common_vendor.index.switchTab({
        url: "/pages/index/index"
      });
    }
    common_vendor.onTabItemTap((e) => {
      if (!UserResStore.isLogined) {
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
      return common_vendor.e({
        a: common_vendor.o(unLogin),
        b: common_vendor.unref(UserResStore).userRes.userHead,
        c: common_vendor.t(common_vendor.unref(UserResStore).userRes.username),
        d: selectedButton.value === 1 ? 1 : "",
        e: common_vendor.o(($event) => {
          _ctx.selectpage("personmusic");
          _ctx.changeColor(1);
        }),
        f: toSelect.value === "personmusic"
      }, toSelect.value === "personmusic" ? {} : {});
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/mine/mine.vue"]]);
wx.createPage(MiniProgramPage);
