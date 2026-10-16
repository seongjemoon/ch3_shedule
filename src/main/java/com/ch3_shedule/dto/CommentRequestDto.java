package com.ch3_shedule.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;
    private String writer;
    private String password;
}