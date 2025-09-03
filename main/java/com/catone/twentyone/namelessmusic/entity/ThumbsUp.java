package com.catone.twentyone.namelessmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@TableName(value = "thumbs")
public class ThumbsUp implements Serializable {
    private static final long serialVersionUID = 778602186687044297L;

    @TableId(type = IdType.AUTO)
    private Integer thumbsUpId;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "music_id")
    private Integer musicId;

}
