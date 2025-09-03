package com.catone.twentyone.namelessmusic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.ThumbsUp;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithCoverDTO;
import com.catone.twentyone.namelessmusic.entity.dto.ThumbsUpDTO;
import com.catone.twentyone.namelessmusic.repository.ThumbsUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThumbsUpService implements IThumbsUpService{
    @Autowired
    ThumbsUpRepository thumbsUpRepository;
    //实现用户点赞歌曲操作
    @Override
    public ThumbsUp giveThumbsUp(ThumbsUpDTO thumbs) {
        if (thumbs.getUserId() != 0 && thumbs.getMusicId() != 0) {
            ThumbsUp Music= thumbsUpRepository.findMusicOne(thumbs.getUserId(),thumbs.getMusicId());
            return Music;
        }else {
            throw new IllegalArgumentException("thumbsUp参数错误");
        }
    }


}
