package com.wavetest.test.service;

import com.wavetest.test.entity.Schedule;
import com.wavetest.test.entity.dto.RoomDTO;
import com.wavetest.test.entity.dto.ScheduleDTO;
import com.wavetest.test.entity.dto.ScheduleInfoDTO;
import com.wavetest.test.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public List<RoomDTO> getAllScheduleInfo() {
        List<ScheduleInfoDTO> sDTO = scheduleRepository.getAllScheduleInfoDTO();

        Map<String, List<ScheduleDTO>> map = sDTO.stream()
                .collect(Collectors.groupingBy(ScheduleInfoDTO::getRoomName,
                        Collectors.mapping(x ->
                                new ScheduleDTO(x.getThemePresentation(), x.getTimeBegin(), x.getTimeEnd()),
                                Collectors.toList())));

        List<RoomDTO> list = new ArrayList<>();
        map.entrySet().stream().forEach(stringListEntry -> list.add(new RoomDTO(stringListEntry.getKey(), stringListEntry.getValue())));
        return list;

    }

}
