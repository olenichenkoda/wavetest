package com.wavetest.test.entity.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private String roomName;
    private List<ScheduleDTO> schedules;
}
