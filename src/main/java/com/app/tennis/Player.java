package com.app.tennis;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;
    private int currentGamePoint = 0;
    private Map<Integer, Integer> TennisSet = new HashMap<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCurrentGamePoint() {
        return currentGamePoint;
    }

    public void setCurrentGamePoint(int currentGamePoint) {
        this.currentGamePoint = currentGamePoint;
    }

    public Map<Integer, Integer> getTennisSet() {
        return TennisSet;
    }

    public void addPoint() {
        this.currentGamePoint += 1;
    }

    public void reducePoint() {
        this.currentGamePoint -= 1;
    }
}
