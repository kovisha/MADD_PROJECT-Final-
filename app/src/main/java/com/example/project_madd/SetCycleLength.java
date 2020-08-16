package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SetCycleLength extends AppCompatActivity {

    Button inc1, dec1, pCycleLengthConfirm;
    TextView tv1;
    int count1;
    TextView calCycleLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_cycle_length);

        Intent intent = getIntent(); //get intent from settings home

        inc1 = findViewById(R.id.increment2);
        dec1 = findViewById(R.id.decrement2);

        tv1 = findViewById(R.id.calCycleLength);

        inc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count1++;
                tv1.setText(count1 + " Days");
            }
        });

        dec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count1--;
                tv1.setText(count1 + " Days");
            }
        });

    }

    public void confirmCycleLength(View view) {

        pCycleLengthConfirm = findViewById(R.id.CLengthSetButtn);
        pCycleLengthConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calCycleLength = findViewById(R.id.calCycleLength);
                String userCycleLength = calCycleLength.getText().toString();

                Intent intent = new Intent(SetCycleLength.this, PeriodSettingsHome.class);

                intent.putExtra("CycleSetLength", userCycleLength);

                startActivity(intent);

            }
        });

    }

}