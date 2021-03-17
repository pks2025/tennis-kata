package com.socgen.tennis;

import static  org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void createNewPlayer() {
        Player player = new Player("Roger");
        assertNotNull(player, "should be able to create a new Player");
    }

    @Test
    void playerShouldHaveName() {
        String playerName = "Roger";
        Player player = new Player(playerName);
        assertNotNull(player.getName());
        assertEquals(playerName, player.getName(), "player should have name while creating a player");
    }

    @Test
    void playerShouldHaveInitialCurrentGamePointAsZero() {
        String playerName = "Roger";
        Player player = new Player(playerName);

        assertEquals(0, player.getCurrentGamePoint(), "player should have initial current game point as zero");
    }

    @Test
    void playerShouldHaveInitialEmptyTennisSets() {
        Player player = new Player("Roger");
        assertEquals(0, player.getTennisSet().size(), "Player will initially have no tennis set matches ");
    }

    @Test
    void gamePointOfPlayerShouldIncreaseOnAddingPoint() {
        Player player = new Player("Roger");
        assertEquals(0, player.getCurrentGamePoint(), "Initially player should have zero points");

        player.addPoint();
        assertEquals(1, player.getCurrentGamePoint(), "points is increased to one on  point addition to player");
    }

    @Test
    void gamePointOfPlayerShouldDecreaseOnRemovingPoint() {
        Player player = new Player("Roger");
        assertEquals(0, player.getCurrentGamePoint(), "gamePointOfPlayerShouldDecreaseOnRemovingPoint : Initially player should have zero points");

        player.addPoint();
        assertEquals(1, player.getCurrentGamePoint(), "gamePointOfPlayerShouldDecreaseOnRemovingPoint : points is increased to one on  point addition to player");

        player.reducePoint();
        assertEquals(0, player.getCurrentGamePoint(), "gamePointOfPlayerShouldDecreaseOnRemovingPoint: points is decreased by one on  point removal to player (DEUCE) ");
    }
}
