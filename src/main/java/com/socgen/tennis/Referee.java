package com.socgen.tennis;

import java.util.Map;
import java.util.Optional;

public class Referee {
    private String name;

    private Player firstPlayer;
    private Player secondPlayer;
    private Player winner;

    private int currentSet = 0;

    private boolean tieBreakActive;

    public Referee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public int getCurrentSet() {
        return currentSet;
    }

    public void startTennisSet() {
        currentSet += 1;
        tieBreakActive = false;

        firstPlayer.getTennisSet().put(currentSet, 0);
        secondPlayer.getTennisSet().put(currentSet, 0);

        startNewGame();
    }

    public void startNewGame() {
        firstPlayer.setCurrentGamePoint(0);
        secondPlayer.setCurrentGamePoint(0);
    }

    public void addPointToPlayer(Player wonPointPlayer) {
        if (!tieBreakActive) {
            if (isDeuceRuleActive()) {
                setPointAfterDeuce(wonPointPlayer);
            } else {
                // add point to player if they do not reach to deuce.
                wonPointPlayer.addPoint();
            }

            if (Optional.ofNullable(getPlayerWithAdvantage()).isPresent()) {
                Player gameWinner = getGameWinner();
                if (Optional.ofNullable(gameWinner).isPresent()) {
                    startNewGame();
                    Player tennisSetWinnerPlayer = addGameWinByPlayerToSet(gameWinner);
                    if (Optional.ofNullable(tennisSetWinnerPlayer).isPresent()) {
                        winner = tennisSetWinnerPlayer;
                    }
                }
            }
        } else {
            tieBreakGame(wonPointPlayer);
        }
    }

    private boolean isDeuceRuleActive() {
        return firstPlayer.getCurrentGamePoint() >= 3 && secondPlayer.getCurrentGamePoint() >= 3;
    }

    private void setPointAfterDeuce(Player wonPointPlayer) {
        if (firstPlayer.getCurrentGamePoint() == 3 && secondPlayer.getCurrentGamePoint() == 3) {
            wonPointPlayer.addPoint();
        } else {
            if (checkAdvantage(wonPointPlayer)) {
                wonPointPlayer.addPoint();
            } else {
                Player playerWithAdvantage = getPlayerWithAdvantage();
                playerWithAdvantage.reducePoint();
            }
        }
    }

    private Player getPlayerWithAdvantage() {
        return checkAdvantage(firstPlayer) ? firstPlayer : checkAdvantage(secondPlayer) ? secondPlayer : null;
    }

    private boolean checkAdvantage(Player player) {
        return player.getCurrentGamePoint() >= 4;
    }

    private Player getGameWinner() {
        return isPointDiffToWin(this.firstPlayer.getCurrentGamePoint() - this.secondPlayer.getCurrentGamePoint())
                ? firstPlayer
                : isPointDiffToWin(this.secondPlayer.getCurrentGamePoint() - this.firstPlayer.getCurrentGamePoint())
                ? secondPlayer
                : null;
    }

    private void tieBreakGame(Player wonPointPlayer) {
        wonPointPlayer.addPoint();
        if (firstPlayer.getCurrentGamePoint() == 6 || secondPlayer.getCurrentGamePoint() == 6) {
            Player tieBreakGameWinner = getGameWinner();
            if (Optional.ofNullable(tieBreakGameWinner).isPresent()) {
                tieBreakGameWinner.getTennisSet().put(currentSet, tieBreakGameWinner.getTennisSet().get(currentSet) + 1);
                winner = tieBreakGameWinner;
            }
        }
    }

    private Player addGameWinByPlayerToSet(Player gameWinnerPlayer) {
        Player tennisSetWinner = null;

        Map<Integer, Integer> firstPlayerSet = firstPlayer.getTennisSet();
        Map<Integer, Integer> secondPlayerSet = secondPlayer.getTennisSet();

        gameWinnerPlayer.getTennisSet().put(currentSet, gameWinnerPlayer.getTennisSet().get(currentSet) + 1);

        if (firstPlayerSet.get(currentSet) == 6 || secondPlayerSet.get(currentSet) == 6) {
            tennisSetWinner = getTennisSetWinner();
            if (Optional.ofNullable(tennisSetWinner).isPresent()) {
                return tennisSetWinner;
            }
        }

        if ((firstPlayerSet.get(currentSet) >= 5 && secondPlayerSet.get(currentSet) >= 5)) {
            tennisSetWinner = getTennisSetWinner();
            if (!Optional.ofNullable(tennisSetWinner).isPresent() && firstPlayerSet.get(currentSet) == 6 && secondPlayerSet.get(currentSet) == 6) {
                this.tieBreakActive = true;
            }
        }
        return tennisSetWinner;
    }

    private Player getTennisSetWinner() {
        int firstPlayerWonGameInSet = this.firstPlayer.getTennisSet().get(currentSet);
        int secondPlayerWonGameInSet = this.secondPlayer.getTennisSet().get(currentSet);

        return isPointDiffToWin(firstPlayerWonGameInSet - secondPlayerWonGameInSet)
                ? firstPlayer
                : isPointDiffToWin(secondPlayerWonGameInSet - firstPlayerWonGameInSet)
                ? secondPlayer
                : null;
    }

    private boolean isPointDiffToWin(int pointDiff) {
        return pointDiff >= 2;
    }
}
