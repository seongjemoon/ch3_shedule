package com.ch3_shedule.service;

import com.ch3_shedule.dto.ScheduleDeleteRequestDto;
import com.ch3_shedule.dto.ScheduleResponseDto;
import com.ch3_shedule.entity.Schedule;
import com.ch3_shedule.repository.ScheduleRepository;
import com.ch3_shedule.dto.ScheduleRequestDto;
import com.ch3_shedule.dto.ScheduleUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 1. 일정 등록
    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getWriter(),
                requestDto.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule);
    }

    // 2. 전체 일정 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getSchedules(String writer) {
        List<Schedule> schedules;

        if (writer != null && !writer.trim().isEmpty()) {
            schedules = scheduleRepository.findByWriterOrderByModifiedAtDesc(writer);
        } else {
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        }

        return schedules.stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    // 3. 선택 일정 단건 조회
    @Transactional(readOnly = true)
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다. id=" + id));
        return new ScheduleResponseDto(schedule);
    }

    // 4. 선택 일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다. id=" + id));

        schedule.update(requestDto.getTitle(), requestDto.getWriter(), requestDto.getPassword());

        return new ScheduleResponseDto(schedule);
    }
    // 5. 일정 삭제
    @Transactional
    public Long deleteSchedule(Long id, ScheduleDeleteRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다. id=" + id));

        schedule.validatePassword(requestDto.getPassword());

        scheduleRepository.delete(schedule);
        return id;
    }
}