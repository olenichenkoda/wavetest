package com.wavetest.test.service;

import com.wavetest.test.entity.*;
import com.wavetest.test.entity.dto.ParticipantDTO;
import com.wavetest.test.entity.dto.PresentationDTO;
import com.wavetest.test.entity.dto.ScheduleAllInfoDTO;
import com.wavetest.test.entity.dto.ScheduleInfoDTO;
import com.wavetest.test.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PresentationService {

    private final PresentationRepository presentationRepository;
    private final ScheduleRepository scheduleRepository;
    private final RoleRepository roleRepository;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public ResponseEntity<?> addNewPresentation(ScheduleInfoDTO scheduleInfoDTO) {

        LocalDateTime beginTimeNew = scheduleInfoDTO.getTimeBegin();
        LocalDateTime endTimeNew = scheduleInfoDTO.getTimeEnd();
        Room roomNew = roomRepository.getRoomByNameRoom(scheduleInfoDTO.getRoomName());
        String presentationThemeNew = scheduleInfoDTO.getThemePresentation();

        List<ScheduleInfoDTO> checkScheduleInfo =
                scheduleRepository.checkDateTimeAndRoom(roomNew.getId(), beginTimeNew, endTimeNew);

        if (!checkScheduleInfo.isEmpty()) {
            return ResponseEntity.badRequest().body("Аудитория занята в это время");
        } else {

            Presentation presentation = new Presentation();
            presentation.setTheme(presentationThemeNew);

            Schedule schedule = new Schedule();
            schedule.setRoom(roomNew);
            schedule.setPresentation(presentation);
            schedule.setTimeBegin(beginTimeNew);
            schedule.setTimeEnd(endTimeNew);
            schedule = scheduleRepository.save(schedule);

            //Todo: Получать id user`a из security
            User newUser = userRepository.getById(2L);

            Role newRoleOnPresentation = roleRepository.getByNameRole("Presenter");

            Participant participant = new Participant();
            participant.setUser(newUser);
            participant.setRole(newRoleOnPresentation);
            participant.setPresentation(schedule.getPresentation());
            participant = participantRepository.save(participant);

            ScheduleAllInfoDTO resultSAIDTO = new ScheduleAllInfoDTO();
            resultSAIDTO.setRoomName(roomNew.getNameRoom());
            resultSAIDTO.setThemePresentation(presentationThemeNew);
            resultSAIDTO.setTimeBegin(beginTimeNew);
            resultSAIDTO.setTimeEnd(endTimeNew);

            ParticipantDTO participantDTO = new ParticipantDTO(participant.getId(),participant.getUser().getFirstName(),
            participant.getUser().getLastName(), participant.getRole().getNameRole());
            resultSAIDTO.setParticipants(Arrays.asList(participantDTO));

            return ResponseEntity.ok(resultSAIDTO);
        }

    }

    @Transactional
    public PresentationDTO getPresentationById(Long id) {
        PresentationDTO presentationDTO = new PresentationDTO();
        Presentation presentation = presentationRepository.findById(id).get();
        presentationDTO.setPresentationTheme(presentation.getTheme());
        Set<ParticipantDTO> participantDTOs = presentation.getParticipants().stream().map(x ->
        {return new ParticipantDTO(x.getId(), x.getUser().getFirstName(),
                        x.getUser().getLastName(),
                        x.getRole().getNameRole());
        }).collect(Collectors.toSet());
        presentationDTO.setParticipants(participantDTOs);
        return presentationDTO;
    }

    @Transactional
    public void deletePresentationById(Long id) {
        Schedule scheduleOnDelete = scheduleRepository.findByPresentationId(id);
        scheduleRepository.delete(scheduleOnDelete);
//        presentationRepository.deleteById(id);
    }

    @Transactional
    public ResponseEntity<?> updatePresentationById(Long id, ScheduleInfoDTO scheduleInfoDTO) {

        Schedule schedule = scheduleRepository.findByPresentationId(id);
        LocalDateTime beginTimeNew = scheduleInfoDTO.getTimeBegin();
        LocalDateTime endTimeNew = scheduleInfoDTO.getTimeEnd();
        Room roomNew = roomRepository.getRoomByNameRoom(scheduleInfoDTO.getRoomName());
        String presentationThemeNew = scheduleInfoDTO.getThemePresentation();

        //TODO: Создать новый запрос для проверки, учивать изменение времени в текущей презентации
        List<ScheduleInfoDTO> checkScheduleInfo =
                scheduleRepository.checkDateTimeAndRoom(roomNew.getId(), beginTimeNew, endTimeNew);

        if (!checkScheduleInfo.isEmpty()) {
            return ResponseEntity.badRequest().body("Аудитория занята в это время");
        } else {
            schedule.setRoom(roomNew);

            Presentation oldPresentation = schedule.getPresentation();
            oldPresentation.setTheme(presentationThemeNew);
            Set<Participant> oldParticipant = oldPresentation.getParticipants();
            schedule.setPresentation(oldPresentation);
            schedule.setTimeBegin(beginTimeNew);
            schedule.setTimeEnd(endTimeNew);
            schedule = scheduleRepository.save(schedule);

            ScheduleAllInfoDTO resultSAIDTO = new ScheduleAllInfoDTO();
            resultSAIDTO.setRoomName(roomNew.getNameRoom());
            resultSAIDTO.setThemePresentation(presentationThemeNew);
            resultSAIDTO.setTimeBegin(beginTimeNew);
            resultSAIDTO.setTimeEnd(endTimeNew);

            List<ParticipantDTO> participantDTOs = oldPresentation.getParticipants().stream().map(x ->
            {return new ParticipantDTO(x.getId(),x.getUser().getFirstName(),
                    x.getUser().getLastName(),
                    x.getRole().getNameRole());
            }).collect(Collectors.toList());
            resultSAIDTO.setParticipants(participantDTOs);
            return ResponseEntity.ok(resultSAIDTO);
        }

    }


}
