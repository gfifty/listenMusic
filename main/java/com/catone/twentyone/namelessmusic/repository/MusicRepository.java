package com.catone.twentyone.namelessmusic.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MusicRepository extends BaseMapper<Music> {

    /**
     * 根据歌曲ID列表获取歌曲信息
     *
     * @param musicIds 歌曲ID列表
     * @return 歌曲列表
     */
    @Select("<script>" +
            "SELECT * FROM music WHERE music_id IN " +
            "<foreach item='id' collection='musicIds' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Music> findAllById(@Param("musicIds") List<Integer> musicIds);
}