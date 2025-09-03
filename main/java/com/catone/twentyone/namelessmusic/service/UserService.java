package com.catone.twentyone.namelessmusic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.catone.twentyone.namelessmusic.entity.Singlist;
import com.catone.twentyone.namelessmusic.entity.User;
import com.catone.twentyone.namelessmusic.entity.dto.UserDTO;
import com.catone.twentyone.namelessmusic.repository.SingerRepository;
import com.catone.twentyone.namelessmusic.repository.SinglistRepository;
import com.catone.twentyone.namelessmusic.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;
    SinglistRepository singlistRepository;

    @Override
    public User add(UserDTO userDTO) {
        // 判断用户名是否已存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userDTO.getUsername());  // 根据用户名查询是否存在

        User existingUser = userRepository.selectOne(wrapper); // 查找已存在的用户

        if (existingUser != null) {
            throw new RuntimeException("用户名已存在"); // 如果用户名已存在，抛出异常
        }

        // 用户名不存在，可以进行注册
        Singlist newsinglist=new Singlist();
        User newUser = new User();
        BeanUtils.copyProperties(userDTO, newUser);  // 将 UserDTO 的属性复制到新创建的 User 实体

        // 设置默认的用户头像
        newUser.setUserHead("https://s2.loli.net/2024/11/17/Ch7UN9cozX4xwHv.webp");

        // 设置当前时间为创建时间（或最近一次登录时间）
        newUser.setLaterTime(new Date());  // 设置用户创建时间（或最近登录时间）

        //创建用户专属得“我喜欢”歌单
        newsinglist.setSinglistId(1715+newUser.getUserId());
        newsinglist.setSinglistName(newUser.getUsername()+"的喜欢");
        newsinglist.setSinglistTime(new Date());
        newsinglist.setSinglistHead("https://s2.loli.net/2025/06/17/Tg6k9ShyGcJFVMq.png");
        newsinglist.setSinglistLike(0);

        // 保存新用户到数据库
        userRepository.insert(newUser);
        singlistRepository.insert(newsinglist);
        return newUser;  // 返回注册成功的用户信息
    }

    @Override
    public User login(UserDTO userDTO) {
        // 根据用户名查询用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userDTO.getUsername());  // 根据用户名查询

        User user = userRepository.selectOne(wrapper); // 使用 MyBatis-Plus 提供的查询方法

        // 判断用户是否存在
        if (user == null) {
            throw new RuntimeException("用户名不存在"); // 如果用户名不存在，抛出异常
        }

        // 校验密码是否正确
        if (!user.getPassword().equals(userDTO.getPassword())) {
            throw new RuntimeException("密码错误"); // 如果密码不匹配，抛出异常
        }

        // 登录成功，更新最近一次登录时间
        user.setLaterTime(new Date());  // 设置当前时间为最后登录时间
        userRepository.updateById(user);  // 更新数据库中的登录时间

        return user;  // 返回登录成功的用户信息
    }

    @Override
    public User updateAddress(UserDTO userDTO) {
        // 根据 userId 查找用户
        User existingUser = userRepository.selectById(userDTO.getUserId());

        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }

        // 更新用户地址
        existingUser.setAddress(userDTO.getAddress());
        // 更新数据库中的地址信息
        userRepository.updateById(existingUser);

        return existingUser; // 返回更新后的用户信息
    }

    @Override
    public User updateSex(UserDTO userDTO) {
        // 根据 userId 查找用户
        User existingUser = userRepository.selectById(userDTO.getUserId());

        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }

        // 更新用户性别
        existingUser.setSex(userDTO.getSex());
        // 更新数据库中的性别信息
        userRepository.updateById(existingUser);
        return existingUser; // 返回更新后的用户信息
    }

    @Override
    public User updateBirthday(UserDTO userDTO) {
        // 根据 userId 查找用户
        User existingUser = userRepository.selectById(userDTO.getUserId());
        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }

        // 更新用户生日
        existingUser.setBirthday(userDTO.getBirthday());
        // 更新数据库中的生日信息
        userRepository.updateById(existingUser);

        return existingUser; // 返回更新后的用户信息
    }

}
