package com.catone.twentyone.namelessmusic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.catone.twentyone.namelessmusic.entity.Singer;
import com.catone.twentyone.namelessmusic.repository.SingerRepository;
import com.catone.twentyone.namelessmusic.repository.SinglistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerService implements ISingerService{
    @Autowired
    SingerRepository singerRepository;

    @Override
    public List<Singer> searchSinger(Singer singer) {
        QueryWrapper<Singer> wrapper=new QueryWrapper<>();
        if (singer.getSingerName() != null) {
            wrapper.like("singer_name", singer.getSingerName());
        } else {
            throw new IllegalArgumentException("singer_name 参数不能为空");
        }

        return singerRepository.selectList(wrapper);
    }
}
