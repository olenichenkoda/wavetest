package com.wavetest.test.controller;

import com.wavetest.test.entity.dto.ParticipantDTO;
import com.wavetest.test.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participant")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping("/{id}")
    public List<ParticipantDTO> getAllParticipantByPresentationId(@PathVariable("id") Long presentationId) {
        return participantService.getAllParticipantByPresentationId(presentationId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParticipantInPresentationId(@PathVariable("id") Long presentationId) {
       return participantService.deleteParticipantInPresentationId(presentationId);
    }

    @PutMapping("/update")
    public ParticipantDTO updateParticipant(@RequestBody ParticipantDTO participantDTO) {
        return participantService.updateParticipant(participantDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> signUpToPresentation(@PathVariable("id") Long presentationId) {
        return participantService.signUpToPresentation(presentationId);
    }
}
