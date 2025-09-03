package com.catone.twentyone.namelessmusic.service;

import com.catone.twentyone.namelessmusic.entity.AlbumSingle;
import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithAlbumInfoDTO;

import java.util.List;

public interface IAlbumSingleService {
    List<MusicWithAlbumInfoDTO> getAlbumSingle(AlbumSingle albumSingle);
}
