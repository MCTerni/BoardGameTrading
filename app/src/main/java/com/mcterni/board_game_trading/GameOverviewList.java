package com.mcterni.board_game_trading;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GameOverviewList extends ArrayList<GameOverview> {

    public GameOverviewList(String game, final GameOverviewListCallback callback){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://boardgamegeek.com/xmlapi/search?search="+game)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onFailure();
                    throw new IOException("Unexpected code " + response);
                } else {
                    // do something wih the result

//                    fillList();

                    InputStream stream = null;
                    //Instantiate the parser
                    BGGSearchXmlParser bggSearchXmlParser = new BGGSearchXmlParser();
                    List<BGGSearchXmlParser.GameDetails> gameDetailsList = null;

                    try {
                        stream = response.body().byteStream();
                        gameDetailsList = bggSearchXmlParser.parse(stream);
                        // Makes sure that the InputStream is closed after the app is
                        // finished using it.
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } finally {
                        if (stream != null) {
                            stream.close();
                        }
                    }

                    for(BGGSearchXmlParser.GameDetails gameDetails : gameDetailsList)
                    {
                        GameOverviewList.this.add(new GameOverview(Integer.parseInt(gameDetails.gameId),1, gameDetails.gameId + " - " + gameDetails.gameName, 1.1));
                    }
                    callback.onSuccess();
                }
            }
        });
    }

//    public void fillList(){
//        InputStream stream = null;
//        //Instantiate the parser
//        BGGSearchXmlParser bggSearchXmlParser = new BGGSearchXmlParser();
//        List<BGGSearchXmlParser.GameDetails> gameDetailsList = null;
//
//
//        GameOverviewList.this.add(new GameOverview(1, "Teste 1", 1.1));
//    }

    public interface GameOverviewListCallback {
        void onFailure();
        void onSuccess();
    }

}
