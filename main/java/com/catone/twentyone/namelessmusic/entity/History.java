package com.catone.twentyone.namelessmusic.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: makejava
 * @date: 2024-11-20 00:27:44
 * @description: (History)实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class History implements Serializable {
    private static final long serialVersionUID = -56760580591538919L;

//    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

//    @ApiModelProperty("用户id（外键），连接user表")
@TableField(value = "user_id")
    private Integer userId;

//    @ApiModelProperty("记录的类型")
@TableField(value = "record_type")
    private Integer recordType;

//    @ApiModelProperty("指向id（外键），用于指向对应的歌曲，专辑，歌单")
@TableField(value = "record_id")
    private Integer recordId;

//    @ApiModelProperty("记录播放的时间")
@TableField(value = "play_time")
    private Date playTime;

}


