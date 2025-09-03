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
 * @date: 2024-12-06 19:04:41
 * @description: (AlbumSingle)实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table

public class AlbumSingle implements Serializable {
    private static final long serialVersionUID = 417231412461713398L;

//    @ApiModelProperty("")
@TableId(type = IdType.AUTO)
    private Integer id;

//    @ApiModelProperty("")
@TableField(value = "music_id")
    private Integer musicId;

//    @ApiModelProperty("")
@TableField(value = "album_id")
    private Integer albumId;

}


