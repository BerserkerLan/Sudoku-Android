package com.kyoudai.sudioku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class DifficultySelectionPopup extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty_selection_popup);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.6));

        findViewById(R.id.easyButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playGame = new Intent(getApplicationContext(), GameActivity.class);
                playGame.putExtra("difficulty", 0);
                startActivity(playGame);
                finish();
            }
        });

        findViewById(R.id.mediumButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playGame = new Intent(getApplicationContext(), GameActivity.class);
                playGame.putExtra("difficulty", 1);
                startActivity(playGame);
                finish();
            }
        });

        findViewById(R.id.expertButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playGame = new Intent(getApplicationContext(), GameActivity.class);
                playGame.putExtra("difficulty", 2);
                startActivity(playGame);
                finish();
            }
        });
    }
}
