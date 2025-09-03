package com.catone.twentyone.namelessmusic.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.catone.twentyone.namelessmusic.entity.Album;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AlbumRepository extends BaseMapper<Album> {
    // 根据专辑ID查询专辑名称
    @Select("SELECT album_name FROM album WHERE album_id = #{albumId}")
    String findAlbumNameById(Integer albumId);

    // 根据专辑ID查询专辑封面
    @Select("SELECT album_head FROM album WHERE album_id = #{albumId}")
    String findAlbumCoverById(Integer albumId);
    @Select("SELECT album_introduction FROM album WHERE album_id = #{albumId}")
    String findAlbumDescriptionById(Integer albumId);  // 查询专辑简介
    // 根据专辑ID列表批量查询专辑信息
    @Select("<script>" +
            "SELECT * FROM album WHERE album_id IN " +
            "<foreach collection='albumIds' item='albumId' open='(' separator=',' close=')'>" +
            "#{albumId}" +
            "</foreach>" +
            "</script>")
    List<Album> findAlbumsByIds(@Param("albumIds") List<Integer> albumIds);
}
