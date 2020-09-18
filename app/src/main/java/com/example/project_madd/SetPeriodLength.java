package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        else
            return super.onOptionsItemSelected(item);
    }

}