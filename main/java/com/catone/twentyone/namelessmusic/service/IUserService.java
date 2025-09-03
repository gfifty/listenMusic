package com.catone.twentyone.namelessmusic.service;

import com.catone.twentyone.namelessmusic.entity.User;
import com.catone.twentyone.namelessmusic.entity.dto.UserDTO;

public interface IUserService {

    User add(UserDTO user);

    User login(UserDTO user);

    User updateAddress(UserDTO user);

    User updateSex(UserDTO user);

    User updateBirthday(UserDTO user);
}
