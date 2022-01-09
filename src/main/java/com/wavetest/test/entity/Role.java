package com.wavetest.test.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;


@Entity
@Table(name="\"role\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_role")
    private String nameRole;


    @Override
    public String getAuthority() {
        return nameRole;
    }
}
