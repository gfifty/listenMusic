package com.catone.twentyone.namelessmusic.controller;

import com.catone.twentyone.namelessmusic.entity.Album;
import com.catone.twentyone.namelessmusic.entity.Singlist;
import com.catone.twentyone.namelessmusic.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.catone.twentyone.namelessmusic.service.util.service.util.FwResult;
import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    IAlbumService iAlbumService;
//搜索专辑
    @PostMapping("/searchAlbum")
    public FwResult searchAlbum(@RequestBody Album album){
        List<Album> albumList = iAlbumService.searchAlbum(album);
        return FwResult.ok(albumList,"搜索成功");
    }
    //    获取歌单信息
    @PostMapping("/getAlbumThing")
    public  FwResult getAlbumThing(@RequestBody Album album){
        Album album1 = iAlbumService.getAlbumThing(album);
        return FwResult.ok(album1,"搜索成功");
    }
}
