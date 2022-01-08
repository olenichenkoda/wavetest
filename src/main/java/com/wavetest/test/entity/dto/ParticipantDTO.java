package com.wavetest.test.entity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDTO {

    private Long idParticipant;
    private String firstName;
    private String lastName;
    private String roleOnPresentation;


}
