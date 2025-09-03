package com.catone.twentyone.namelessmusic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.catone.twentyone.namelessmusic.entity.Album;
import com.catone.twentyone.namelessmusic.entity.AlbumSingle;
import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.Singer;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithCoverDTO;
import com.catone.twentyone.namelessmusic.entity.dto.SingerNameDTO;
import com.catone.twentyone.namelessmusic.repository.AlbumRepository;
import com.catone.twentyone.namelessmusic.repository.AlbumSingleRepository;
import com.catone.twentyone.namelessmusic.repository.MusicRepository;
import com.catone.twentyone.namelessmusic.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MusicService implements IMusicService{
    @Autowired
    MusicRepository musicRepository;
    @Autowired
    SingerRepository singerRepository;
    @Autowired
    AlbumSingleRepository albumSingleRepository;
    @Autowired
    AlbumRepository albumRepository;
//    搜索音乐
@Override
public List<MusicWithCoverDTO> searchMusic(Music music) {
    // 校验输入参数
    String musicName = Optional.ofNullable(music.getMusicName())
            .filter(name -> !name.trim().isEmpty())
            .orElseThrow(() -> new IllegalArgumentException("music_name 参数不能为空"));

    // 构造查询条件
    QueryWrapper<Music> wrapper = new QueryWrapper<>();
    wrapper.like("music_name", musicName);

    // 查询符合条件的音乐列表
    List<Music> musicList = musicRepository.selectList(wrapper);

    // 如果没有符合条件的音乐，直接返回空列表
    if (musicList.isEmpty()) {
        return Collections.emptyList();
    }

    // 获取所有歌曲的 ID
    List<Integer> musicIds = musicList.stream()
            .map(Music::getMusicId)
            .collect(Collectors.toList());

    // 根据歌曲ID列表批量查询 album_single 中的专辑ID
    List<Integer> albumIds = albumSingleRepository.findAlbumIdsByMusicIds(musicIds);

    // 根据专辑ID列表批量查询专辑信息（包括封面）
    List<Album> albums = albumRepository.findAlbumsByIds(albumIds);

    // 将专辑信息存储到 Map 中以便快速查找
    Map<Integer, Album> albumMap = albums.stream()
            .collect(Collectors.toMap(Album::getAlbumId, album -> album));

    // 获取所有歌手的 ID
    List<Integer> singerIds = musicList.stream()
            .map(Music::getSingerId)
            .distinct()
            .collect(Collectors.toList());

    // 批量查询歌手姓名并返回 List<SingerNameDTO>
    List<SingerNameDTO> singerNames = singerRepository.findSingerNamesByIds(singerIds);

    // 将 List<SingerNameDTO> 转换为 Map<Integer, String> 以便快速查找
    Map<Integer, String> singerIdToNameMap = singerNames.stream()
            .collect(Collectors.toMap(SingerNameDTO::getSingerId, SingerNameDTO::getSingerName));

    // 构造结果 DTO 列表
    List<MusicWithCoverDTO> result = new ArrayList<>();
    for (Music m : musicList) {
        // 根据歌曲ID查找对应的专辑ID
        Integer albumId = albumSingleRepository.findAlbumIdByMusicId(m.getMusicId());

        // 获取该专辑的封面信息
        Album album = albumMap.get(albumId);

        if (album != null) {
            // 将歌曲信息和封面信息封装成 DTO
            MusicWithCoverDTO musicWithCoverDTO = new MusicWithCoverDTO();
            musicWithCoverDTO.setMusicId(m.getMusicId());
            musicWithCoverDTO.setMusicName(m.getMusicName());
            musicWithCoverDTO.setLanguage(m.getLanguage());
            musicWithCoverDTO.setMusicType(m.getMusicType());
            musicWithCoverDTO.setLyric(m.getLyric());
            musicWithCoverDTO.setMusicDuration(m.getMusicDuration());
            musicWithCoverDTO.setSingerId(m.getSingerId());
            musicWithCoverDTO.setMusicUrl(m.getMusicUrl());
            musicWithCoverDTO.setHotTab(m.getHotTab());
            musicWithCoverDTO.setCover(album.getAlbumHead()); // 设置专辑封面

            // 查询歌手姓名
            String singerName = singerIdToNameMap.get(m.getSingerId());
            musicWithCoverDTO.setSingerName(singerName);

            result.add(musicWithCoverDTO);
        }
    }
    // 返回包含歌曲信息和封面信息的 DTO 列表
    return result;
}

//获取热门歌曲
    @Override
    public List<MusicWithCoverDTO> getHotListv() {
        // 查询所有 hotTab = 1 的音乐
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.eq("hot_tab", 1);
        List<Music> musicList = musicRepository.selectList(wrapper);

        if (musicList.isEmpty()) {
            return Collections.emptyList();
        }

        // 提取所有歌手 ID 和音乐 ID
        List<Integer> singerIds = musicList.stream()
                .map(Music::getSingerId)
                .distinct()
                .collect(Collectors.toList());

        List<Integer> musicIds = musicList.stream()
                .map(Music::getMusicId)
                .collect(Collectors.toList());

        // 根据歌手 ID 获取歌手姓名列表（List<SingerNameDTO>）
        List<SingerNameDTO> singerList = singerRepository.findSingerNamesByIds(singerIds);
        // 将 List<SingerNameDTO> 转换为 Map<Integer, String>
        Map<Integer, String> singerMap = singerList.stream()
                .collect(Collectors.toMap(SingerNameDTO::getSingerId, SingerNameDTO::getSingerName));

        // 根据音乐 ID 获取专辑封面信息
        List<AlbumSingle> albumSingles = albumSingleRepository.selectList(
                new QueryWrapper<AlbumSingle>().in("music_id", musicIds)
        );

        // 获取专辑封面列表并转换为 Map<Integer, String>
        Map<Integer, String> coverMap = albumRepository.selectBatchIds(
                albumSingles.stream()
                        .map(AlbumSingle::getAlbumId)
                        .distinct()
                        .collect(Collectors.toList())
        ).stream().collect(Collectors.toMap(Album::getAlbumId, Album::getAlbumHead));

        // 将音乐列表转换为 DTO 列表
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
            dto.setCover(coverMap.getOrDefault(m.getMusicId(), null)); // 若封面信息不需要，可设为 null
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<MusicWithCoverDTO> getMusicType(Music music) {
        // 构建查询条件
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        if (music.getMusicType() != null) {
            wrapper.eq("music_type", music.getMusicType());
        } else {
            throw new IllegalArgumentException("music_type 参数不能为空");
        }

        // 查询符合条件的音乐列表
        List<Music> musicList = musicRepository.selectList(wrapper);

        if (musicList.isEmpty()) {
            return Collections.emptyList();
        }

        // 提取所有歌手 ID 和音乐 ID
        List<Integer> singerIds = musicList.stream()
                .map(Music::getSingerId)
                .distinct()
                .collect(Collectors.toList());

        List<Integer> musicIds = musicList.stream()
                .map(Music::getMusicId)
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

        // 将音乐列表转换为 DTO 列表
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
            dto.setCover(coverMap.getOrDefault(m.getMusicId(), null)); // 若封面信息不需要，可设为 null
            return dto;
        }).collect(Collectors.toList());
    }

}
