package com.catone.twentyone.namelessmusic.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.catone.twentyone.namelessmusic.entity.Attention;
import com.catone.twentyone.namelessmusic.entity.User;

import java.util.List;

public interface IAttentionService{
    List<User> getAttentionList(Attention attention);
}
