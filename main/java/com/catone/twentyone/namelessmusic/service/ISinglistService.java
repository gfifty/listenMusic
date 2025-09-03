package com.catone.twentyone.namelessmusic.service;

import com.catone.twentyone.namelessmusic.entity.Singlist;

import java.util.List;

public interface ISinglistService {
    Singlist getSinglist(Singlist singlist) ;

    List<Singlist> secrchSinglist(Singlist singlist);

    boolean createSinglist(Singlist singlist);

    List<Singlist> getHotList();

    //    查看自己的歌单
    List<Singlist> getMyList(Singlist singlist);
}
