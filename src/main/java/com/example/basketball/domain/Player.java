package com.example.basketball.domain;

import com.example.basketball.audit.AuditTrailListener;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@EntityListeners(AuditTrailListener.class)
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;

    /*
    1 -> PG
    2 -> SG
    3 -> SF
    4 -> PF
    5 -> C
     */
    private Short position;

    @ManyToOne
    private Team team;

    public enum Position {
        POINT_GUARD,
        SHOOTING_GUARD
        /*
        @Enumerated(EnumType.STRING)
    private Gender gender;
         */
    }
}
