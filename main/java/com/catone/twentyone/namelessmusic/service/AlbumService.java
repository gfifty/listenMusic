package com.catone.twentyone.namelessmusic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.catone.twentyone.namelessmusic.entity.Album;
import com.catone.twentyone.namelessmusic.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService implements IAlbumService{
    @Autowired AlbumRepository albumRepository;
    @Override
    public List<Album> searchAlbum(Album album) {
        QueryWrapper<Album> wrapper = new QueryWrapper<>();
        if (album.getAlbumName() != null) {
            wrapper.like("album_name", album.getAlbumName());
        } else {
            throw new IllegalArgumentException("album_name 参数不能为空");
        }
        return albumRepository.selectList(wrapper);
    }

    @Override
    public Album getAlbumThing(Album album) {
        QueryWrapper wrapper =new QueryWrapper<>();
        wrapper.eq("album_id",album.getAlbumId());
        return albumRepository.selectOne(wrapper);
    }
}
