package com.example.nileshbhopali.BookHub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        final User user=new User(SplashActivity.this);
        Timer timer=new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                if(user.getName()!="")
                {
                    Intent intent = new Intent(SplashActivity.this, WelcomePage.class);
                    intent.putExtra("name",user.getName());
                    startActivity(intent);
                    finish();
                }

                else
                {
                    Intent intent = new Intent(SplashActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);

    }
}
