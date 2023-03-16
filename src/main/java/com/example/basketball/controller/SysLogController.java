package com.example.basketball.controller;

import com.example.basketball.domain.SysLog;
import com.example.basketball.domain.Team;
import com.example.basketball.repository.SysLogRepository;
import com.example.basketball.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysLogController {
    @Autowired
    SysLogRepository sysLogRepository;
    @QueryMapping
    public Iterable<SysLog> getAllSysLogs() {
        return sysLogRepository.findAll();
    }
}
