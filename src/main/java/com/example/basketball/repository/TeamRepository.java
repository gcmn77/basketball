package com.example.basketball.repository;

import com.example.basketball.domain.Player;
import com.example.basketball.domain.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Long> {
Boolean existsTeamByName(String name);
}
