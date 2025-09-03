package com.catone.twentyone.namelessmusic.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThumbsUpDTO {
    private int userId;
    private int musicId;
}
