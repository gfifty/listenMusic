package com.catone.twentyone.namelessmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: makejava
 * @date: 2024-11-20 00:19:22
 * @description: (Album)实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName
public class Album implements Serializable {
    private static final long serialVersionUID = 700820825054151958L;

//    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Integer albumId;

//    @ApiModelProperty("专辑名称")
    @TableField(value = "album_name")
    private String albumName;

//    @ApiModelProperty("专辑创作时间")
    @TableField(value ="album_time")
    private Date albumTime;

//    @ApiModelProperty("专辑下音乐数")
@TableField(value ="album_num")
    private Integer albumNum;

//    @ApiModelProperty("专辑简介")
@TableField(value ="album_introduction")
    private String albumIntroduction;

//    @ApiModelProperty("专辑作者")
@TableField(value ="album_author")
    private String albumAuthor;

//    @ApiModelProperty("专辑封面的url")
@TableField(value ="album_head")
    private String albumHead;

//    @ApiModelProperty("歌手id（外键）")
@TableField(value ="singer_id")
    private Integer singerId;



}


