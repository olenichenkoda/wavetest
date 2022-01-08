package com.wavetest.test.controller;

import com.wavetest.test.entity.dto.RoomDTO;
import com.wavetest.test.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public List<RoomDTO> getAllScheduleInfoDTO(){
        return scheduleService.getAllScheduleInfo();
    }

}
