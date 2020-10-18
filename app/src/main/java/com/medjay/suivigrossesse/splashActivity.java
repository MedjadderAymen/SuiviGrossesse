package com.medjay.suivigrossesse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import java.util.Locale;
import java.util.Objects;

public class splashActivity extends AppCompatActivity {

    Locale locale;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        preferences=getApplicationContext().getSharedPreferences("My_pref",MODE_PRIVATE);

        if(Objects.equals(preferences.getString("language", null), "fr_FR")||Objects.equals(preferences.getString("language", null),null)){
            locale = new Locale("fr_FR");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            res.updateConfiguration(conf, dm);
        }else{
            locale = new Locale("ar", "AR");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            res.updateConfiguration(conf, dm);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                startActivity(new Intent(splashActivity.this,MainActivity.class));

                finish();

            }
        }, 2500);
    }
}
