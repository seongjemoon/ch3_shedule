package com.ch3_shedule.repository;

import com.ch3_shedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByModifiedAtDesc();
    List<Schedule> findByWriterOrderByModifiedAtDesc(String writer);
}