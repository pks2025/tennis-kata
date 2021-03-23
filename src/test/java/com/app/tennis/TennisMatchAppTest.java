package com.app.tennis;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Scanner;

public class TennisMatchAppTest {

    @Test
    void shouldCreateTennisMatch() {
        Scanner scanner = new Scanner(System.in);

        TennisMatch tennisMatch =  TennisMatchApp.initTennisMatch(scanner);

        assertNotNull(tennisMatch, "should able to create tennis match");
    }

    @Test
    void shouldStartTennisMatch() {
        Scanner scanner = new Scanner(System.in);

        TennisMatch tennisMatch =  TennisMatchApp.initTennisMatch(scanner);

        TennisMatchApp.startTennis(tennisMatch, scanner);

        assertNotNull(tennisMatch.getReferee().getWinner(), "should get winner at end of tennis set match");
    }
}
