package com.catone.twentyone.namelessmusic.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicWithCoverDTO {
    @TableId(type = IdType.AUTO)
    private Integer musicId;

    @TableField(value = "music_name")
    private String musicName;

    private String language;

    @TableField(value = "music_type")
    private String musicType;

    private String lyric;

    @TableField(value = "music_duration")
    private LocalTime musicDuration;

    @TableField(value = "singer_id")
    private Integer singerId;

    @TableField(value = "music_url")
    private String musicUrl;

    @TableField(value = "hot_tab")
    private Integer hotTab;


    private String singerName; // 新增歌手姓名字段

    private String cover; // 封面信息
}

