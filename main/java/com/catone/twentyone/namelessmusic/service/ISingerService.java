package com.catone.twentyone.namelessmusic.service;

import com.catone.twentyone.namelessmusic.entity.Singer;

import java.util.List;

public interface ISingerService {
    List<Singer> searchSinger(Singer singer);
}
