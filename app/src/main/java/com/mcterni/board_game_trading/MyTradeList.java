package com.mcterni.board_game_trading;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

public class MyTradeList extends AppCompatActivity  {


    private ListView gameList;
    private ImageButton buttonSearch;
    private TextView textSearch;
    private GameOverviewList games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trade_list);

        gameList = findViewById(R.id.games_list_view);
        textSearch = findViewById(R.id.text_search);
        buttonSearch = findViewById(R.id.search_button);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch();
                //hideKeyboard(MyTradeList.this, textSearch);
            }
        });



        if (savedInstanceState != null) { //no savedInstanceState when activity is first launched


        }

    }
    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);


    }

    public static void hideKeyboard(Context context, View editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
    public void performSearch(){


        try {
            games = new GameOverviewList(textSearch.getText().toString(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayAdapter<GameOverview> adapter = new ArrayAdapter<GameOverview>(MyTradeList.this, android.R.layout.simple_list_item_1, games);
        gameList.setAdapter(adapter);

    }
}
