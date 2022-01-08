package com.wavetest.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="presentation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theme")
	private String theme;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "presentation")
    private Set<Participant> participants;

//    @OneToOne(cascade = CascadeType.ALL)
//    private Schedule schedule;

    public Presentation(String theme, Set<Participant> participants) {
        this.theme = theme;
        this.participants = participants;
    }
}
