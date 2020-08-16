package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class water_calculation extends AppCompatActivity {


    Button waterCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_calculation);

        //spinner for units
        Spinner spinnerUnit = (Spinner) findViewById(R.id.UnitSpinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(water_calculation.this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.units_array));

        myAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnerUnit.setAdapter(myAdapter);


        //spinner for exercise
        Spinner spinnerExercise = (Spinner) findViewById(R.id.exercise_spinner);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(water_calculation.this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.exercise_array));

        myAdapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnerExercise.setAdapter(myAdapter2);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.menuHome){
            startActivity(new Intent(this,viewMyWater.class));
        }

        if(menuId == R.id.menuSettings){
            startActivity(new Intent(this,Settings.class));
        }

        return super.onOptionsItemSelected(item);
    }


    public void waterCalcDone(View view){
        waterCalc = findViewById(R.id.btnWaterCalcDone);
        waterCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(water_calculation.this
                        , viewMyWater.class);

                startActivity(intent);
            }
        });

    }











}