package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    Switch switch1;
    Button btnUnits , btnExercise, btnGoals;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        myDialog = new Dialog(this);
        //reminder Switch Method
        switch1 = (Switch) findViewById(R.id.reminderSwitch);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    Toast.makeText(getApplicationContext(),"Reminders On" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Reminders Off" , Toast.LENGTH_SHORT).show();
                }
            }
        });


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
            Intent intent = new Intent(Settings.this , Settings.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(Settings.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }


    //navigate to change goals
    public void changeGoals(View view){

        btnGoals = findViewById(R.id.btnChangeGoals);
        btnGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Settings.this,Water_home.class);
                startActivity(intent1);
            }
        });

    }


    //navigate to change units
        /*public void changeUnits(View view){
            btnUnits = findViewById(R.id.btnChangeUnits);
            btnUnits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = new Intent(Settings.this,settings_changeUnit.class);
                    startActivity(intent2);
                }
            });
        }*/


    //navigate to change exercise time
       /*public void changeExerciseTime(View view){
        btnExercise = findViewById(R.id.btnChangeExerciseTime);
        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Settings.this, settings_changeExercise.class);
                startActivity(intent3);
            }
        });
       }*/

    public void changeExercise(View view){
        //btnExercise = findViewById(R.id.btnChangeExerciseTime);
        myDialog.setContentView(R.layout.popup);
        btnExercise = (Button)myDialog.findViewById(R.id.btnChangeExerciseTime);
        Button btnReset = (Button)myDialog.findViewById(R.id.btnSetTime);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        myDialog.show();
    }

    public void changeUnits(View view){
        myDialog.setContentView(R.layout.popup_unit);
        btnUnits = (Button)myDialog.findViewById(R.id.btnChangeUnits);
        Button btnReset = (Button)myDialog.findViewById(R.id.btnSetUnit);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        myDialog.show();
    }

}