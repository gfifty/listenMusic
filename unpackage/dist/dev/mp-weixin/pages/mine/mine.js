"use strict";
const common_vendor = require("../../common/vendor.js");
const store_logindata = require("../../store/logindata.js");
if (!Math) {
  (personMusic + miniPlayer)();
}
const miniPlayer = () => "../../components/miniPlayer/miniPlayer1.js";
const personMusic = () => "../../components/personMusic/personMusic.js";
const _sfc_main = {
  __name: "mine",
  setup(__props) {
    const UserResStore = store_logindata.useUserResStore();
    common_vendor.ref(1);
    const toSelect = common_vendor.ref("personmusic");
    function unLogin() {
      UserResStore.resetUser();
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
        a: common_vendor.unref(UserResStore).userRes.userHead,
        b: common_vendor.t(common_vendor.unref(UserResStore).userRes.username),
        c: toSelect.value === "personmusic"
      }, toSelect.value === "personmusic" ? {} : {}, {
        d: common_vendor.o(($event) => unLogin())
      });
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/mine/mine.vue"]]);
wx.createPage(MiniProgramPage);
