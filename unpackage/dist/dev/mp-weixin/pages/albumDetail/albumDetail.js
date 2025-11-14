"use strict";
const common_vendor = require("../../common/vendor.js");
const store_player = require("../../store/player.js");
if (!Array) {
  const _component_uni_list_item = common_vendor.resolveComponent("uni-list-item");
  const _component_uni_list = common_vendor.resolveComponent("uni-list");
  (_component_uni_list_item + _component_uni_list)();
}
const _sfc_main = {
  __name: "albumDetail",
  setup(__props) {
    const player = store_player.usePlayerStore();
    common_vendor.storeToRefs(player);
    const alubum = common_vendor.ref();
    const alubumList = common_vendor.ref({});
    common_vendor.onLoad((option) => {
      common_vendor.index.request({
        url: "http://localhost:8080/albumSingle/getAlbumSingle",
        method: "POST",
        data: {
          albumId: option.albumId
        },
        success: (res) => {
          if (res.data.status == 200) {
            alubumList.value = res.data.data;
            console.log(alubumList.value);
          } else {
            console.log("获取歌曲列表失败，status不为200");
          }
        },
        fail: (err) => {
          console.log(err);
        }
      });
      fetchSinglistData(option);
    });
    const fetchSinglistData = async (option) => {
      try {
        const alubumListRes = await new Promise((resolve, reject) => {
          common_vendor.index.request({
            url: "http://localhost:8080/album/getAlbumThing",
            method: "POST",
            data: {
              albumId: option.albumId
            },
            success: (res) => {
              resolve(res);
            },
            fail: (err) => {
              reject(err);
            }
          });
        });
        if (alubumListRes.data.status === 200) {
          alubum.value = alubumListRes.data.data;
        } else {
          console.log("获取歌单信息失败，status不为200");
        }
      } catch (err) {
        console.error("请求失败：", err);
      }
    };
    function jumpTo(p, index) {
      const playerStore = store_player.usePlayerStore();
      playerStore.setPlaylist(alubumList.value, index);
      common_vendor.index.navigateTo({
        url: `/pages/play/play`
      });
    }
    return (_ctx, _cache) => {
      return {
        a: alubum.value.albumHead,
        b: common_vendor.t(alubum.value.albumName),
        c: common_vendor.t(alubum.value.albumIntroduction),
        d: common_vendor.f(alubumList.value, (p, index, i0) => {
          return {
            a: common_vendor.t(index + 1),
            b: common_vendor.t(p.musicName),
            c: common_vendor.t(p.singerName),
            d: common_vendor.o(($event) => jumpTo(p, index), index),
            e: index,
            f: "6552c63b-1-" + i0 + ",6552c63b-0"
          };
        })
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/albumDetail/albumDetail.vue"]]);
wx.createPage(MiniProgramPage);
