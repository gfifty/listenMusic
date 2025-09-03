package com.catone.twentyone.namelessmusic.service;

import com.catone.twentyone.namelessmusic.entity.Album;

import java.util.List;

public interface IAlbumService {
    List<Album> searchAlbum(Album album);

    Album getAlbumThing(Album album);
}
