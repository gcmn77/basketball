package com.example.basketball.domain;

import com.example.basketball.audit.AuditTrailListener;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EntityListeners(AuditTrailListener.class)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String conference;
    @OneToMany(fetch = FetchType.LAZY)
    Set<Player> players;
}
