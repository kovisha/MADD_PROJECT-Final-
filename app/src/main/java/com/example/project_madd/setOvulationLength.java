package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class setOvulationLength extends AppCompatActivity {

    Button ovuleLengthSet , incFertile , deFertile;
    TextView fertilityLength , tv4;
    int counterFertile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ovulation_length);

        Intent intent = getIntent();

        incFertile = findViewById(R.id.incrementer2);
        deFertile = findViewById(R.id.decrementer2);

        tv4 = findViewById(R.id.textView5);

        incFertile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterFertile++;
                tv4.setText(counterFertile + " Days");
            }
        });

        deFertile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterFertile--;
                tv4.setText(counterFertile + " Days");
            }
        });
    }


    public void confirmFertilityLength(View view){

        ovuleLengthSet = findViewById(R.id.FertileLengthbtnSet);
        ovuleLengthSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fertilityLength = findViewById(R.id.textView5);
                String userFertilityLength = fertilityLength.getText().toString();

                Intent intent = new Intent(setOvulationLength.this , PeriodSettingsHome.class);

                intent.putExtra("FertilitySetLength" , userFertilityLength);

                startActivity(intent);

            }
        });

    }
}