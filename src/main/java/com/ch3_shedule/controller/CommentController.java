package com.ch3_shedule.controller;

import com.ch3_shedule.dto.CommentRequestDto;
import com.ch3_shedule.dto.CommentResponseDto;
import com.ch3_shedule.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

        @PostMapping("/{scheduleId}/comments")
    public CommentResponseDto createComment(
            @PathVariable Long scheduleId,
            @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(scheduleId, requestDto);
    }
}