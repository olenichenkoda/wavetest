package com.wavetest.test.repository;

import com.wavetest.test.entity.Room;
import com.wavetest.test.entity.Schedule;
import com.wavetest.test.entity.dto.RoomDTO;
import com.wavetest.test.entity.dto.ScheduleInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT new com.wavetest.test.entity.dto.ScheduleInfoDTO " +
            "(s.room.nameRoom, s.presentation.theme, s.timeBegin, s.timeEnd) from Schedule s " )
    List<ScheduleInfoDTO> getAllScheduleInfoDTO();

    @Query(value = "SELECT new com.wavetest.test.entity.dto.ScheduleInfoDTO " +
            "(s.room.nameRoom, s.presentation.theme, s.timeBegin, s.timeEnd) from Schedule s " +
            "where s.room.id = :roomId " +
            "AND (( :beginTime >= s.timeBegin and :beginTime < s.timeEnd " +
            "or :endTime > s.timeBegin and :endTime <= s.timeEnd ) " +
            "or ( s.timeBegin > :beginTime and s.timeBegin < :endTime " +
            "or s.timeEnd > :beginTime and s.timeEnd < :endTime ))")
    List<ScheduleInfoDTO> checkDateTimeAndRoom(@Param("roomId") Long roomId,
                                               @Param("beginTime") LocalDateTime begin,
                                               @Param("endTime") LocalDateTime end);

    @Query(value = "SELECT s from Schedule s where s.presentation.id = :idPresentation")
    Schedule findByPresentationId(@Param("idPresentation") Long id);
}
