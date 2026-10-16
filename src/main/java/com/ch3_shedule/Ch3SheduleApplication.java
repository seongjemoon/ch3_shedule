package com.ch3_shedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ch3SheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch3SheduleApplication.class, args);
    }
}