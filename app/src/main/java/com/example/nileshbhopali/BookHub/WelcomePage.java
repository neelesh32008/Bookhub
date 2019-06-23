package com.example.nileshbhopali.BookHub;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        t=(TextView)findViewById(R.id.msg);

        String name=getIntent().getExtras().getString("name");
        t.setText(name);


    }

    public void logOut(View view)
    {
        new User(WelcomePage.this).removeUser();
        Intent intent=new Intent(WelcomePage.this,Login.class);
        startActivity(intent);
        finish();

    }


}
