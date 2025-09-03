package com.catone.twentyone.namelessmusic.controller;

import com.catone.twentyone.namelessmusic.entity.Singer;
import com.catone.twentyone.namelessmusic.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.catone.twentyone.namelessmusic.service.util.service.util.FwResult;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    ISingerService iSingerService;
//    搜索歌手
    @PostMapping("/searchSinger")
    public FwResult searchSinger(@RequestBody Singer singer){
        List<Singer> singerList=iSingerService.searchSinger(singer);
        return FwResult.ok(singerList,"查询成功");
    }
}
