package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class SetCycleLength extends AppCompatActivity {

    Button inc1, dec1, pCycleLengthConfirm;
    TextView tv1;
    int count1;
    TextView calCycleLength;
    SwitchCompat simpleSwitch;

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

    public void onSwitchMoment(View view){
        simpleSwitch = findViewById(R.id.switch2);

        boolean result = simpleSwitch.isChecked();

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean result) {

                Intent switchIntent = new Intent(SetCycleLength.this,PeriodSettingsHome.class);

                switchIntent.putExtra("AverageValue",28);
                startActivity(switchIntent);
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
        else
            return super.onOptionsItemSelected(item);
    }



}