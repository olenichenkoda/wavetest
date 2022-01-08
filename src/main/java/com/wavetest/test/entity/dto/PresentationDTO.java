package com.wavetest.test.entity.dto;

import com.wavetest.test.entity.Participant;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PresentationDTO {

    private String presentationTheme;
    private Set<ParticipantDTO> participants;
}
