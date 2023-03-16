package com.example.basketball.repository;

import com.example.basketball.domain.Player;
import com.example.basketball.domain.Team;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
    int countByTeam(Team team);
    void deleteById(Long id);
    boolean existsById(Long id);
}
