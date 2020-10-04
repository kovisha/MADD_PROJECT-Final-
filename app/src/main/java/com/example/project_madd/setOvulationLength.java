package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;

public class setOvulationLength extends AppCompatActivity {

    Button btSave,btGo;
    Button  incFertile , deFertile;
    TextView  tv4;
    int counterFertile;
    String enteredFertileLength;
    SwitchCompat ovulationSave;

    SharedPreferences sharedPreferences;


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
                String userOvuleLength = Integer.toString(counterFertile);
                tv4.setText(userOvuleLength);

            }
        });

        deFertile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterFertile--;
                String userOvuleLength = Integer.toString(counterFertile);
                tv4.setText(userOvuleLength);
            }
        });

        btSave = findViewById(R.id.SaveBtn);
        btGo = findViewById(R.id.FertileLengthbtnSet);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("SaveData2", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Value2",tv4.getText().toString());
                editor.apply();
                Toast.makeText(getApplicationContext(), "Your Preference Saved", Toast.LENGTH_SHORT).show();
            }
        });

        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enteredFertileLength =  tv4.getText().toString();
                Integer OUserLength = Integer.parseInt(enteredFertileLength);
                addUserOvulation(OUserLength);

                Intent myintent = new Intent(getApplicationContext(),PeriodSettingsHome.class);
                startActivity(myintent);
            }
        });

        /**********************Saving the state of switch for avg ovulation length******************************************/
        ovulationSave = findViewById(R.id.OvulationSwitch);

        SharedPreferences sharedPreferences = getSharedPreferences("OvulationSwitch",MODE_PRIVATE);
        ovulationSave.setChecked(sharedPreferences.getBoolean("EnableAvgOvulation",true));

        ovulationSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ovulationSave.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("OvulationSwitch",MODE_PRIVATE).edit();
                    editor.putBoolean("EnableAvgOvulation",true);
                    editor.apply();
                    ovulationSave.setChecked(true);
                    Toast.makeText(getApplicationContext(), "Average Settings On", Toast.LENGTH_SHORT).show();
                }

                else{
                    SharedPreferences.Editor editor = getSharedPreferences("OvulationSwitch",MODE_PRIVATE).edit();
                    editor.putBoolean("EnableAvgOvulation",false);
                    editor.apply();
                    ovulationSave.setChecked(false);
                    Toast.makeText(getApplicationContext(), "Your Choice will be added", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    /**********User Ovulation Length update method************************/
    public void addUserOvulation(Integer OLengthUser) {

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

        long val = dbOpenHelper.updateUserOvulationLength( OLengthUser);

        if (val > 0) {
            Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Update failed.Please retry!", Toast.LENGTH_SHORT).show();
        }


    }






    /*public void confirmFertilityLength(View view){

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

    }*/

    public void OvulationInfoMessage(View view){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Average Length");

        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_info_24);

        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Turn on the option,use average value to predict your next ovulation");
        alertDialogBuilder.setCancelable(true);

        Dialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    /*******************menu code************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(setOvulationLength.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(setOvulationLength.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(setOvulationLength.this , User_profile.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }
}
