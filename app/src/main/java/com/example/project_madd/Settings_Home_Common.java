package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class    Settings_Home_Common extends AppCompatActivity {

    Button periodSettings,bmiSettings,waterSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__home__common);


        periodSettings = findViewById(R.id.btnPeriodTrack);
        bmiSettings = findViewById(R.id.btnBMITrack);
        waterSettings = findViewById(R.id.btnWaterTrack);

        /******************************Navigate to period settings home**************************************/

        periodSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings_Home_Common.this,PeriodSettingsHome.class);
                startActivity(intent);
            }
        });

        waterSettings = findViewById(R.id.btnWaterTrack);
        waterSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings_Home_Common.this,Settings.class);
                startActivity(intent);
            }
        });

    }

    /********************************************************Menu code************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(Settings_Home_Common.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(Settings_Home_Common.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(Settings_Home_Common.this , User_profile.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }






}