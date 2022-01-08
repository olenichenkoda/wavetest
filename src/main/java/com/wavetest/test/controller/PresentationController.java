package com.wavetest.test.controller;

import com.wavetest.test.entity.dto.PresentationDTO;
import com.wavetest.test.entity.dto.ScheduleAllInfoDTO;
import com.wavetest.test.entity.dto.ScheduleInfoDTO;
import com.wavetest.test.service.PresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/presentation")
@RequiredArgsConstructor
public class PresentationController {

    private final PresentationService presentationService;

    @PostMapping
    public ResponseEntity<?> addNewPresentation(@RequestBody ScheduleInfoDTO scheduleInfoDTO) {
        return presentationService.addNewPresentation(scheduleInfoDTO);
    }

    @GetMapping("/{id}")
    public PresentationDTO getPresentationById(@PathVariable Long id) {
        return presentationService.getPresentationById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePresentationById(@PathVariable Long id) {
        presentationService.deletePresentationById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePresentationById(@PathVariable Long id, @RequestBody ScheduleInfoDTO scheduleInfoDTO) {
        return presentationService.updatePresentationById(id, scheduleInfoDTO);
    }
}
