package com.catone.twentyone.namelessmusic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.catone.twentyone.namelessmusic.entity.Album;
import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.Singlist;
import com.catone.twentyone.namelessmusic.entity.Singlist_Music;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithCoverDTO;
import com.catone.twentyone.namelessmusic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class Singlist_MusicService implements ISinglist_MusicService {

    @Autowired
    private SinglistRepository singlistRepository;
    @Autowired
    private Singlist_MusicRepository singlistMusicRepository;
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private AlbumSingleRepository albumSingleRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private SingerRepository singerRepository;


    //    获取歌单下的歌曲
    @Override
    public List<MusicWithCoverDTO> getSinglistWithMusic(Singlist singlist) {
        // 根据歌单ID获取歌单中的歌曲ID列表
        List<Integer> musicIds = singlistMusicRepository.findMusicIdsBySinglistId(singlist.getSinglistId());

        // 根据歌曲ID列表批量查询 album_single 中的专辑ID
        List<Integer> albumIds = albumSingleRepository.findAlbumIdsByMusicIds(musicIds);

        // 根据专辑ID列表批量查询专辑信息（包括封面）
        List<Album> albums = albumRepository.findAlbumsByIds(albumIds);

        // 查询所有歌曲信息
        List<Music> musics = musicRepository.findAllById(musicIds);

        // 将歌曲信息和封面信息进行合并
        Map<Integer, Album> albumMap = albums.stream()
                .collect(Collectors.toMap(Album::getAlbumId, album -> album));

        List<MusicWithCoverDTO> result = new ArrayList<>();
        for (Music music : musics) {
            // 根据歌曲ID查找对应的专辑ID
            Integer albumId = albumSingleRepository.findAlbumIdByMusicId(music.getMusicId());

            // 获取该专辑的封面信息
            Album album = albumMap.get(albumId);

            if (album != null) {
                // 将歌曲信息和封面信息封装成 DTO
                MusicWithCoverDTO musicWithCoverDTO = new MusicWithCoverDTO();
                musicWithCoverDTO.setMusicId(music.getMusicId());
                musicWithCoverDTO.setMusicName(music.getMusicName());
                musicWithCoverDTO.setLanguage(music.getLanguage());
                musicWithCoverDTO.setMusicType(music.getMusicType());
                musicWithCoverDTO.setLyric(music.getLyric());
                musicWithCoverDTO.setMusicDuration(music.getMusicDuration());
                musicWithCoverDTO.setSingerId(music.getSingerId());
                musicWithCoverDTO.setMusicUrl(music.getMusicUrl());
                musicWithCoverDTO.setHotTab(music.getHotTab());
                // 查询歌手姓名
                String singerName = singerRepository.findSingerNameById(music.getSingerId());
                musicWithCoverDTO.setSingerName(singerName);
                musicWithCoverDTO.setCover(album.getAlbumHead()); // 封面信息

                result.add(musicWithCoverDTO);
            }
        }

        // 返回包含歌曲信息和封面信息的 DTO 列表
        return result;
    }


    //添加歌曲到歌单
@Override
public Boolean addMusicToList(Singlist_Music singlist_music) {
    // 创建 QueryWrapper 进行查询
    QueryWrapper<Singlist_Music> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("singlist_id", singlist_music.getSinglistId())
            .eq("music_id", singlist_music.getMusicId());

    // 检查歌单中是否已经有该歌曲
    Singlist_Music existing = singlistMusicRepository.selectOne(queryWrapper);

    if (existing != null) {
        // 歌单中已经包含该歌曲，返回 false
        return false;
    }

    // 创建一个新的 Singlist_Music 对象
    Singlist_Music newsinglistMusic = new Singlist_Music();
    newsinglistMusic.setSinglistId(singlist_music.getSinglistId());
    newsinglistMusic.setMusicId(singlist_music.getMusicId());
    newsinglistMusic.setAddTime(new java.util.Date()); // 假设有一个 addTime 字段

    // 执行插入操作
    int res = singlistMusicRepository.insert(newsinglistMusic);

    // 判断插入是否成功，返回 true 或 false
    return res > 0;
}

//获取歌单信息
    @Override
    public Singlist getSinglistThing(Singlist singlist) {
        QueryWrapper wrapper=new QueryWrapper<>();
        wrapper.eq("singlist_id",singlist.getSinglistId());
        return singlistRepository.selectOne(wrapper);
    }


}