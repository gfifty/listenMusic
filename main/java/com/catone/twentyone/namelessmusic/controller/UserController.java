package com.catone.twentyone.namelessmusic.controller;

import com.catone.twentyone.namelessmusic.entity.User;
import com.catone.twentyone.namelessmusic.entity.dto.UserDTO;
import com.catone.twentyone.namelessmusic.service.IUserService;
import com.catone.twentyone.namelessmusic.service.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import com.catone.twentyone.namelessmusic.service.util.service.util.FwResult;
import org.springframework.web.bind.annotation.*;

@RestController //接口返回对象，直接转换为文本
@RequestMapping("/user") //localhost:8080/user
public class UserController {
    @Autowired
    IUserService iUserService;
//    增加用户
    @PostMapping("/addOne")
    public FwResult addOneUser(@Validated @RequestBody UserDTO user){
        User newuser=iUserService.add(user);
        return FwResult.ok(newuser,"添加成功");
    }
//      登录用户
    @PostMapping("/login")
    public FwResult login(@Validated @RequestBody UserDTO user){
            User userNew = iUserService.login(user);
            String token =JwtTokenUtil.createToken(user.getUsername());
        return FwResult.ok(userNew,"登录成功").setToken(token);
    }
//    修改用户地址
    @PostMapping("/updateAddress")
    public FwResult upadteAddress(@Validated @RequestBody UserDTO user){
        User usernew = iUserService.updateAddress(user);
        return FwResult.ok(usernew,"修改成功");
    }
//    修改用户性别
    @PostMapping("/updateSex")
    public FwResult upadteSex(@Validated @RequestBody UserDTO user){
        User user1 =iUserService.updateSex(user);
        return FwResult.ok(user1,"修改成功");
    }
//修改用户生日
    @PostMapping("/updateBirthday")
    public FwResult updateBirthday(@Validated @RequestBody UserDTO user){
         User user1 = iUserService.updateBirthday(user);
        return FwResult.ok(user1,"修改成功");
    }

}
