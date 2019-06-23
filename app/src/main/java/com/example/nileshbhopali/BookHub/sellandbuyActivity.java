package com.example.nileshbhopali.BookHub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sellandbuyActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellandbuy);

        b1 = (Button)findViewById(R.id.donate);
        b2 = (Button)findViewById(R.id.purchase1);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent in = new Intent(getApplicationContext(),DonateActivity.class);
        startActivity(in);
    }
    public void next1(View view1)
    {
        Intent intent=new Intent(sellandbuyActivity.this,MainActivity.class);
        startActivity(intent);


    }
}
