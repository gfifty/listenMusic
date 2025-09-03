package com.catone.twentyone.namelessmusic.controller;

import com.catone.twentyone.namelessmusic.entity.AlbumSingle;
import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithAlbumInfoDTO;
import com.catone.twentyone.namelessmusic.service.IAlbumSingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.catone.twentyone.namelessmusic.service.util.service.util.FwResult;

import java.util.List;

@RestController
@RequestMapping("/albumSingle")
public class AlbumSingleController {
    @Autowired
    IAlbumSingleService iAlbumSingleService;
//    获取专辑中的歌曲
    @PostMapping("/getAlbumSingle")
    public FwResult getAlbumSingle(@RequestBody AlbumSingle albumSingle){
        List<MusicWithAlbumInfoDTO> albumSingle1 = iAlbumSingleService.getAlbumSingle(albumSingle);
        return FwResult.ok(albumSingle1,"查询成功");
    }
}
