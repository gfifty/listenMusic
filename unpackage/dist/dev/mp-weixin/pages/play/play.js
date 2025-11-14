"use strict";
const common_vendor = require("../../common/vendor.js");
const store_player = require("../../store/player.js");
const _sfc_main = {
  __name: "play",
  setup(__props) {
    const player = store_player.usePlayerStore();
    const { currentTrack, playing, progress, duration, lyrics, currentLyricIndex, showPlaylist, mode } = common_vendor.storeToRefs(player);
    const playlist = common_vendor.computed(() => player.playlist);
    const index = common_vendor.computed(() => player.index);
    common_vendor.onLoad(() => {
      var _a;
      if (((_a = playlist.value) == null ? void 0 : _a.length) > 0) {
        player.setPlaylist(playlist.value, index.value);
      }
    });
    const modeIcon = common_vendor.computed(() => {
      switch (mode.value) {
        case "order":
          return "🔁";
        case "shuffle":
          return "🔀";
        default:
          return "🔁";
      }
    });
    const playTrack = (track) => player.playTrack(track);
    const togglePlay = () => player.togglePlay();
    const next = () => {
      player.next();
    };
    const prev = () => player.prev();
    const toggleMode = () => player.toggleMode();
    const togglePlaylist = () => player.togglePlaylist();
    const onChanging = (e) => {
      console.log("onChanging:", e.detail.value);
      progress.value = e.detail.value;
    };
    const onSeek = (e) => {
      console.log("onSeek：", e.detail.value);
      player.seek(e.detail.value);
    };
    const formatTime = (sec) => {
      if (!sec)
        return "00:00";
      const m = Math.floor(sec / 60).toString().padStart(2, "0");
      const s = Math.floor(sec % 60).toString().padStart(2, "0");
      return `${m}:${s}`;
    };
    common_vendor.watch(currentTrack, (newTrack) => {
      console.log("🎵 当前播放歌曲:", newTrack);
    });
    return (_ctx, _cache) => {
      return common_vendor.e({
        a: common_vendor.unref(currentTrack).cover,
        b: common_vendor.t(common_vendor.unref(currentTrack).musicName || "未播放"),
        c: common_vendor.t(common_vendor.unref(currentTrack).singerName || "未知歌手"),
        d: common_vendor.t(formatTime(common_vendor.unref(progress))),
        e: common_vendor.unref(progress),
        f: common_vendor.unref(duration),
        g: common_vendor.o(onChanging),
        h: common_vendor.o(onSeek),
        i: common_vendor.t(formatTime(common_vendor.unref(duration))),
        j: common_vendor.o(prev),
        k: common_vendor.t(common_vendor.unref(playing) ? "⏸" : "▶️"),
        l: common_vendor.o(togglePlay),
        m: common_vendor.o(next),
        n: common_vendor.t(common_vendor.unref(modeIcon)),
        o: common_vendor.o(toggleMode),
        p: common_vendor.o(togglePlaylist),
        q: common_vendor.f(common_vendor.unref(lyrics), (line, i, i0) => {
          return {
            a: common_vendor.t(line.text),
            b: i,
            c: common_vendor.n({
              active: i === common_vendor.unref(currentLyricIndex)
            })
          };
        }),
        r: common_vendor.unref(showPlaylist)
      }, common_vendor.unref(showPlaylist) ? {
        s: common_vendor.f(common_vendor.unref(playlist), (track, i, i0) => {
          return {
            a: common_vendor.t(i + 1),
            b: common_vendor.t(track.musicName),
            c: common_vendor.t(track.singerName),
            d: track.musicId,
            e: track.musicId === common_vendor.unref(currentTrack).musicId ? 1 : "",
            f: common_vendor.o(($event) => playTrack(track), track.musicId)
          };
        })
      } : {});
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__scopeId", "data-v-edf5a525"], ["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/play/play.vue"]]);
wx.createPage(MiniProgramPage);
