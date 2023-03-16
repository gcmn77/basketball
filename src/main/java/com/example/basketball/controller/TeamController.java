package com.example.basketball.controller;

import com.example.basketball.domain.Player;
import com.example.basketball.domain.Team;
import com.example.basketball.repository.PlayerRepository;
import com.example.basketball.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class TeamController {
    @Autowired
    TeamRepository teamRepository;

    @QueryMapping
    public Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
