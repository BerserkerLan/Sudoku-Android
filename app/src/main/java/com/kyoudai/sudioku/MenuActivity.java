package com.kyoudai.sudioku;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        StartAppSDK.init(this, Constants.APP_ID, true);
        StartAppAd.disableSplash();

        findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkIfGameExists();
                    Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                    intent.putExtra("continue", false);
                    StartAppAd.showAd(getApplicationContext());
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please start a new game, there is no previous game saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.newGameButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
