"use strict";
const common_vendor = require("../common/vendor.js");
const useUserResStore = common_vendor.defineStore("user", {
  // 定义状态（参数，变量）
  state: () => {
    return {
      userRes: {},
      isLogined: false
    };
  },
  getters: {},
  // 动作支持异步
  actions: {
    setUserRes(Vi) {
      this.userRes = Vi;
    },
    setIsLogined(Vi) {
      this.isLogined = Vi;
    },
    //单属性添加
    setUserResField(key, value) {
      if (this.userRes) {
        this.userRes[key] = value;
      } else {
        this.userRes = { [key]: value };
      }
    }
  }
});
exports.useUserResStore = useUserResStore;
