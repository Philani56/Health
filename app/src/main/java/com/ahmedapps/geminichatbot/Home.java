package com.ahmedapps.geminichatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Home extends AppCompatActivity {

    private TextView txtCurrentDate;
    private TextView txtCurrentTime;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtCurrentDate = findViewById(R.id.txtCurrentDate);
        txtCurrentTime = findViewById(R.id.txtCurrentTime);

        // Set current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        txtCurrentDate.setText(currentDate);

        // Initialize the handler and runnable for updating time
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000); // Update every second
            }
        };

        // Start the runnable
        handler.post(runnable);
    }

    private void updateTime() {
        // Set current time
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
        String currentTime = timeFormat.format(new Date());
        txtCurrentTime.setText(currentTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the runnable when the activity is destroyed to prevent memory leaks
        handler.removeCallbacks(runnable);
    }

    public void onClickProfile(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void onClickAppointment(View view) {
        Intent intent = new Intent(this, Appointment.class);
        startActivity(intent);
    }

    public void onClickDoctor(View view) {
        Intent intent = new Intent(this, Doctor.class);
        startActivity(intent);
    }

    public void onClickBot(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCalendar(View view) {
        Intent intent = new Intent(this, News.class);
        startActivity(intent);
    }

    public void onClickSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}