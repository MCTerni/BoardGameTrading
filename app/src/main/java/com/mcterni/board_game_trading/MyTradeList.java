package com.mcterni.board_game_trading;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyTradeList extends AppCompatActivity {

    private ListView tradeList;
    private GameOverviewList games;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trade_list);

        games = new GameOverviewList();
        tradeList = findViewById(R.id.trade_list);
//        games = new GameOverviewList().tradeGameOverviewList();
        loadTradeList();
    }

    public void loadTradeList(){
        //Setup the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("game");

//        myRef.addValueEventListener(new ValueEventListener() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                games.clear();

                for (DataSnapshot gameSnapshot: dataSnapshot.getChildren()) {
                    GameOverview game = gameSnapshot.getValue(GameOverview.class);
                    games.add(game);
                }

                ArrayAdapter<GameOverview> adapter = new ArrayAdapter<GameOverview>(MyTradeList.this, android.R.layout.simple_list_item_1, games);
                tradeList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TradeList","DB connection cancelled");
            }
        });
    }
}
