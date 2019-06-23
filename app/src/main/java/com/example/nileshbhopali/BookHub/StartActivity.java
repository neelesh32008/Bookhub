package com.example.nileshbhopali.BookHub;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {





    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        viewPager=(ViewPager)findViewById(R.id.viewPagaer);

        ViewPagerAdapter viewPagerAdapter =new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
    }

}
