"use strict";
const common_vendor = require("../../common/vendor.js");
const store_player = require("../../store/player.js");
const _sfc_main = {
  __name: "play",
  setup(__props) {
    common_vendor.onLoad(() => {
      const player2 = store_player.usePlayerStore();
      const playlist = common_vendor.computed(() => player2.playlist);
      const index = common_vendor.computed(() => player2.index);
      player2.setPlaylist(playlist, index);
      player2.playTrack(playlist[index]);
    });
    const currentTrack = common_vendor.computed(() => player.currentTrack || {});
    const playing = common_vendor.computed(() => player.playing);
    const progress = common_vendor.computed(() => player.progress);
    const duration = common_vendor.computed(() => player.duration);
    const lyrics = common_vendor.computed(() => player.lyrics);
    const currentLyricIndex = common_vendor.computed(() => player.currentLyricIndex);
    const showPlaylist = common_vendor.computed(() => player.showPlaylist);
    const mode = common_vendor.computed(() => player.mode);
    const modeIcon = common_vendor.computed(() => {
      if (mode.value === "order")
        return "🔁";
      if (mode.value === "shuffle")
        return "🔀";
      if (mode.value === "single")
        return "🔂";
      return "🔁";
    });
    const playTrack = (track) => player.playTrack(track);
    const togglePlay = () => player.togglePlay();
    const next = () => player.next();
    const prev = () => player.prev();
    const toggleMode = () => player.toggleMode();
    const togglePlaylist = () => player.showPlaylist = !player.showPlaylist;
    const onSeek = (e) => player.seek(e.detail.value);
    const formatTime = (sec) => {
      if (!sec)
        return "00:00";
      const m = Math.floor(sec / 60).toString().padStart(2, "0");
      const s = Math.floor(sec % 60).toString().padStart(2, "0");
      return `${m}:${s}`;
    };
    return (_ctx, _cache) => {
      return common_vendor.e({
        a: common_vendor.unref(currentTrack).cover,
        b: common_vendor.t(common_vendor.unref(currentTrack).name || "未播放"),
        c: common_vendor.t(common_vendor.unref(currentTrack).artist || "未知歌手"),
        d: common_vendor.t(formatTime(common_vendor.unref(progress))),
        e: common_vendor.unref(progress),
        f: common_vendor.unref(duration),
        g: common_vendor.o(onSeek),
        h: common_vendor.t(formatTime(common_vendor.unref(duration))),
        i: common_vendor.o(prev),
        j: common_vendor.t(common_vendor.unref(playing) ? "⏸" : "▶️"),
        k: common_vendor.o(togglePlay),
        l: common_vendor.o(next),
        m: common_vendor.t(common_vendor.unref(modeIcon)),
        n: common_vendor.o(toggleMode),
        o: common_vendor.o(togglePlaylist),
        p: common_vendor.f(common_vendor.unref(lyrics), (line, i, i0) => {
          return {
            a: common_vendor.t(line.text),
            b: i,
            c: common_vendor.n({
              active: i === common_vendor.unref(currentLyricIndex)
            })
          };
        }),
        q: common_vendor.unref(showPlaylist)
      }, common_vendor.unref(showPlaylist) ? {
        r: common_vendor.f(_ctx.playlist, (track, i, i0) => {
          return {
            a: common_vendor.t(i + 1),
            b: common_vendor.t(track.name),
            c: common_vendor.t(track.artist),
            d: track.id,
            e: track.id === common_vendor.unref(currentTrack).id ? 1 : "",
            f: common_vendor.o(($event) => playTrack(track), track.id)
          };
        })
      } : {});
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__scopeId", "data-v-edf5a525"], ["__file", "Z:/U盘资源拷贝/namelessMusic/音乐/pages/play/play.vue"]]);
wx.createPage(MiniProgramPage);
