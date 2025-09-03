package com.catone.twentyone.namelessmusic.controller;


import com.catone.twentyone.namelessmusic.entity.ThumbsUp;
import com.catone.twentyone.namelessmusic.entity.dto.ThumbsUpDTO;
import com.catone.twentyone.namelessmusic.service.IThumbsUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.catone.twentyone.namelessmusic.service.util.service.util.FwResult;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/thumbsUp")
public class thumbsUpController {
    IThumbsUpService iThumbsUpService;
    //    对歌曲进行点赞操作
    @PostMapping("/giveThumbsUp")
    public FwResult giveThumbsUp(@RequestBody ThumbsUpDTO thumbs){
        ThumbsUp thumbs1 =iThumbsUpService.giveThumbsUp(thumbs);
        return FwResult.ok(thumbs1,"获取成功");
    }
}
