package com.example.basketball.domain;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class SysLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String domain;
    private String process;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
    private String body;
}
