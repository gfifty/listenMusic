package com.catone.twentyone.namelessmusic.service;

import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithCoverDTO;

import java.util.List;

public interface IMusicService {
    List<MusicWithCoverDTO> searchMusic(Music music);

    List<MusicWithCoverDTO> getHotListv();

    List<MusicWithCoverDTO> getMusicType(Music music);


}
