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
 * @date: 2024-11-20 00:28:41
 * @description: (Singlist_Music)实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@TableName(value = "singlist_music")
//@ApiModel("")
public class Singlist_Music implements Serializable {
    private static final long serialVersionUID = 778602186687044297L;

//    @ApiModelProperty("id（主键）")
    @TableId(type = IdType.AUTO)
    private Integer id;

//    @ApiModelProperty("歌单的id（外键，关联singlist表）")
@TableField(value = "singlist_id")
    private Integer singlistId;

//    @ApiModelProperty("音乐的id（外键，关联music表）")
@TableField(value = "music_id")
    private Integer musicId;

//    @ApiModelProperty("歌曲添加到歌单的时间")
@TableField(value = "add_time")
    private Date addTime;

}


