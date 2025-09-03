package com.catone.twentyone.namelessmusic.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.catone.twentyone.namelessmusic.entity.Singer;
import com.catone.twentyone.namelessmusic.entity.dto.SingerNameDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SingerRepository extends BaseMapper<Singer> {
    @Select("SELECT singer_name FROM singer WHERE singer_id = #{singerId}")
    String findSingerNameById(@Param("singerId") Integer singerId);
//    批量查询歌手姓名
// 批量查询歌手姓名并返回 List<SingerNameDTO>
@Select({
        "<script>",
        "SELECT singer_id, singer_name ",
        "FROM singer ",
        "WHERE singer_id IN ",
        "<foreach collection='singerIds' item='singerId' open='(' separator=',' close=')'>",
        "#{singerId}",
        "</foreach>",
        "</script>"
})
List<SingerNameDTO> findSingerNamesByIds(@Param("singerIds") List<Integer> singerIds);
}
