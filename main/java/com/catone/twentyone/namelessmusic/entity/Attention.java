package com.catone.twentyone.namelessmusic.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: makejava
 * @date: 2024-11-20 00:26:06
 * @description: (Attention)实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Attention implements Serializable {
    private static final long serialVersionUID = -20591538339222411L;

//    @ApiModelProperty("id（主键）")
@TableId(type = IdType.AUTO)
    private Integer id;

//    @ApiModelProperty("关注者id（外键），关联user表")
@TableField(value = "user_id")
    private Integer userId;

//    @ApiModelProperty("被关注者的id（外键），关联user表")
@TableField(value = "followser_id")
    private Integer followuserId;

}


