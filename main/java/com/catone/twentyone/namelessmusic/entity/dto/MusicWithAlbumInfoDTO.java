package com.catone.twentyone.namelessmusic.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicWithAlbumInfoDTO {

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

    @TableField(value = "music_url")
    private String musicUrl;

    private String singerName;
    private String albumName;
    private String cover;
    @TableField(value ="album_introduction")
    private String albumIntroduction;

}
