package com.catone.twentyone.namelessmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: makejava
 * @date: 2024-11-20 00:28:33
 * @description: (Singlist)实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Singlist implements Serializable {
    private static final long serialVersionUID = 630580356955147283L;

//    @ApiModelProperty("id（主键）")
    @TableId(type = IdType.AUTO)
    private Integer singlistId;

//    @ApiModelProperty("歌单标题")
@TableField(value = "singlist_name")
@NotEmpty(message = "歌单标题不能为空")
    private String singlistName;

//    @ApiModelProperty("歌单简介")
@TableField(value = "singlist_introduction")
    private String singlistIntroduction;

//    @ApiModelProperty("歌单获赞数")
@TableField(value = "singlist_like")
    private Integer singlistLike;

//    @ApiModelProperty("歌单创建时间")/
@TableField(value = "singlist_time")
    private Date singlistTime;

//    @ApiModelProperty("创建歌单的用户的id（外键）")
@TableField(value = "user_id")
@NotNull(message = "用户id不能为空")
    private Integer userId;

//    @ApiModelProperty("歌单的封面的url")
@TableField(value = "singlist_head")
@NotEmpty(message = "封面不能为空")
    private String singlistHead;

//    @ApiModelProperty("是否热门的标志（1为hot，0不是热门）")
@TableField(value = "hot_tab")
    private Integer hotTab;

}


