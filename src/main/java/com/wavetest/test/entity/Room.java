package com.wavetest.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_room")
    private String nameRoom;

//    @Column(name = "number_of_seats")
//    private int numberOfSeats;

//    @OneToMany(mappedBy = "room")
//    private List<Schedule> schedules;

}
