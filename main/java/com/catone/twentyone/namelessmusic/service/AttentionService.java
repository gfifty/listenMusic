package com.catone.twentyone.namelessmusic.service;

import com.catone.twentyone.namelessmusic.entity.Attention;
import com.catone.twentyone.namelessmusic.entity.User;
import com.catone.twentyone.namelessmusic.repository.AlbumRepository;
import com.catone.twentyone.namelessmusic.repository.AttentionRepository;
import com.catone.twentyone.namelessmusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttentionService implements IAttentionService{
    @Autowired
    AttentionRepository attentionRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> getAttentionList(Attention attention) {
        List<Integer> userIds=attentionRepository.getUseridByAttention(attention.getUserId());
        return userRepository.findAllById(userIds);
    }
}
