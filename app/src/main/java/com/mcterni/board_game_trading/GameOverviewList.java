package com.mcterni.board_game_trading;

import android.app.Activity;
import android.content.Context;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameOverviewList extends ArrayList<GameOverview> implements ReturnHTTP.ReturnHTTPCallback {

    private String game;
    private InputStream stream = new ReturnHTTP().getStream("https://boardgamegeek.com/xmlapi/search?search="+game, this);
    private Activity activity;
    public GameOverviewList(String game, Activity activity) throws IOException {

        this.game = game;
        this.activity = activity;

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccess() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //Instantiate the parser
                GameOVerviewSearchXmlParser gameOVerviewSearchXmlParser = new GameOVerviewSearchXmlParser();
                List<GameOVerviewSearchXmlParser.GameDetails> gameDetailsList = null;


                try {
                    gameDetailsList = gameOVerviewSearchXmlParser.parse(stream);
                    // Makes sure that the InputStream is closed after the app is
                    // finished using it.
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                for (GameOVerviewSearchXmlParser.GameDetails gameDetails : gameDetailsList) {
                    GameOverviewList.this.add(new GameOverview(Integer.parseInt(gameDetails.gameId), 1, gameDetails.gameId + " - " + gameDetails.gameName, 1.1));
                }
            }
        });
    }
}

