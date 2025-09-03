package com.catone.twentyone.namelessmusic.controller;

import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithCoverDTO;
import com.catone.twentyone.namelessmusic.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.catone.twentyone.namelessmusic.service.util.service.util.FwResult;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/Music")
public class MusicController {
    @Autowired
    IMusicService iMusicService;
//    获取热门歌曲
    @GetMapping("/hotMusic")
    public FwResult getHotList(){
        List<MusicWithCoverDTO> music1 = iMusicService.getHotListv();
        return FwResult.ok(music1,"获取成功");
    }
//    搜索音乐
    @PostMapping("/searchMusic")
    public FwResult searchMusic(@RequestBody Music music){
        List<MusicWithCoverDTO> music1 = iMusicService.searchMusic(music);
        return FwResult.ok(music1,"获取成功");
    }
//    获取对应类型的歌曲
    @PostMapping("/getMusicType")
    public FwResult getMusicType(@RequestBody Music music){
        List<MusicWithCoverDTO> musicList =iMusicService.getMusicType(music);
        return FwResult.ok(musicList,"获取成功");
    }

}
