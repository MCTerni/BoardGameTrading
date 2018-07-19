package com.mcterni.board_game_trading;



public class GameOverview {
    private int gameId;
    private int imageId;
    private String gameName;
    private double gamePrice;

    public GameOverview(int gameId, int imageId, String gameName, double gamePrice){
        this.gameId = gameId;
        this.imageId = imageId;
        this.gameName = gameName;
        this.gamePrice = gamePrice;
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

    @Override
    public String toString() {
        return gameName;
    }
}
