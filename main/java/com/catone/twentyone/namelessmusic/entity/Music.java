package com.catone.twentyone.namelessmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * @author: makejava
 * @date: 2024-11-20 00:28:01
 * @description: (Music)实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
//@ApiModel("")
public class Music implements Serializable {
    private static final long serialVersionUID = 486878803775440830L;

//    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Integer musicId;

//    @ApiModelProperty("歌曲名")
@TableField(value = "music_name")
    private String musicName;

//    @ApiModelProperty("语言")
    private String language;

//    @ApiModelProperty("音乐类型")
@TableField(value = "music_type")
    private String musicType;

//    @ApiModelProperty("歌词")
    private String lyric;

//    @ApiModelProperty("音乐时长")
@TableField(value = "music_duration")
    private LocalTime musicDuration;

//    @ApiModelProperty("歌手id")
@TableField(value = "singer_id")
    private Integer singerId;

//    @ApiModelProperty("音源的网址")
@TableField(value = "music_url")
    private String musicUrl;

//    @ApiModelProperty("是否热门的标志（1为hot，0不是热门）")
@TableField(value = "hot_tab")
    private Integer hotTab;

//@TableField(value = "thumbs_up_num")
//    private Integer thumbsUpNum;


}


