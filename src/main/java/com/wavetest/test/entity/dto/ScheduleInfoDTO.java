package com.wavetest.test.entity.dto;

import com.wavetest.test.entity.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleInfoDTO {

//    private Long room_id;
    private String roomName;
    private String themePresentation;
    private LocalDateTime timeBegin;
    private LocalDateTime timeEnd;
}
