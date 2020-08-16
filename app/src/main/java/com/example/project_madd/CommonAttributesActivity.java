package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CommonAttributesActivity extends AppCompatActivity {

    Button btnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_attributes);

        Intent myIntent = getIntent(); //gets the intent from main Activity Page
    }

    public void toDashboard(View view){ //adding record will redirect to common dashboard page

        btnd = findViewById(R.id.dashboardbtn);
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommonAttributesActivity.this , DashBoard.class);

                startActivity(intent);
            }
        });


    }

}