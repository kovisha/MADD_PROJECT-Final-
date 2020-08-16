package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoard extends AppCompatActivity {

    Button btnP ,btnS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Intent myIntent = getIntent(); //gets the intent from common Attribute page
    }

    public void PeriodHome(View view){ // Navigate to period home page
         btnP = findViewById(R.id.btnMens);
         btnP.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(DashBoard.this , MenstrualHome.class);

                 startActivity(intent);
             }
         });

    }

    public void settingsHome(View view){

        btnS = findViewById(R.id.settingsHome);
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this , PeriodSettingsHome.class);
                startActivity(intent);
            }
        });
        }
}