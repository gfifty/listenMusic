package com.catone.twentyone.namelessmusic.entity.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Integer userId;

    @NotBlank(message = "用户名不能为空")
    private String username;


    private String phone;

    @TableField(value = "user_head")
    private  String userHead;


    @NotBlank(message = "密码不能为空")
    private String password;


    private String birthday;


    private Integer sex;


    @Email(message = "邮箱格式错误")
    private String email;


    private String address;

@TableField(value = "later_time")
    private Date laterTime;
}
