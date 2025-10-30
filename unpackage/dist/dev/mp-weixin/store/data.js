"use strict";
const common_vendor = require("../common/vendor.js");
const useSearchresStore = common_vendor.defineStore("search", {
  // 定义状态（参数，变量）
  state: () => {
    return {
      searchRes: ""
    };
  },
  getters: {},
  // 动作支持异步
  actions: {
    setSearchRES(V) {
      this.searchRes = V;
    }
  }
});
exports.useSearchresStore = useSearchresStore;
