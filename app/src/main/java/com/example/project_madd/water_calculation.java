package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class water_calculation extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    Button waterCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_calculation);

        //Spinner spinner = (Spinner) findViewById(R.id.UnitSpinner);

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
          //      R.array.planets_array, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // spinner.setAdapter(adapter);
        //spinner for units
       Spinner spinnerUnit = (Spinner) findViewById(R.id.UnitSpinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(water_calculation.this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.units_array));

        myAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnerUnit.setAdapter(myAdapter);
        spinnerUnit.setOnItemSelectedListener(this);

        //spinner for exercise
        Spinner spinnerExercise = (Spinner) findViewById(R.id.exercise_spinner);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(water_calculation.this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.exercise_array));

        myAdapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinnerExercise.setAdapter(myAdapter2);


    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(water_calculation.this , Settings.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(water_calculation.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else
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