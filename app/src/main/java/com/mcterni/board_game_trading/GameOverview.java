package com.mcterni.board_game_trading;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class GameOverview {
    private int gameId;
    private int imageId;
    private String gameName;
    private double gamePrice;

    public GameOverview(){};

    public GameOverview(int gameId, int imageId, String gameName, double gamePrice){
        this.gameId = gameId;
        this.imageId = imageId;
        this.gameName = gameName;
        this.gamePrice = gamePrice;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("gameId", gameId);
        result.put("imageId", imageId);
        result.put("gameName", gameName);
        result.put("gamePrice", gamePrice);

        return result;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(double gamePrice) {
        this.gamePrice = gamePrice;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return gameId + " - "+ gameName;
    }
}
