package com.wavetest.test.entity.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private String themePresentation;
    private LocalDateTime timeBegin;
    private LocalDateTime timeEnd;
}
