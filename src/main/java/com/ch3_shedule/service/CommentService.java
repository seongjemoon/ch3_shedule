package com.ch3_shedule.service;

import com.ch3_shedule.dto.CommentRequestDto;
import com.ch3_shedule.dto.CommentResponseDto;
import com.ch3_shedule.entity.Comment;
import com.ch3_shedule.entity.Schedule;
import com.ch3_shedule.repository.CommentRepository;
import com.ch3_shedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto) {
        // 1. 해당 일정이 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다. id=" + scheduleId));

        // 2. 하나의 일정에는 댓글을 최대 10개까지만 작성 가능하도록 제한
        long commentCount = commentRepository.countByScheduleId(scheduleId);
        if (commentCount >= 10) {
            throw new IllegalStateException("하나의 일정에는 댓글을 최대 10개까지만 작성할 수 있습니다.");
        }

        // 3. 댓글 저장
        Comment comment = new Comment(
                requestDto.getContent(),
                requestDto.getWriter(),
                requestDto.getPassword(),
                schedule
        );
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }
}