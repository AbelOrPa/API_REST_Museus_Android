package com.example.museos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class splash_activity extends Activity {

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_activity);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkCredentials();

            };
        }, 3000);
*/
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_activity.this,MainActivity.class);
                startActivity(intent);
                finish();
                //checkCredentials();
            }
        };


        Timer tiempo = new Timer();
        tiempo.schedule(task, 3000);


        //loadPreferences();
    }

    /*private void loadPreferences(){
        preferences = getSharedPreferences("Login credentials", Context.MODE_PRIVATE);
    }

    private void checkCredentials(){

        String nickname =preferences.getString("userNickname", null);

        if (nickname != null){
            goMainActivity();
        }
        else{
            goLoginActivity();
        }
    }*/
    private void goMainActivity(){
        Intent intent = new Intent(splash_activity.this, showMuseosActivity.class);
        startActivity(intent);
        finish();
    }
    private void goLoginActivity(){
        Intent intent = new Intent(splash_activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}