package com.hkz.testingkits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sarnava.textwriter.TextWriter;

public class SplashscreenActivity extends AppCompatActivity {
    TextWriter textWriter;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        textWriter = findViewById(R.id.textWriter);
        img = findViewById(R.id.imageView);

        Glide.with(this).load(R.raw.run).into(img);

        textWriter
                .setWidth(12)
                .setDelay(30)
                .setColor(R.color.white)
                .setConfig(TextWriter.Configuration.INTERMEDIATE)
                .setSizeFactor(30f)
                .setLetterSpacing(25f)
                .setText("WELCOME")
                .setListener(new TextWriter.Listener() {
                    @Override
                    public void WritingFinished() {
                       silentSignIn();
                    }
                })
                .startAnimation();
    }

    private void silentSignIn() {

    }
}