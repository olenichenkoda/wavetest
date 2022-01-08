package com.wavetest.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "presentation_id")
    private Presentation presentation;

    @Column(name = "time_begin")
    private LocalDateTime timeBegin;

    @Column(name = "time_end")
    private LocalDateTime timeEnd;

    public Schedule(Room room, Presentation presentation, LocalDateTime timeBegin, LocalDateTime timeEnd) {
        this.room = room;
        this.presentation = presentation;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
    }
}
