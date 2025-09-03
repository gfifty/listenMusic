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
 * @date: 2024-11-20 00:26:56
 * @description: (Comment)实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table

public class Comment implements Serializable {
    private static final long serialVersionUID = 558419481078306937L;

//    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Integer commentId;

//    @ApiModelProperty("音乐的id")
@TableField(value = "music_id")
    private Integer musicId;

//    @ApiModelProperty("评论用户的id")
@TableField(value = "user_id")
    private Integer userId;

//    @ApiModelProperty("评论长度")
    private String content;

//    @ApiModelProperty("点赞总量")
@TableField(value = "give_like")
    private Integer giveLike;

//    @ApiModelProperty("评论时间")
@TableField(value = "create_time")
    private Date createTime;

//    @ApiModelProperty("评论状态(0为正常，1为删除)")
    private String state;

//    @ApiModelProperty("回复评论用户的id（外键0），关联user表")
@TableField(value = "replyuser_id")
    private Integer replyuserId;

}


