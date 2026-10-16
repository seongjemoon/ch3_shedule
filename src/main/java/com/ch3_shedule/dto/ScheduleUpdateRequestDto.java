package com.ch3_shedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
    private String title;
    private String writer;
    private String password; // 검증용
}