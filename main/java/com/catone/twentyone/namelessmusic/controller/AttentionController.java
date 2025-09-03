package com.catone.twentyone.namelessmusic.controller;

import com.catone.twentyone.namelessmusic.entity.Attention;
import com.catone.twentyone.namelessmusic.entity.User;
import com.catone.twentyone.namelessmusic.service.IAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.catone.twentyone.namelessmusic.service.util.service.util.FwResult;

import java.util.List;

@RestController
@RequestMapping("/attention")
public class AttentionController {
@Autowired
IAttentionService iAttention;
//获取关注列表
    @PostMapping("/getAttention")
    public FwResult getAttention(@RequestBody Attention attention){
        List<User> userList =iAttention.getAttentionList(attention);
        return FwResult.ok(userList,"获取成功");
    }
}
