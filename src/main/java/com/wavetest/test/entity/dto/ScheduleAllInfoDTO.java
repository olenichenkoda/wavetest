package com.wavetest.test.entity.dto;

import com.wavetest.test.entity.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleAllInfoDTO {

//    private Long room_id;
//    private Long user_id;
//    private Role roleOnPresentation;
    private List<ParticipantDTO> participants;
    private String roomName;
    private String themePresentation;
    private LocalDateTime timeBegin;
    private LocalDateTime timeEnd;
}
