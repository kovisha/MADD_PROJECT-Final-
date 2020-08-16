package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SetPeriodLength extends AppCompatActivity {

    Button inc , dec , PLenConfirm;
    TextView tv;
    int count;
    TextView periodLength;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_period_length);

        Intent intent = getIntent(); //get intent from settings home

        inc = findViewById(R.id.incrementer1); //increment and decrement number
        dec = findViewById(R.id.decrementer1);

        tv = findViewById(R.id.calculation);
       inc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               count++;
               tv.setText(count + " Days");
           }
       });

       dec.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               count--;
               tv.setText(count+ " Days");
           }
       });

    }

    public void confirmPeriodLength(View view){

            PLenConfirm = findViewById(R.id.PlengthSettingBtn);
            PLenConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    periodLength = findViewById(R.id.calculation);
                    String userPeriodLength = periodLength.getText().toString();

                    Intent intent = new Intent(SetPeriodLength.this , PeriodSettingsHome.class);

                    intent.putExtra("PeriodSetLength" , userPeriodLength);

                    startActivity(intent);

                }
            });

    }

}