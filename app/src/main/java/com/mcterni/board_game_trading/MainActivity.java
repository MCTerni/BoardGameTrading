package com.mcterni.board_game_trading;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //Variables
    private Button mainSearchButton;
    private Button mainTradeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainSearchButton = findViewById(R.id.main_search_button);
        mainTradeButton = findViewById(R.id.main_trade_button);

        mainSearchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("Main", "Click on Main Image");
                    Intent resultActivityIntent = new Intent(MainActivity.this, /*FirebaseUIActivity.class*/MySearchList.class);
                    startActivity(resultActivityIntent);
                }
            });
        mainTradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Main", "Click on Main Image");
                Intent resultActivityIntent = new Intent(MainActivity.this, /*FirebaseUIActivity.class*/MyTradeList.class);
                startActivity(resultActivityIntent);
            }
        });


    }
}
