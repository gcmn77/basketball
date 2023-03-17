package com.example.basketball.controller;

import com.example.basketball.domain.Player;
import com.example.basketball.domain.Team;
import com.example.basketball.repository.PlayerRepository;
import com.example.basketball.repository.TeamRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
class PlayerControllerTest {

    @Autowired
    GraphQlTester graphQlTester;

    @MockBean
    private PlayerRepository playerRepository;
    @MockBean
    private TeamRepository teamRepository;

    static Player player = new Player();
    static Team team = new Team();
    static List<Player> players = new ArrayList<>();

    @BeforeAll
    static void setup() {
        player.setPosition(Player.Position.CENTER);
        player.setFirstname("Brook");
        player.setLastname("Lopez");
        team.setName("Milwaukee Bucks");
        team.setId(77L);
        List<String> playerNames = Arrays.asList("Khris Middleton", "Giannis Ante", "Jrue Holiday");
        Long id = 1L;
        for (String playerName : playerNames) {
            Player player = new Player();
            String[] names = playerName.split(" ");
            player.setId(id);
            player.setFirstname(names[0]);
            player.setLastname(names[1]);
            player.setTeam(team);
            player.setPosition(Player.Position.SMALL_FORWARD);
            players.add(player);
            id++;
        }
    }

    @Test
    void savePlayer() {
        doReturn(player).when(playerRepository).save(new Player());
        doReturn(Optional.ofNullable(team)).when(teamRepository).findById(2L);
        val query = "mutation { " +
                "  savePlayer(firstname: \"Brook\", " +
                "  lastname: \"Lopez\", " +
                "  position: CENTER, " +
                "  teamId: \"2\"){id} " +
                "} ";

//        graphQlTester.document(query)
//                .execute()
//                .path("savePlayer")
//                .entity(Player.class)
//                .get();
    }

    @Test
    void getAllPlayers() {
        val query = "{" +
                "getAllPlayers(page: 0, size: 3) {" +
                " id " +
                " firstname " +
                " lastname " +
                " team { " +
                " id " +
                " translatedName: name }}} ";
        Page<Player> page = new PageImpl<>(players, PageRequest.of(0,3), players.size());
        doReturn(page).when(playerRepository).findAll(PageRequest.of(0,3));
        graphQlTester.document(query)
                .execute()
                .path("getAllPlayers")
                .entityList(Player.class)
                .hasSize(3);
    }

}