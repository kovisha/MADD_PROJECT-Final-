package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings_Home_Common extends AppCompatActivity {

    Button periodSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__home__common);



    }

    /******************************Navigate to period settings home**************************************/

    public void navigatePeriodSettings(View view){

        periodSettings = findViewById(R.id.settingsHome);

        periodSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings_Home_Common.this,PeriodSettingsHome.class);
                startActivity(intent);
            }
        });
    }
}