package com.catone.twentyone.namelessmusic.service;

import com.catone.twentyone.namelessmusic.entity.AlbumSingle;
import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithAlbumInfoDTO;
import com.catone.twentyone.namelessmusic.entity.dto.SingerNameDTO;
import com.catone.twentyone.namelessmusic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AlbumSingleService implements IAlbumSingleService{
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumSingleRepository albumSingleRepository;
    @Autowired
    private SingerRepository singerRepository;
    /**
     *
     *获取专辑中的歌曲
     * @param albumSingle
     * @return
     */
    @Override
    public List<MusicWithAlbumInfoDTO> getAlbumSingle(AlbumSingle albumSingle) {
        // 根据专辑ID获取专辑中的音乐ID列表
        List<Integer> musicIds = albumSingleRepository.findMusicIdByAlbumId(albumSingle.getAlbumId());

        // 根据音乐ID列表获取所有音乐信息
        List<Music> musicList = musicRepository.findAllById(musicIds);

        if (musicList.isEmpty()) {
            return Collections.emptyList();
        }

        // 获取专辑封面和专辑名
        String albumName = albumRepository.findAlbumNameById(albumSingle.getAlbumId());
        String albumCover = albumRepository.findAlbumCoverById(albumSingle.getAlbumId());
        String albumDescription = albumRepository.findAlbumDescriptionById(albumSingle.getAlbumId()); // 新增专辑简介

        // 提取所有歌手ID
        List<Integer> singerIds = musicList.stream()
                .map(Music::getSingerId)
                .distinct()
                .collect(Collectors.toList());

        // 批量查询所有歌手信息（姓名）
        List<SingerNameDTO> singerNameDTOList = singerRepository.findSingerNamesByIds(singerIds);
        // 将歌手ID与歌手姓名映射为 Map
        Map<Integer, String> singerMap = singerNameDTOList.stream()
                .collect(Collectors.toMap(SingerNameDTO::getSingerId, SingerNameDTO::getSingerName));

        // 转换为 DTO 列表，填充歌手姓名、专辑名、封面等信息
        return musicList.stream().map(m -> {
            MusicWithAlbumInfoDTO dto = new MusicWithAlbumInfoDTO();
            dto.setMusicId(m.getMusicId());
            dto.setMusicName(m.getMusicName());
            dto.setLanguage(m.getLanguage());
            dto.setMusicType(m.getMusicType());
            dto.setLyric(m.getLyric());
            dto.setMusicDuration(m.getMusicDuration());
            dto.setMusicUrl(m.getMusicUrl());
            dto.setSingerName(singerMap.getOrDefault(m.getSingerId(), "未知歌手"));
            dto.setAlbumName(albumName);
            dto.setCover(albumCover);
            dto.setAlbumIntroduction(albumDescription);
            return dto;
        }).collect(Collectors.toList());
    }
}

