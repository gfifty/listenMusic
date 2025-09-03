package com.catone.twentyone.namelessmusic.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.catone.twentyone.namelessmusic.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentRepository extends BaseMapper<Comment> {
}
