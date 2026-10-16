package com.ch3_shedule.controller;

import com.ch3_shedule.dto.ScheduleDeleteRequestDto;
import com.ch3_shedule.dto.ScheduleRequestDto;
import com.ch3_shedule.dto.ScheduleResponseDto;
import com.ch3_shedule.dto.ScheduleUpdateRequestDto;
import com.ch3_shedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시글 어노테이션을 활용하여 CRUD 맵핑하기
 */
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //1. 게시글 생성
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }
    //2. 게시글 전체 조회
    @GetMapping
    public List<ScheduleResponseDto> getSchedules(@RequestParam(required = false) String writer) {
        return scheduleService.getSchedules(writer);
    }
    //3. 게시글 단건 조회
    @GetMapping("/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }
    //4. 게시글 수정
    @PutMapping("/{id}")
    public ScheduleResponseDto updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }
    //5. 게시글 삭제
    @DeleteMapping("/{id}")
    public Long deleteSchedule(@PathVariable Long id, @RequestBody ScheduleDeleteRequestDto requestDto) {
        return scheduleService.deleteSchedule(id, requestDto);
    }

}