package com.nested.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    @Override
    public  boolean onSupportNavigateUp()
    {onBackPressed();
        return super.onSupportNavigateUp();}
}
