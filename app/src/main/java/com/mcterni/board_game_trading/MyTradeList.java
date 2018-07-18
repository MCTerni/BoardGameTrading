package com.mcterni.board_game_trading;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyTradeList extends AppCompatActivity  {


    private ListView gameList;
    private GameOverviewList games = new GameOverviewList("carcassone");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trade_list);

        gameList = findViewById(R.id.games_list_view);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter<GameOverview> adapter = new ArrayAdapter<GameOverview>(MyTradeList.this, android.R.layout.simple_list_item_1, games);
                gameList.setAdapter(adapter);
            }
        });

    }


}
