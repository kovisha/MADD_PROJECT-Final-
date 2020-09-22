package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

public class setOvulationLength extends AppCompatActivity {

    Button btSave,btGo;
    Button ovuleLengthSet , incFertile , deFertile;
    TextView fertilityLength , tv4;
    int counterFertile;

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

        btSave = findViewById(R.id.SaveBtn);
        btGo = findViewById(R.id.FertileLengthbtnSet);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("SaveData2", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Value2",tv4.getText().toString());
                editor.apply();
            }
        });

        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getApplicationContext(),PeriodSettingsHome.class);
                startActivity(myintent);
            }
        });


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
        else
            return super.onOptionsItemSelected(item);
    }
}
