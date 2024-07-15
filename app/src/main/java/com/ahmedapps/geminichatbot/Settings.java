package com.ahmedapps.geminichatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void onClickLogOut(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void onClickLocation(View view) {
        Intent intent = new Intent(this, GPS.class);
        startActivity(intent);
    }

    public void onContinueLocation(View view) {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }
}