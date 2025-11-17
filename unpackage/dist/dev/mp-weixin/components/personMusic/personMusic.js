"use strict";
const common_vendor = require("../../common/vendor.js");
const store_logindata = require("../../store/logindata.js");
if (!Array) {
  const _easycom_uni_popup2 = common_vendor.resolveComponent("uni-popup");
  _easycom_uni_popup2();
}
const _easycom_uni_popup = () => "../../uni_modules/uni-popup/components/uni-popup/uni-popup.js";
if (!Math) {
  _easycom_uni_popup();
}
const _sfc_main = {
  __name: "personMusic",
  setup(__props) {
    const userResStore = store_logindata.useUserResStore();
    const singleList = common_vendor.ref([]);
    const selectedImage = common_vendor.ref("");
    common_vendor.ref("");
    const SMMS_API_KEY = "TkySYmMinE36XLUlKJmLI1PceSFaFtac";
    const popup = common_vendor.ref(null);
    const open = () => {
      if (popup.value)
        popup.value.open("bottom");
    };
    const newSinglistName = common_vendor.ref("");
    const newSinglistDesc = common_vendor.ref("");
    const previewImage = common_vendor.ref(null);
    common_vendor.index.request({
      url: "http://localhost:8080/singlist/lookSinglist",
      method: "POST",
      data: {
        userId: userResStore.userRes.userId
      },
      success(res) {
        if (res.data.status == 200) {
          singleList.value = res.data.data;
          console.log(singleList.value);
        }
      },
      fail: (err) => {
        console.log(err);
      }
    });
    function openSinglist(singlistId) {
      common_vendor.index.navigateTo({
        url: `/pages/singlistDetail/singlistDetail?singlistId=${singlistId}`
      });
      console.log(singlistId);
    }
    const createSinglist = async () => {
      if (!newSinglistName.value.trim()) {
        common_vendor.index.showToast({ title: "歌单名称不能为空", icon: "none" });
        return;
      }
      try {
        let singlistHead = "";
        if (selectedImage.value) {
          const uploadResult = await uploadToSmms(selectedImage.value);
          if (uploadResult && uploadResult.success) {
            singlistHead = uploadResult.data.url;
            console.log("11");
          } else {
            common_vendor.index.showToast({ title: "图片上传失败", icon: "none" });
            return;
          }
        }
        common_vendor.index.request({
          url: "http://localhost:8080/singlist/createSinglist",
          method: "POST",
          data: {
            userId: userResStore.userRes.userId,
            singlistName: newSinglistName.value,
            singlistIntroduction: newSinglistDesc.value || "",
            singlistHead
          },
          success(res) {
            if (res.data.status === 200) {
              common_vendor.index.showToast({ title: "创建成功", icon: "success" });
              singleList.value.push({
                singlistName: newSinglistName.value,
                singlistHead,
                singlistIntroduction: newSinglistDesc.value || "",
                singlistId: res.data.data.singlistId
                // 从后端返回结果获取歌单ID
              });
              console.log(singleList.value);
              popup.value.close();
              newSinglistName.value = "";
              newSinglistDesc.value = "";
              previewImage.value = null;
              selectedImage.value = null;
            } else {
              common_vendor.index.showToast({ title: "创建失败", icon: "none" });
            }
          },
          fail(err) {
            common_vendor.index.showToast({ title: "请求失败", icon: "none" });
          }
        });
      } catch (err) {
        common_vendor.index.showToast({ title: "图片上传失败", icon: "none" });
      }
    };
    const chooseImage = () => {
      common_vendor.index.chooseImage({
        count: 1,
        // 限制只能选择 1 张图片
        success: (chooseRes) => {
          selectedImage.value = chooseRes.tempFilePaths[0];
          previewImage.value = selectedImage;
          common_vendor.index.showToast({
            title: "图片选择成功",
            icon: "success"
          });
        },
        fail: () => {
          common_vendor.index.showToast({
            title: "图片选择失败",
            icon: "error"
          });
        }
      });
    };
    const uploadToSmms = (filePath) => {
      return new Promise((resolve, reject) => {
        common_vendor.index.uploadFile({
          url: "https://sm.ms/api/v2/upload",
          filePath,
          name: "smfile",
          // sm.ms 接收的字段名是 `smfile`
          header: {
            Authorization: SMMS_API_KEY
            // 在请求头中添加 API Key
          },
          success: (uploadRes) => {
            const res = JSON.parse(uploadRes.data);
            resolve(res);
          },
          fail: (err) => {
            reject(err);
          }
        });
      });
    };
    return (_ctx, _cache) => {
      return {
        a: common_vendor.t(singleList.value.length),
        b: common_vendor.f(singleList.value, (singleList2, index, i0) => {
          return {
            a: singleList2.singlistHead,
            b: common_vendor.t(singleList2.singlistName),
            c: index,
            d: common_vendor.o(($event) => openSinglist(singleList2.singlistId), index)
          };
        }),
        c: common_vendor.o(open),
        d: common_vendor.o((...args) => _ctx.closeModal && _ctx.closeModal(...args)),
        e: newSinglistName.value,
        f: common_vendor.o(($event) => newSinglistName.value = $event.detail.value),
        g: newSinglistDesc.value,
        h: common_vendor.o(($event) => newSinglistDesc.value = $event.detail.value),
        i: common_vendor.o(chooseImage),
        j: common_vendor.o(createSinglist),
        k: common_vendor.sr(popup, "aa36beda-0", {
          "k": "popup"
        }),
        l: common_vendor.p({
          type: "bottom",
          ["border-radius"]: "10px 10px 0 0"
        })
      };
    };
  }
};
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/components/personMusic/personMusic.vue"]]);
wx.createComponent(Component);
