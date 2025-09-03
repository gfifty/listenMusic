package com.catone.twentyone.namelessmusic.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.catone.twentyone.namelessmusic.entity.Attention;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AttentionRepository extends BaseMapper<Attention> {
    @Select("SELECT * FROM attention where user_id=#{userId}")
    List<Integer> getUseridByAttention(@Param("userId") Integer userId);
}
