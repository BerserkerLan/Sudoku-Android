package com.kyoudai.sudioku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.synnapps.carouselview.CarouselView;

public class TutorialPopup extends AppCompatActivity {

    CarouselView carouselView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_popup);

        findViewById(R.id.backToHomeFromTutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
