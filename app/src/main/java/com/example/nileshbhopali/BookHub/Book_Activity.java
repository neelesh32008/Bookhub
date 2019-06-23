package com.example.nileshbhopali.BookHub;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Book_Activity extends AppCompatActivity {

    private TextView tvtitle,tvdescription,tvcategory;
 private ImageView img;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__acticvity);
        mDrawerlayout = (DrawerLayout)findViewById(R.id.drawer1);
        mToggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvtitle =(TextView) findViewById(R.id.txttitle);
        tvdescription=(TextView) findViewById(R.id.txtdesc);
        tvcategory =(TextView) findViewById(R.id.txtcat);
img = (ImageView) findViewById(R.id.bookthumbnail);

    //Recieve data

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail");

        //Setting values

        tvtitle.setText(Title);
        tvdescription.setText(Description);
        img.setImageResource(image);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
