package com.example.root.recipesapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {


    private static int spalsh_time_out=2000;
    private ImageView splashImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splashImage = (ImageView) findViewById(R.id.bgspalsh);

       int orient = getWindowManager().getDefaultDisplay().getOrientation();
        if (orient == 0) {
            splashImage.setBackgroundResource(R.drawable.splashscreenimage);
        } else {
            splashImage.setBackgroundResource(R.drawable.horizental);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,screen1.class);
                startActivity(i);
                finish();

            }
        },spalsh_time_out);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orient = getWindowManager().getDefaultDisplay().getOrientation();
        if (orient == 0) {
            splashImage.setBackgroundResource(R.drawable.splashscreenimage);
        } else {
            splashImage.setBackgroundResource(R.drawable.horizental);
        }
    }
}
