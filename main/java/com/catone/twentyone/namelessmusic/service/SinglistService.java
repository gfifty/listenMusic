package com.catone.twentyone.namelessmusic.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.catone.twentyone.namelessmusic.entity.Singlist;
import com.catone.twentyone.namelessmusic.repository.SingerRepository;
import com.catone.twentyone.namelessmusic.repository.SinglistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SinglistService implements ISinglistService{
    @Autowired
    SinglistRepository singlistRepository;;

//获取歌单的内容
    @Override
    public Singlist getSinglist(Singlist singlist) {
        QueryWrapper<Singlist> wrapper=new QueryWrapper<>();
        wrapper.eq("singlist_id",singlist.getSinglistId());
        return singlistRepository.selectOne(wrapper);
    }

//    搜索歌单
    @Override
    public List<Singlist> secrchSinglist(Singlist singlist) {
        QueryWrapper<Singlist> wrapper =new QueryWrapper<>();
        if (singlist.getSinglistName() != null) {
            wrapper.like("singlist_name", singlist.getSinglistName());
        } else {
            throw new IllegalArgumentException("singlist_name 参数不能为空");
        }
        return singlistRepository.selectList(wrapper);
    }
//    创建歌单
    @Override
    public boolean createSinglist(Singlist singlist) {
//        设置默认字段
        singlist.setSinglistTime(new java.util.Date());
        singlist.setSinglistLike(0);

        int result =singlistRepository.insert(singlist);

        return  result>0;
    }
//获取热门歌单
    @Override
    public List<Singlist> getHotList() {
        return singlistRepository.findHotSinglistBylike();
    }

//    获取自己的歌单
    @Override
    public List<Singlist> getMyList(Singlist singlist) {
        QueryWrapper<Singlist> wrapper =new QueryWrapper<>();
        if (singlist.getUserId() != null) {
            wrapper.eq("user_id", singlist.getUserId());
        } else {
            throw new IllegalArgumentException("用户id 参数不能为空");
        }
        return singlistRepository.selectList(wrapper);
    }
}
