package com.kyoudai.sudioku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class LosingPopup extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.losing_popup);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.6));

        findViewById(R.id.undoLastMistake).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.mistakes = 2;
                GameActivity.mistakesText.setText("Mistakes: " + GameActivity.mistakes  + "/3");
                finish();
            }
        });

        findViewById(R.id.quitGameButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSavedGame();
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                finish();
            }
        });

    }

    public void deleteSavedGame() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        sharedPref.edit().putString("matrix", "0").apply();
    }

    @Override
    public void onBackPressed() {
        //Nothing
    }
}
