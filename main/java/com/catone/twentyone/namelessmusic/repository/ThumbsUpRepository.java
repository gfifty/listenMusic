package com.catone.twentyone.namelessmusic.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.catone.twentyone.namelessmusic.entity.ThumbsUp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ThumbsUpRepository extends BaseMapper<ThumbsUp> {
    @Select("SELECT thumbs.* FROM thumbs WHERE user.user_id = #{user_id} AND music.music_id = #{music_id}")
    ThumbsUp findMusicOne(@Param("user_id") Integer user_id, @Param("music_id") Integer music_id);
}
