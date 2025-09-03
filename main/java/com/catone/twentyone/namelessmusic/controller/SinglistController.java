package com.catone.twentyone.namelessmusic.controller;

import com.catone.twentyone.namelessmusic.entity.Singlist;
import com.catone.twentyone.namelessmusic.service.ISinglistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.catone.twentyone.namelessmusic.service.util.service.util.FwResult;

import java.util.List;


@RestController
@ResponseBody
@RequestMapping("/singlist")
public class SinglistController {
    @Autowired
    ISinglistService iSinglistService;
//    搜索歌单
    @PostMapping("/secrchSinglist")
    public FwResult secrchSinglist(@RequestBody Singlist singlist){
        List<Singlist> singlistList= iSinglistService.secrchSinglist(singlist);
        return  FwResult.ok(singlistList,"搜索成功");
    }
    //获取歌单的内容
    @PostMapping("/getSinglist")
    public FwResult getSinglist(@RequestBody Singlist singlist){
        Singlist singlist1 =iSinglistService.getSinglist(singlist);
        return FwResult.ok(singlist1,"获取成功");
    }
    //创建歌单
    @PostMapping("/createSinglist")
    public FwResult createSinglist(@Validated @RequestBody Singlist singlist){
        boolean res= iSinglistService.createSinglist(singlist);
        if (res){
            return FwResult.ok("创建成功");
        }else {
            return FwResult.failed("创建失败");
        }
    }
//    获取热门歌单
    @GetMapping("/getHotList")
    public  FwResult getHotList(){
        List<Singlist> singlistList=iSinglistService.getHotList();
        return FwResult.ok(singlistList,"获取成功");
    }
//    查看自己的歌单
    @PostMapping("/lookSinglist")
    public  FwResult getMyList(@RequestBody Singlist singlist){
        List<Singlist> singlistList=iSinglistService.getMyList(singlist);
        return FwResult.ok(singlistList,"获取成功");
    }
}
