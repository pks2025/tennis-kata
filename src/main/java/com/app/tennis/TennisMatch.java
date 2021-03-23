package com.app.tennis;

import java.util.HashMap;
import java.util.Map;

public class TennisMatch {
    private String name;
    private Referee referee;

    private Map<Integer, String> gamePoints = new HashMap<>();

    public TennisMatch(String name) {
        this.name = name;
        initGamePoints();
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    private void initGamePoints(){
        gamePoints.put(0, "0");
        gamePoints.put(1, "15");
        gamePoints.put(2, "30");
        gamePoints.put(3, "40");
        gamePoints.put(4, "AD");
    }

    public String toString() {
        return "Name     : " + this.name + '\n'
                + "Referee  : " + this.getReferee().getName() + '\n'
                + "First player  : " + this.getReferee().getFirstPlayer().getName() + '\n'
                + "Second player : " + this.getReferee().getSecondPlayer().getName() + '\n';
    }

    public void showScore() {
        showPlayerScore(this.getReferee().getFirstPlayer());
        showPlayerScore(this.getReferee().getSecondPlayer());
    }

    private void showPlayerScore(Player player) {
        System.out.println(
                "Player  : " + player.getName() + '\n' +
                        "Tennis set score     : " + player.getTennisSet()+ '\n' +
                        "current Game point   : " + gamePoints.get(player.getCurrentGamePoint())
        );
    }

    public void showWinner() {
        Player winner = this.getReferee().getWinner();
        System.out.println('\n' + "Winner of Tennis Match : " + '\n');

        showPlayerScore(winner);
    }
}
