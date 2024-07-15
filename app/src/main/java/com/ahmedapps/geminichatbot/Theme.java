package com.ahmedapps.geminichatbot;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class Theme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load theme from preferences
        SharedPreferences preferences = getSharedPreferences("theme", MODE_PRIVATE);
        boolean isDarkTheme = preferences.getBoolean("isDarkTheme", false);
        if (isDarkTheme) {
            setTheme(R.style.Theme_Dark);
        } else {
            setTheme(R.style.Theme_Light);
        }

        setContentView(R.layout.activity_theme);

        findViewById(R.id.themeToggleButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toggle theme
                boolean isDarkTheme = preferences.getBoolean("isDarkTheme", false);
                SharedPreferences.Editor editor = preferences.edit();
                if (isDarkTheme) {
                    setTheme(R.style.Theme_Light);
                    editor.putBoolean("isDarkTheme", false);
                } else {
                    setTheme(R.style.Theme_Dark);
                    editor.putBoolean("isDarkTheme", true);
                }
                editor.apply();

                // Restart activity to apply theme
                recreate();
            }
        });
    }
}