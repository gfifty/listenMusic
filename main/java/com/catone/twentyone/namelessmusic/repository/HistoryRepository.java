package com.catone.twentyone.namelessmusic.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.catone.twentyone.namelessmusic.entity.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HistoryRepository extends BaseMapper<History> {
//    根据用户id获取历史记录里的所有歌曲id
@Select("SELECT history.record_id AS music_id FROM history WHERE history.record_type = 1 AND history.user_id = #{userId}")
    List<Integer> findMusicIdsByHistory(@Param("userId") Integer userId);
}
