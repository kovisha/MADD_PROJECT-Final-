package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class bmi_Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi__home);
        Button btn_bmi;
        btn_bmi = findViewById(R.id.bmi_btn1);


        btn_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bmi_Home.this, bmi_calculation.class);
                startActivity(intent);
            }
        });

        Button btn_bmr ;
        btn_bmr = findViewById(R.id.bmr_btn1);
        btn_bmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bmi_Home.this, bmr_calculation.class);
                startActivity(intent);
            }
        });


        Button btn_wth;
        btn_wth = findViewById(R.id.wth_btn1);
        btn_wth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(bmi_Home.this, waistHeight_calculation.class);
                startActivity(intent);
            }
        });
    }



}