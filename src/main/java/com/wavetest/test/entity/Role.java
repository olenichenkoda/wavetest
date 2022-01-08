package com.wavetest.test.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="\"role\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_role")
    private String nameRole;


}
