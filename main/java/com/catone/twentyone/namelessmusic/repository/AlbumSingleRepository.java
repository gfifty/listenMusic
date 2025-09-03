package com.catone.twentyone.namelessmusic.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.catone.twentyone.namelessmusic.entity.AlbumSingle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AlbumSingleRepository extends BaseMapper<AlbumSingle> {
    /**
     * 根据获取到的专辑id列获取对应的歌曲ID
     */
    @Select("SELECT music_id FROM album_single WHERE album_id = #{albumId}")
    List<Integer> findMusicIdByAlbumId(@Param("albumId") Integer albumId);
    // 根据 music_id 获取对应的 album_id
    @Select("SELECT album_id FROM album_single WHERE music_id = #{musicId}")
    Integer findAlbumIdByMusicId(@Param("musicId") Integer musicId);
    // 批量查询 album_id
    @Select("<script>" +
            "SELECT album_id FROM album_single WHERE music_id IN " +
            "<foreach collection='musicIds' item='musicId' open='(' separator=',' close=')'>" +
            "#{musicId}" +
            "</foreach>" +
            "</script>")
    List<Integer> findAlbumIdsByMusicIds(@Param("musicIds") List<Integer> musicIds);
//    添加批量查询封面
@Select({
        "<script>",
        "SELECT music_id, a.album_head AS cover ",
        "FROM album_single als ",
        "JOIN album a ON als.album_id = a.album_id ",
        "WHERE als.music_id IN ",
        "<foreach collection='musicIds' item='musicId' open='(' separator=',' close=')'>",
        "#{musicId}",
        "</foreach>",
        "</script>"
})
Map<Integer, String> findAlbumCoversByMusicIds(@Param("musicIds") List<Integer> musicIds);
}

