package com.kyoudai.sudioku;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

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

    }

    @Override
    public void onBackPressed() {
        // No Back
    }
}
