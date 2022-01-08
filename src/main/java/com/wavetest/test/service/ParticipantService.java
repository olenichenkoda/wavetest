package com.wavetest.test.service;

import com.wavetest.test.entity.Participant;
import com.wavetest.test.entity.Presentation;
import com.wavetest.test.entity.Role;
import com.wavetest.test.entity.dto.ParticipantDTO;
import com.wavetest.test.repository.ParticipantRepository;
import com.wavetest.test.repository.PresentationRepository;
import com.wavetest.test.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final RoleRepository roleRepository;
//    private final PresentationRepository presentationRepository;

    @Transactional
    public List<ParticipantDTO> getAllParticipantByPresentationId(Long id) {

        List<Participant> participants = participantRepository.getAllParticipantByPresentationId(id);
        List<ParticipantDTO> resultPDTO =
                participants.stream().collect(Collectors.mapping(t -> new ParticipantDTO(
                                        t.getId(),
                                        t.getUser().getFirstName(),
                                        t.getUser().getLastName(),
                                        t.getRole().getNameRole())
                                , Collectors.toList()));

        return resultPDTO;
    }

    @Transactional
    public ResponseEntity<?> deleteParticipantInPresentationId(Long presentationId) {
        //TODO: достать userId из security
        Long userId = 3L;
        Participant participant =
                participantRepository.getAllParticipantByPresentationIdAndUserId(userId, presentationId);
        if (participant == null) {
            return ResponseEntity.badRequest().body("Данный участник презентации не найден");
        } else {
            participantRepository.delete(participant);
            return ResponseEntity.ok("Участник удален");
        }


    }

    @Transactional
    public ParticipantDTO updateParticipant(ParticipantDTO participantDTO) {
        Participant participant = participantRepository.getById(participantDTO.getIdParticipant());
        Role role =  roleRepository.getByNameRole(participantDTO.getRoleOnPresentation());
        participant.setRole(role);
        participant = participantRepository.save(participant);

        ParticipantDTO resultParticipantDTO =
                new ParticipantDTO(participant.getId(),
                        participant.getUser().getFirstName(),
                        participant.getUser().getLastName(),
                        participant.getRole().getNameRole());
        return resultParticipantDTO;
    }
}
