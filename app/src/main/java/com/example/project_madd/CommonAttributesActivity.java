package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CommonAttributesActivity extends AppCompatActivity {

    Button btnDash;
    Button btnSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_attributes);

        Intent myIntent = getIntent();
    }



    public void toDashboard(View view){

        btnDash = findViewById(R.id.dashboardbtn);
        btnDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommonAttributesActivity.this , DashBoard.class);

                startActivity(intent);
            }
        });

    }

    public void goToSettings(View view){

        btnSets = findViewById(R.id.btnSettings);
        btnSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CommonAttributesActivity.this , Settings.class);
                startActivity(i);
            }
        });


    }

}