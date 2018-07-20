package com.mcterni.board_game_trading;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MySearchList extends AppCompatActivity  implements GameOverviewList.GameOverviewListCallback{


    private ListView gameList;
    private ImageButton buttonSearch;
    private TextView textSearch;
    private GameOverviewList games;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_search_list);

        gameList = findViewById(R.id.games_list_view);
        textSearch = findViewById(R.id.text_search);
        buttonSearch = findViewById(R.id.search_button);

        gameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //Setup the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(getString(R.string.db_game_list));
                //get values to write to database
                GameOverview game = (GameOverview) gameList.getItemAtPosition(position);
                String child = String.valueOf(game.getGameId());
                //write values to database
                myRef.child(child).setValue(gameList.getItemAtPosition(position));
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch();
                hideKeyboard(MySearchList.this, textSearch);
            }
        });

        textSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(games != null)
                    games.clear();//Clean the screen if one enters for a new search
            }
        });


        if (savedInstanceState != null) { //no savedInstanceState when activity is first launched
            textSearch.setText(savedInstanceState.getString(TEXT_SEARCH));
            performSearch();//redo the list on screen

        }

    }
    private static final String TEXT_SEARCH = "text_search";


    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(TEXT_SEARCH, textSearch.getText().toString());
    }

    public static void hideKeyboard(Context context, View editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
    public void performSearch(){

        games = new GameOverviewList().searchGameOverviewLis(textSearch.getText().toString(), this);

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter<GameOverview> adapter = new ArrayAdapter<GameOverview>(MySearchList.this, android.R.layout.simple_list_item_1, games);
                gameList.setAdapter(adapter);
            }
        });
    }
}
