package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class bmr_calculation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr_calculation);

        Intent i = getIntent();

        Button done ;
        done = findViewById(R.id.bmr_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bmr_calculation.this, bmi_Home.class);
                startActivity(intent);
            }
        });
        Button done2;
        done2 = findViewById(R.id.bmr_done2);
        done2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bmr_calculation.this, bmi_tips.class);
                startActivity(intent);
            }
        });

    }



}