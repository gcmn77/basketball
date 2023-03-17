package com.example.basketball.repository;

import com.example.basketball.domain.Player;
import com.example.basketball.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class DataLoaderService {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PlayerRepository playerRepository;

    @PostConstruct
    public void loadData() {
        Team bucks = new Team();
        bucks.setName("Milwaukee Bucks");
        bucks.setConference("East");
        teamRepository.save(bucks);
        List<String> playerNames = Arrays.asList("Khris Middleton", "Giannis Ante", "Jrue Holiday", "Jamal Crawford"
                , "Josh Carter", "Joe Ingles", "Bobby Portis", "Pat Connaugton", "Wes Mattews"
                , "Greyson Allen", "Thanasis Ante", "Jae Crowder", "Meyers Leonard");
        for (String playerName : playerNames) {
            Player player = new Player();
            String[] names = playerName.split(" ");
            player.setFirstname(names[0]);
            player.setLastname(names[1]);
            player.setTeam(bucks);
            player.setPosition(Player.Position.SMALL_FORWARD);
            playerRepository.save(player);
        }
    }

}
