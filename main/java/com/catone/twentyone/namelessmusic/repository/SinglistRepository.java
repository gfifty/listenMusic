package com.catone.twentyone.namelessmusic.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.catone.twentyone.namelessmusic.entity.Singlist;
import com.catone.twentyone.namelessmusic.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SinglistRepository extends BaseMapper<Singlist> {
    /**
     *  获取热门歌单
     */
    @Select("SELECT * FROM singlist ORDER BY singlist_like DESC LIMIT 9")
    List<Singlist> findHotSinglistBylike();
}