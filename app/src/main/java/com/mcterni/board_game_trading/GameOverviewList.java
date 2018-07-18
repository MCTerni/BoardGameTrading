package com.mcterni.board_game_trading;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GameOverviewList extends ArrayList<GameOverview> {

    public GameOverviewList(String game){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://boardgamegeek.com/xmlapi/search?search="+game)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    // do something wih the result
                    GameOverviewList.this.add(new GameOverview(1, "Teste 1", 1.1));
                    GameOverviewList.this.add(new GameOverview(2, "Teste 2", 1.2));
                }
            }
        });
    }

//    public interface QuizCallback {
//        void onFailure();
//        void onSuccess();
//    }

}
