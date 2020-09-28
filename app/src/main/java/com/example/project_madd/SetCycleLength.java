package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;

public class SetCycleLength extends AppCompatActivity {


    Button btSave,btGo;
    Button inc1, dec1, pCycleLengthConfirm;
    TextView tv1;
    int count1;
     Switch CycleSwitch;
    String enteredCycleLength;

    SharedPreferences sharedPreferences;

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
                String userCycleLength = Integer.toString(count1);
                tv1.setText(userCycleLength);

            }
        });

        dec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count1--;
                String userCycleLength = Integer.toString(count1);
                tv1.setText(userCycleLength);
            }
        });

        btSave = findViewById(R.id.SaveBtn);
        btGo = findViewById(R.id.CLengthSetButtn);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Value",tv1.getText().toString());
                editor.apply();
                Toast.makeText(getApplicationContext(), "Your Preference Saved", Toast.LENGTH_SHORT).show();
            }
        });

        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enteredCycleLength=  tv1.getText().toString();
                Integer CUserLength = Integer.parseInt(enteredCycleLength);
                addUserCycle(CUserLength);

                Intent intent = new Intent(getApplicationContext(),PeriodSettingsHome.class);
                startActivity(intent);
            }
        });

        /**********************Saving the state of switch for avg cycle length******************************************/
        CycleSwitch= findViewById(R.id.switch2);

        SharedPreferences sharedPreferences = getSharedPreferences("CycleSwitch",MODE_PRIVATE);
        CycleSwitch.setChecked(sharedPreferences.getBoolean("EnableAvgCycle",true));

        CycleSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CycleSwitch.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("CycleSwitch",MODE_PRIVATE).edit();
                    editor.putBoolean("EnableAvgCycle",true);
                    editor.apply();
                    CycleSwitch.setChecked(true);
                    Toast.makeText(getApplicationContext(), "Average Lengths Set", Toast.LENGTH_SHORT).show();
                }

                else{
                    SharedPreferences.Editor editor = getSharedPreferences("CycleSwitch",MODE_PRIVATE).edit();
                    editor.putBoolean("EnableAvgCycle",false);
                    editor.apply();
                    CycleSwitch.setChecked(false);
                    Toast.makeText(getApplicationContext(), "Your Choice is added", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    /**********User Period Length update method************************/
    public void addUserCycle(Integer CLengthUser) {

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

        long val = dbOpenHelper.updateUserCycleLength( CLengthUser);

        if (val > 0) {
            Toast.makeText(getApplicationContext(), " User Cycle length update success", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), " User Cycle Length update failed", Toast.LENGTH_SHORT).show();
        }


    }




 /*public void switchConfirmCycleLength(View view){

     simpleSwitch = findViewById(R.id.switch2);//getting the id of switch

     //Save switch state in shared preferences
     SharedPreferences sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
     simpleSwitch.setChecked(sharedPreferences.getBoolean("value",true));

     simpleSwitch.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             if(simpleSwitch.isChecked()){
                 SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                 editor.putBoolean("value",true);
                 editor.apply();
                 simpleSwitch.setChecked(true);

                 Toast.makeText(SetCycleLength.this, "switchActivated", Toast.LENGTH_SHORT).show();

                 Intent switchIntent = new Intent(SetCycleLength.this, PeriodSettingsHome.class);

                 switchIntent.putExtra("AverageValue", 28);
                 startActivity(switchIntent);

             }
             else{
                 SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                 editor.putBoolean("value",false);
                 editor.apply();
                 simpleSwitch.setChecked(false);

             }
         }
     });

 }*/

 /*public void buttoncnfrmCycleLength(View view){
     pCycleLengthConfirm = findViewById(R.id.CLengthSetButtn);

     pCycleLengthConfirm.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             calCycleLength = findViewById(R.id.calCycleLength); //getting the user input cycle length

              String userCycleLength = calCycleLength.getText().toString();


             Toast.makeText(SetCycleLength.this, "switchInActivated", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(SetCycleLength.this, PeriodSettingsHome.class);

             intent.putExtra("CycleSetLength", userCycleLength);

             startActivity(intent);

         }
     });

 }*/







    public void cycleInfoMessage(View view){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Average Length");

        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_info_24);

        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Turn on the option,use average value to predict your next menstrual cycle");
        alertDialogBuilder.setCancelable(true);

        Dialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

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
            Intent intent = new Intent(SetCycleLength.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(SetCycleLength.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(SetCycleLength.this , User_profile.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }



}