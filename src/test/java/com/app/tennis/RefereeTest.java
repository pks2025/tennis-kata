package com.app.tennis;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RefereeTest {

    @Test
    void createNewReferee(){
        Referee referee = new Referee("Bharat");
        assertNotNull(referee, "should be able to create a referee");
    }

    @Test
    void refereeShouldHaveName() {
        Referee referee = new Referee("Bharat");
        assertEquals("Bharat", referee.getName(), "referee should have name");
    }

    @Test
    void refereeShouldAbleToAddFirstPlayer() {
        Referee referee = new Referee("Bharat");
        referee.setFirstPlayer(new Player("Roger"));

        assertEquals("Roger", referee.getFirstPlayer().getName(), "referee should able to set first player");
    }

    @Test
    void refereeShouldAbleToAddSecondPlayer() {
        Referee referee = new Referee("Bharat");
        referee.setSecondPlayer(new Player("Nick"));

        assertEquals("Nick", referee.getSecondPlayer().getName(), "referee should able to set second player");
    }

    @Test
    void currentSetShouldBeZeroBeforeStartingSet() {
        Referee referee = new Referee("Bharat");
        assertEquals(0, referee.getCurrentSet(), "current tennis set should be zero initially");
    }

    @Test
    void winnerShouldBeNullInitially() {
        Referee referee = new Referee("Bharat");
        assertNull(referee.getWinner(), "Initially Referee should not know winner");
    }

    @Test
    void refereeShouldAbleToStartNewSet() {
        Referee referee = new Referee("Bharat");

        referee.setFirstPlayer(new Player("Roger"));
        referee.setSecondPlayer(new Player("Nick"));

        referee.startTennisSet();

        assertEquals(1, referee.getCurrentSet(), "current set should be increased by one on starting a new tennis set");
        assertEquals(0, referee.getFirstPlayer().getTennisSet().get(referee.getCurrentSet()), "first player should have zero game win on start of new Tennis Set");
        assertEquals(0, referee.getSecondPlayer().getTennisSet().get(referee.getCurrentSet()), "first player should have zero game win on start of new Tennis Set");
    }

    @Test
    void eachPlayerShouldHaveZeroGameWinOnNewGameStart() {
        Referee referee = new Referee("Bharat");

        referee.setFirstPlayer(new Player("Roger"));
        referee.setSecondPlayer(new Player("Nick"));

        referee.startNewGame();

        assertEquals(0, referee.getFirstPlayer().getCurrentGamePoint(), "first player should have zero game point when a new game is started by referee");
        assertEquals(0, referee.getSecondPlayer().getCurrentGamePoint(), "second player should have zero game point when a new game is started by referee");
    }

    @Test
    void shouldAbleToAddPointToPlayer() {
        Referee referee = new Referee("Bharat");

        referee.setFirstPlayer(new Player("Roger"));
        referee.setSecondPlayer(new Player("Nick"));

        referee.startNewGame();
        assertEquals(0, referee.getFirstPlayer().getCurrentGamePoint(), "shouldAbleToAddPointToPlayer: first player should have zero game point when a new game is started by referee");
        assertEquals(0, referee.getSecondPlayer().getCurrentGamePoint(), "shouldAbleToAddPointToPlayer: second player should have zero game point when a new game is started by referee");

        referee.addPointToPlayer(referee.getFirstPlayer());
        assertEquals(1, referee.getFirstPlayer().getCurrentGamePoint(), "After first point");
        assertEquals(0, referee.getSecondPlayer().getCurrentGamePoint(), "After first point");
    }

    @Test
    void shouldAbleToDecideCurrentGameWinner() {
        Referee referee = new Referee("Bharat");

        referee.setFirstPlayer(new Player("Roger"));
        referee.setSecondPlayer(new Player("Nick"));

        referee.startTennisSet();
        assertEquals(0, referee.getFirstPlayer().getCurrentGamePoint(), "shouldAbleToAddPointToPlayer: first player should have zero game point when a new game is started by referee");
        assertEquals(0, referee.getFirstPlayer().getCurrentGamePoint(), "shouldAbleToAddPointToPlayer: second player should have zero game point when a new game is started by referee");

        referee.addPointToPlayer(referee.getFirstPlayer());
        assertEquals(1, referee.getFirstPlayer().getCurrentGamePoint(), "After first point first player");
        assertEquals(0, referee.getSecondPlayer().getCurrentGamePoint(), "After first point second player");

        referee.addPointToPlayer(referee.getFirstPlayer());
        assertEquals(2, referee.getFirstPlayer().getCurrentGamePoint(), "After 2nd point first player");
        assertEquals(0, referee.getSecondPlayer().getCurrentGamePoint(), "After 2nd point second player");

        referee.addPointToPlayer(referee.getFirstPlayer());
        assertEquals(3, referee.getFirstPlayer().getCurrentGamePoint(), "After 3rd point first player");
        assertEquals(0, referee.getSecondPlayer().getCurrentGamePoint(), "After 3rd point second player");

        referee.addPointToPlayer(referee.getFirstPlayer());
        assertEquals(1, referee.getFirstPlayer().getTennisSet().get(referee.getCurrentSet()), "After 4th point first player");
        assertEquals(0, referee.getSecondPlayer().getTennisSet().get(referee.getCurrentSet()), "After 4th point second player");
    }

    @Test
    void shouldAbleToDecideCurrentSetWinner() {
        Referee referee = new Referee("Bharat");
        Player firstPlayer = new Player("Roger");
        Player secondPlayer = new Player("Nick");

        referee.setFirstPlayer(firstPlayer);
        referee.setSecondPlayer(secondPlayer);

        referee.startTennisSet();

        int pointForPlayer;
        do {
            pointForPlayer = (int)(Math.random()*((2-1)+1))+1;
            switch (pointForPlayer) {
                case 1:
                    referee.addPointToPlayer(firstPlayer);
                    break;
                case 2:
                    referee.addPointToPlayer(secondPlayer);
                    break;
            }
        } while (!Optional.ofNullable(referee.getWinner()).isPresent());
        // debug purpose.
        System.out.println("winner : " + referee.getWinner().getName());
        System.out.println("first player  : " + referee.getFirstPlayer().getName() + " set score : " + referee.getFirstPlayer().getTennisSet());
        System.out.println("second player : " + referee.getSecondPlayer().getName() + " set score : " + referee.getSecondPlayer().getTennisSet());

        assertNotNull(referee.getWinner(), "should able to decide winner of set in tennis set match");
    }
}
