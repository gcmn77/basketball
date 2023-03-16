package com.example.basketball.repository;

import com.example.basketball.domain.Player;
import com.example.basketball.domain.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {

}
