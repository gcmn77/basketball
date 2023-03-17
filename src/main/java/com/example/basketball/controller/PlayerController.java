package com.example.basketball.controller;

import com.example.basketball.domain.Player;
import com.example.basketball.domain.Team;
import com.example.basketball.exception.TeamFullException;
import com.example.basketball.repository.PlayerRepository;
import com.example.basketball.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TeamRepository teamRepository;

    @QueryMapping
    public Page<Player> getAllPlayers(@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return playerRepository.findAll(pageRequest);
    }

    @MutationMapping
    @Transactional
    public Player savePlayer(@Argument String firstname, @Argument String lastname, @Argument Player.Position position, @Argument Long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if(!team.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found.");
        }
        if(playerRepository.countByTeam(team.get()) >= 15) {
            throw new TeamFullException("Team's roster is full.");
        }
        Player player = new Player();
        player.setFirstname(firstname);
        player.setLastname(lastname);
        player.setPosition(position);
        player.setTeam(team.get());
        return playerRepository.save(player);
    }

    @MutationMapping
    @Transactional
    public Boolean deletePlayer(@Argument Long id) {
        if(!playerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found.");
        }
        playerRepository.deleteById(id);
        return true;
    }
}
