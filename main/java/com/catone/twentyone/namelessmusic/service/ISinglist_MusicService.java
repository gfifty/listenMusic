package com.catone.twentyone.namelessmusic.service;
import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.Singlist;
import com.catone.twentyone.namelessmusic.entity.Singlist_Music;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithCoverDTO;
import com.catone.twentyone.namelessmusic.service.util.service.util.FwResult;

import java.util.List;

public interface ISinglist_MusicService {
    List<MusicWithCoverDTO> getSinglistWithMusic(Singlist singlist);

    Boolean addMusicToList(Singlist_Music singlist_music);

    Singlist getSinglistThing(Singlist singlist);
}
