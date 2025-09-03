package com.catone.twentyone.namelessmusic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.catone.twentyone.namelessmusic.entity.*;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithCoverDTO;
import com.catone.twentyone.namelessmusic.entity.dto.SingerNameDTO;
import com.catone.twentyone.namelessmusic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HistoryService implements IHistoryService {
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    MusicRepository musicRepository;
    @Autowired
    SingerRepository singerRepository;
    @Autowired
    AlbumSingleRepository albumSingleRepository;
    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<MusicWithCoverDTO> getmusicHistory(User user) {
        // 获取用户历史记录中的音乐 ID
        List<Integer> musicIds = historyRepository.findMusicIdsByHistory(user.getUserId());

        if (musicIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 获取音乐详细信息
        List<Music> musicList = musicRepository.findAllById(musicIds);

        // 提取歌手 ID
        List<Integer> singerIds = musicList.stream()
                .map(Music::getSingerId)
                .distinct()
                .collect(Collectors.toList());

        // 查询歌手信息
        List<SingerNameDTO> singerList = singerRepository.findSingerNamesByIds(singerIds);
        Map<Integer, String> singerMap = singerList.stream()
                .collect(Collectors.toMap(SingerNameDTO::getSingerId, SingerNameDTO::getSingerName));

        // 查询专辑封面信息
        List<AlbumSingle> albumSingles = albumSingleRepository.selectList(
                new QueryWrapper<AlbumSingle>().in("music_id", musicIds)
        );
        Map<Integer, String> coverMap = albumRepository.selectBatchIds(
                albumSingles.stream()
                        .map(AlbumSingle::getAlbumId)
                        .distinct()
                        .collect(Collectors.toList())
        ).stream().collect(Collectors.toMap(Album::getAlbumId, Album::getAlbumHead));

        // 封装为 DTO 列表
        return musicList.stream().map(m -> {
            MusicWithCoverDTO dto = new MusicWithCoverDTO();
            dto.setMusicId(m.getMusicId());
            dto.setMusicName(m.getMusicName());
            dto.setLanguage(m.getLanguage());
            dto.setMusicType(m.getMusicType());
            dto.setLyric(m.getLyric());
            dto.setMusicDuration(m.getMusicDuration());
            dto.setMusicUrl(m.getMusicUrl());
            dto.setHotTab(m.getHotTab());
            dto.setSingerName(singerMap.getOrDefault(m.getSingerId(), "未知歌手"));
            dto.setCover(coverMap.getOrDefault(m.getMusicId(), null)); // 若封面不存在，可设为 null
            return dto;
        }).collect(Collectors.toList());
    }

//    产生历史记录
    @Override
    public boolean createLog(History history) {
        history.setPlayTime(new java.util.Date());
        int result =historyRepository.insert(history);

        return  result>0;
    }
}
