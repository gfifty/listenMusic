package com.catone.twentyone.namelessmusic.controller;

import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.Singlist;
import com.catone.twentyone.namelessmusic.entity.Singlist_Music;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithCoverDTO;
import com.catone.twentyone.namelessmusic.service.ISinglist_MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.catone.twentyone.namelessmusic.service.util.service.util.FwResult;

import java.util.List;

@RestController
@RequestMapping("/singlistMusic")
public class Singlist_MusicController {
    @Autowired
    private ISinglist_MusicService singlistMusicService;

//    获取歌单信息
    @PostMapping("/getSinglistThing")
    public  FwResult getSinglistThing(@RequestBody Singlist singlist){
        Singlist singlist1 = singlistMusicService.getSinglistThing(singlist);
        return FwResult.ok(singlist1,"搜索成功");
    }
//    获取歌单下的歌曲
    @PostMapping("/getSinglistMusic")
    public FwResult getSinglistMusic(@RequestBody Singlist singlist) {
        List<MusicWithCoverDTO> musicList = singlistMusicService.getSinglistWithMusic(singlist);
        // 调用服务层方法获取歌单及歌曲信息
        return FwResult.ok(musicList,"搜索成功");
    }

//添加歌曲到歌单
    @PostMapping("/addMusicToList")
    public FwResult addMusicToList(@RequestBody Singlist_Music singlist_music) {
        boolean res= singlistMusicService.addMusicToList(singlist_music);
        System.out.println(res);
        if (res){
            return FwResult.ok("创建成功");
        }else {
            return FwResult.failed("创建失败");
        }
    }

}
