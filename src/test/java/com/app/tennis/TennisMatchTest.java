package com.app.tennis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class TennisMatchTest {

    @Test
    void createTennisMatch() {
        TennisMatch tennisMatch = new TennisMatch("Tennis Match");
        assertNotNull(tennisMatch, "should able to create a Tennis match");
    }

    @Test
    void shouldAddRefereeToTennisMatch(){
        TennisMatch tennisMatch = new TennisMatch("Tennis Match");

        Referee referee = new Referee("Bharat");
        tennisMatch.setReferee(referee);

        assertNotNull(tennisMatch.getReferee());
        assertEquals("Bharat", tennisMatch.getReferee().getName(), "referee should have name");
    }

    @Test
    void shouldSetPlayersToTennisMatch() {
        TennisMatch tennisMatch = new TennisMatch("Tennis Match");

        Referee referee = new Referee("Bharat");
        referee.setFirstPlayer(new Player("Roger"));
        referee.setSecondPlayer(new Player("Nick"));

        tennisMatch.setReferee(referee);

        assertNotNull(tennisMatch.getReferee().getFirstPlayer(), "should able to set first player");
        assertNotNull(tennisMatch.getReferee().getSecondPlayer(), "should able to set second player");
    }
}
