package com.wavetest.test.repository;

import com.wavetest.test.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query(value = "select p from Participant p where p.presentation.id = :presentationId")
    List<Participant> getAllParticipantByPresentationId(@Param("presentationId") Long id);

    @Query(value = "select p from Participant p " +
            "where p.presentation.id = :presentationId and p.user.id = :userId")
    Participant getAllParticipantByPresentationIdAndUserId(
            @Param("userId") Long userId, @Param("presentationId") Long presentationId);

    @Query(value = "SELECT p from Participant p where p.user.id = :userId and p.presentation.id = :presentationId")
    Optional<Participant> isParticipantExistsOnPresentation(
            @Param("userId") Long userId, @Param("presentationId") Long presentationId);
}
