"use strict";
Object.defineProperty(exports, Symbol.toStringTag, { value: "Module" });
const common_vendor = require("./common/vendor.js");
if (!Math) {
  "./pages/index/index.js";
  "./pages/mine/mine.js";
  "./pages/trends/trends.js";
  "./pages/attention/attention.js";
  "./pages/search/search.js";
  "./pages/play/play.js";
  "./pages/login/login.js";
  "./pages/register/register.js";
  "./pages/typeShow/typeShow.js";
  "./pages/singlistDetail/singlistDetail.js";
  "./pages/changehuman/changehuman.js";
  "./pages/albumDetail/albumDetail.js";
}
const _sfc_main = {
  onLaunch: function() {
    console.log("App Launch");
  },
  onShow: function() {
  },
  onHide: function() {
    console.log("App Hide");
  }
};
const App = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/App.vue"]]);
function createApp() {
  const app = common_vendor.createSSRApp(App);
  const pinia = common_vendor.createPinia();
  app.use(pinia);
  return {
    app
  };
}
createApp().app.mount("#app");
exports.createApp = createApp;
