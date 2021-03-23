package com.app.tennis;

import java.util.Optional;
import java.util.Scanner;

public class TennisMatchApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        TennisMatch tennisMatch = initTennisMatch(input);
        startTennis(tennisMatch, input);
    }

    public static TennisMatch initTennisMatch(Scanner input) {
        System.out.println("Start a tennis match");
        System.out.println("Enter tennis match name : ");
        TennisMatch tennisMatch = new TennisMatch(input.nextLine());

        System.out.println("Enter name of match Referee: ");
        tennisMatch.setReferee(new Referee(input.nextLine()));

        System.out.println("Enter first player name: ");
        tennisMatch.getReferee().setFirstPlayer(new Player(input.nextLine()));

        System.out.println("Enter second player name: ");
        tennisMatch.getReferee().setSecondPlayer(new Player(input.nextLine()));

        System.out.println("TennisMatch  details: ");
        System.out.println(tennisMatch.toString());

        System.out.println("Enter point for players");
        System.out.println("Enter 1 for " + tennisMatch.getReferee().getFirstPlayer().getName());
        System.out.println("Enter 2 for " + tennisMatch.getReferee().getSecondPlayer().getName() + '\n');

        return tennisMatch;
    }

    public static void startTennis(TennisMatch tennisMatch, Scanner input) {
        Referee referee = tennisMatch.getReferee();
        Player firstPlayer = referee.getFirstPlayer();
        Player secondPlayer = referee.getSecondPlayer();

        tennisMatch.getReferee().startTennisSet();
        tennisMatch.showScore();

        int pointForPlayer;
        do {
            pointForPlayer = input.nextInt();
            switch (pointForPlayer) {
                case 1:
                    referee.addPointToPlayer(firstPlayer);
                    break;
                case 2:
                    referee.addPointToPlayer(secondPlayer);
                    break;
                default:
                    System.out.println("Please select correct player 1 for "
                            + referee.getFirstPlayer().getName()
                            + " and 2 for "
                            + referee.getSecondPlayer().getName());
            }
            tennisMatch.showScore();
        } while (!Optional.ofNullable(referee.getWinner()).isPresent());

        tennisMatch.showWinner();
    }
}
