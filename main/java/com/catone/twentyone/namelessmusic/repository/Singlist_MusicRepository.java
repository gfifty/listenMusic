package com.catone.twentyone.namelessmusic.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.catone.twentyone.namelessmusic.entity.Singlist_Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Singlist_MusicRepository extends BaseMapper<Singlist_Music> {

    /**
     * 根据歌单ID获取该歌单中的所有歌曲ID
     *
     * @param singlistId 歌单ID
     * @return 歌曲ID列表
     */
    @Select("SELECT music_id FROM singlist_music WHERE singlist_id = #{singlistId}")
    List<Integer> findMusicIdsBySinglistId(@Param("singlistId") Integer singlistId);
}