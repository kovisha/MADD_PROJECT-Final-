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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;

public class SetPeriodLength extends AppCompatActivity {

    Button btSave,btGo;
    Button inc , dec ;
    TextView tv;
    int count;

    String enteredPeriodLength;
    SwitchCompat savePeriodLength;

    SharedPreferences sharedPreferences;

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
               String userPeriodLength = Integer.toString(count);
               tv.setText(userPeriodLength);

           }
       });

       dec.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               count--;
               String userPeriodLength = Integer.toString(count);
               tv.setText(userPeriodLength);

           }
       });

       btSave = findViewById(R.id.SaveBtn);
       btGo=findViewById(R.id.PlengthSettingBtn);

       btSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sharedPreferences = getSharedPreferences("SaveData3", Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString("Value3",tv.getText().toString());
               editor.apply();
               Toast.makeText(getApplicationContext(), "Your Preference Saved", Toast.LENGTH_SHORT).show();
           }
       });

       btGo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               enteredPeriodLength=  tv.getText().toString();
               Integer PUserLength = Integer.parseInt(enteredPeriodLength);
               addUserPeriod(PUserLength);

               Intent intent = new Intent(getApplicationContext(),PeriodSettingsHome.class);
               startActivity(intent);
           }
       });

        /**********************Saving the state of switch for avg period length******************************************/
       savePeriodLength = findViewById(R.id.PeriodSwitch);

        SharedPreferences sharedPreferences = getSharedPreferences("PeriodSwitch",MODE_PRIVATE);
        savePeriodLength.setChecked(sharedPreferences.getBoolean("EnableAvgPeriod",true));

       savePeriodLength.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(savePeriodLength.isChecked()){
                   SharedPreferences.Editor editor = getSharedPreferences("PeriodSwitch",MODE_PRIVATE).edit();
                   editor.putBoolean("EnableAvgPeriod",true);
                   editor.apply();
                   savePeriodLength.setChecked(true);
                   Toast.makeText(getApplicationContext(), "Average Settings On", Toast.LENGTH_SHORT).show();
               }

               else{
                   SharedPreferences.Editor editor = getSharedPreferences("PeriodSwitch",MODE_PRIVATE).edit();
                   editor.putBoolean("EnableAvgPeriod",false);
                   editor.apply();
                   savePeriodLength.setChecked(false);
                   Toast.makeText(getApplicationContext(), "Your Choice will be added", Toast.LENGTH_SHORT).show();
               }
           }
       });



    }

    /**********User Period Length update method************************/
    public void addUserPeriod(Integer PLengthUser) {

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

            long val = dbOpenHelper.updateUserPeriodLength( PLengthUser);

            if (val > 0) {
                Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "Update failed.Please retry!", Toast.LENGTH_SHORT).show();
            }


    }

    public void PeriodInfoMessage(View view){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Average Length");

        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_info_24);

        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Turn on the option,use average value as your period length ");
        alertDialogBuilder.setCancelable(true);

        Dialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    /**********************Menu code************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(SetPeriodLength.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(SetPeriodLength.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(SetPeriodLength.this , User_profile.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

}