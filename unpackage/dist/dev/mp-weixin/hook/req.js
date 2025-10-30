"use strict";
const common_vendor = require("../common/vendor.js");
const req = (urlString) => {
  const promise = new Promise((res, req2) => {
    common_vendor.index.request({
      url: urlString,
      success(result) {
        res(result);
      },
      fail(err) {
      }
    });
  });
  return promise;
};
exports.req = req;
