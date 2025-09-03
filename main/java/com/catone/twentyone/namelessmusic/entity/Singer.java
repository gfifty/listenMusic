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
 * @date: 2024-11-20 00:28:25
 * @description: (Singer)实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table

public class Singer implements Serializable {
    private static final long serialVersionUID = -82029306361783175L;

//    @ApiModelProperty("主键")
@TableId(type = IdType.AUTO)
    private Integer singerId;

//    @ApiModelProperty("歌手名")
@TableField(value = "singer_name")
    private String singerName;

//    @ApiModelProperty("歌手简介")
@TableField(value = "singer_introduction")
    private String singerIntroduction;

//    @ApiModelProperty("歌手头像的url")
@TableField(value = "singer_head")
    private String singerHead;

}


