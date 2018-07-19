package com.mcterni.board_game_trading;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //Variables
    private ImageView mainImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainImage = findViewById(R.id.imageMain);

            mainImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("Main", "Click on Main Image");
                    Intent resultActivityIntent = new Intent(MainActivity.this, MySearchList.class);
                    startActivity(resultActivityIntent);
                }
            });


    }
}
