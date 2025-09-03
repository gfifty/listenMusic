package com.catone.twentyone.namelessmusic.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: makejava
 * @date: 2024-11-20 00:28:49
 * @description: (User)实体类
 */
@Data
@Table

public class User implements Serializable {
    private static final long serialVersionUID = -42040816097577063L;

    @TableId(type = IdType.AUTO)
    private Integer userId;

//    @ApiModelProperty("用户名")
    private String username;

@TableField(value = "user_head")
     private  String userHead;

//    @ApiModelProperty("手机号")
    private String phone;

//    @ApiModelProperty("密码")
    private String password;

//    @ApiModelProperty("生日")
    private String birthday;

//    @ApiModelProperty("性别")
    private Integer sex;

//    @ApiModelProperty("邮箱")/
    private String email;

//    @ApiModelProperty("住址")
    private String address;

//    @ApiModelProperty("最近一次登录时间")
@TableField(value = "later_time",fill = FieldFill.INSERT_UPDATE)
    private Date laterTime;

}


