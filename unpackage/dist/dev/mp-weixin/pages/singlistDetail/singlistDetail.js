"use strict";
const common_vendor = require("../../common/vendor.js");
if (!Array) {
  const _component_uni_list_item = common_vendor.resolveComponent("uni-list-item");
  const _component_uni_list = common_vendor.resolveComponent("uni-list");
  (_component_uni_list_item + _component_uni_list)();
}
const _sfc_main = {
  __name: "singlistDetail",
  setup(__props) {
    const singlist = common_vendor.ref({});
    const singlistMusic = common_vendor.ref([]);
    common_vendor.onLoad((option) => {
      common_vendor.index.request({
        url: "http://localhost:8080/singlistMusic/getSinglistMusic",
        method: "POST",
        data: { singlistId: option.singlistId },
        success: (res) => {
          if (res.data.status === 200) {
            singlistMusic.value = res.data.data;
            console.log(singlistMusic.value);
          } else {
            console.log("获取歌曲列表失败，status不为200");
          }
        },
        fail: (err) => {
          console.log("请求失败:", err);
        }
      });
      fetchSinglistData(option);
    });
    const fetchSinglistData = async (option) => {
      try {
        const singlistRes = await new Promise((resolve, reject) => {
          common_vendor.index.request({
            url: "http://localhost:8080/singlistMusic/getSinglistThing",
            method: "POST",
            data: { singlistId: option.singlistId },
            success: (response) => resolve(response),
            fail: (error) => reject(error)
          });
        });
        if (singlistRes.data.status === 200) {
          singlist.value = singlistRes.data.data;
        } else {
          console.log("获取歌单信息失败，status不为200");
        }
      } catch (err) {
        console.error("请求失败:", err);
      }
    };
    function jumpTo(p, index) {
      common_vendor.index.navigateTo({
        url: `/pages/play/play?singlist=${encodeURIComponent(JSON.stringify(singlistMusic.value))}&music=${encodeURIComponent(JSON.stringify(p))}&index=${index}`
      });
    }
    return (_ctx, _cache) => {
      return {
        a: singlist.value.singlistHead,
        b: common_vendor.t(singlist.value.singlistName),
        c: common_vendor.t(singlist.value.singlistIntroduction),
        d: common_vendor.f(singlistMusic.value, (p, index, i0) => {
          return {
            a: common_vendor.t(index + 1),
            b: common_vendor.t(p.musicName),
            c: common_vendor.t(p.singerName),
            d: common_vendor.o(($event) => jumpTo(p, index), index),
            e: index,
            f: "468cbf8e-1-" + i0 + ",468cbf8e-0"
          };
        })
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/singlistDetail/singlistDetail.vue"]]);
wx.createPage(MiniProgramPage);
