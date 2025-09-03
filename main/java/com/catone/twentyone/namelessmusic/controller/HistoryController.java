package com.catone.twentyone.namelessmusic.controller;

import com.catone.twentyone.namelessmusic.entity.History;
import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.User;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithCoverDTO;
import com.catone.twentyone.namelessmusic.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.catone.twentyone.namelessmusic.service.util.service.util.FwResult;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    IHistoryService iHistoryService;
//    获取音乐对应的历史记录
    @PostMapping("/getHistory")
    public FwResult getmusicHistory(@RequestBody  User user){
        List<MusicWithCoverDTO> history1 = iHistoryService.getmusicHistory(user);
        return FwResult.ok(history1,"获取成功");
    }
//    产生历史记录
    @PostMapping("/createLog")
    public FwResult createLog(@RequestBody History history){
        boolean res= iHistoryService.createLog(history);
        System.out.println(res);
        if (res){
            return FwResult.ok("创建成功");
        }else {
            return FwResult.failed("创建失败");
        }
    }
}
