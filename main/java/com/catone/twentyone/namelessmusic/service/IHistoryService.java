package com.catone.twentyone.namelessmusic.service;

import com.catone.twentyone.namelessmusic.entity.History;
import com.catone.twentyone.namelessmusic.entity.Music;
import com.catone.twentyone.namelessmusic.entity.User;
import com.catone.twentyone.namelessmusic.entity.dto.MusicWithCoverDTO;

import java.util.List;

public interface IHistoryService {

    List<MusicWithCoverDTO> getmusicHistory(User user);

    boolean createLog(History history);
}
