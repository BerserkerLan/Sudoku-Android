package com.kyoudai.sudioku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.VideoListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;

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
                final StartAppAd startAppAd = new StartAppAd(getApplicationContext());
                startAppAd.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                    @Override
                    public void onReceiveAd(Ad ad) {
                        startAppAd.showAd();
                    }
                    @Override
                    public void onFailedToReceiveAd(Ad ad) {
                        Toast.makeText(getApplicationContext(),"Cannot load advert at this time so you can only quit the game", Toast.LENGTH_LONG).show();
                    }
                });
                startAppAd.setVideoListener(new VideoListener() {
                    @Override
                    public void onVideoCompleted() {
                        Log.i("VideoWatched","watched");
                        GameActivity.mistakes = 2;
                        GameActivity.mistakesText.setText("Mistakes: " + GameActivity.mistakes  + "/3");
                        finish();

                    }
                });

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
