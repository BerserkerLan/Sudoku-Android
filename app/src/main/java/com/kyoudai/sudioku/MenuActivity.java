package com.kyoudai.sudioku;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.mopub.common.MoPub;
import com.mopub.mobileads.MoPubRewardedVideos;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MoPub.onCreate(this);

        findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkIfGameExists();
                    MoPubRewardedVideos.loadRewardedVideo(Constants.MOPUB_KEY);
                    Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                    intent.putExtra("continue", true);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please start a new game, there is no previous game saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.newGameButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoPubRewardedVideos.loadRewardedVideo(Constants.MOPUB_KEY);
                Intent intent = new Intent(getApplicationContext(), DifficultySelectionPopup.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tutorialButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TutorialPopup.class));
            }
        });

        findViewById(R.id.aboutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            }
        });

    }


    public void checkIfGameExists() throws Exception {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String matrixString = sharedPref.getString("matrix", "0");
        if (matrixString.equals("0")) {
            throw new Exception();
        }
    }

}
