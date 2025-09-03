package com.catone.twentyone.namelessmusic.service;

import com.catone.twentyone.namelessmusic.entity.ThumbsUp;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithCoverDTO;
import com.catone.twentyone.namelessmusic.entity.dto.ThumbsUpDTO;

import java.util.List;

public interface IThumbsUpService {

    ThumbsUp giveThumbsUp(ThumbsUpDTO thumbs);
}
