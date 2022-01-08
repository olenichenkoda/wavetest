package com.wavetest.test.repository;

import com.wavetest.test.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "select r from Room r where r.nameRoom = :nameRoom")
    Room getRoomByNameRoom(@Param("nameRoom") String nameRoom);
}
