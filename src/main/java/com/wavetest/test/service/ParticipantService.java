package com.wavetest.test.service;

import com.wavetest.test.entity.Participant;
import com.wavetest.test.entity.Presentation;
import com.wavetest.test.entity.Role;
import com.wavetest.test.entity.User;
import com.wavetest.test.entity.dto.ParticipantDTO;
import com.wavetest.test.repository.ParticipantRepository;
import com.wavetest.test.repository.PresentationRepository;
import com.wavetest.test.repository.RoleRepository;
import com.wavetest.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final PresentationRepository presentationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public List<ParticipantDTO> getAllParticipantByPresentationId(Long presentationId) {

        List<Participant> participants = participantRepository.getAllParticipantByPresentationId(presentationId);
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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long userId = user.getId();
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
        Role role = roleRepository.getByNameRole(participantDTO.getRoleOnPresentation());
        participant.setRole(role);
        participant = participantRepository.save(participant);

        ParticipantDTO resultParticipantDTO =
                new ParticipantDTO(participant.getId(),
                        participant.getUser().getFirstName(),
                        participant.getUser().getLastName(),
                        participant.getRole().getNameRole());
        return resultParticipantDTO;
    }

    @Transactional
    public ResponseEntity<?> signUpToPresentation(Long presentationId) {

        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getById(loginUser.getId());

        Optional<Participant> isParticipantExists =
                participantRepository.isParticipantExistsOnPresentation(user.getId(), presentationId);
        if (isParticipantExists.isPresent()) {
            return ResponseEntity.badRequest().body("Пользователь уже присутствует в данной презентации");
        } else {
            Presentation presentation = presentationRepository.getById(presentationId);
            Participant addParticipant = new Participant();
            addParticipant.setPresentation(presentation);
            addParticipant.setUser(user);
            Role role = roleRepository.getByNameRole("Listener");
            addParticipant.setRole(role);
            addParticipant = participantRepository.save(addParticipant);

            ParticipantDTO participantDTO = new ParticipantDTO(
                    addParticipant.getId(),
                    addParticipant.getUser().getFirstName(),
                    addParticipant.getUser().getLastName(),
                    addParticipant.getRole().getNameRole());
            return ResponseEntity.ok(participantDTO);
        }
    }
}
