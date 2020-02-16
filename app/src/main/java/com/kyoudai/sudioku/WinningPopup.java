package com.kyoudai.sudioku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class WinningPopup extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winning_popup);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.6));

        TextView timeText = findViewById(R.id.winningTime);
        int timeTaken = getIntent().getIntExtra("timeTaken", 0);
        timeText.setText("Time: " + formatSeconds(timeTaken));
        clearSavedSudoku();

        findViewById(R.id.winningBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.gameActivity.finish();
                finish();
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });

    }

    public String formatSeconds(int secondsCount){
        //Calculate the seconds to display:
        int seconds = secondsCount %60;
        secondsCount -= seconds;
        //Calculate the minutes:
        long minutesCount = secondsCount / 60;
        long minutes = minutesCount % 60;
        minutesCount -= minutes;
        //Calculate the hours:
        long hoursCount = minutesCount / 60;
        //Build the String
        return "" + hoursCount + ":" + minutes + ":" + seconds;
    }

    public void clearSavedSudoku() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        sharedPref.edit().putString("matrix", "0").apply();
    }
    @Override
    public void onBackPressed() {
        // No Back
    }
}
