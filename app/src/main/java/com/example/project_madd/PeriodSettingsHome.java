package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PeriodSettingsHome extends AppCompatActivity {

    Button PeriodLength , CycleLength , OvuLength;
    String periodLength , PCycleLength , fertilityLength;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_settings_home);

        Intent intent = getIntent();

        periodLength = intent.getStringExtra("PeriodSetLength"); //getting the user decided period length

        TextView tv = findViewById(R.id.getPeriodLength);
        tv.setText(periodLength);

        PCycleLength = intent.getStringExtra("CycleSetLength"); //getting the user decided cycle length

        TextView tv1 = findViewById(R.id.getCycleLength);
        tv1.setText(PCycleLength);

        fertilityLength = intent.getStringExtra("FertilitySetLength"); //getting the user decided fertile length

        TextView tv2 = findViewById(R.id.getOvulationLength);
        tv2.setText(fertilityLength);

    }

    public void PeriodLengthSet(View view){ //to set period length navigate to period length setting page

        PeriodLength = findViewById(R.id.PLengthSet);
        PeriodLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PeriodSettingsHome.this , SetPeriodLength.class);

                startActivity(intent);
            }
        });

    }

    public void CycleLengthSet(View view){

        CycleLength = findViewById(R.id.PCyclelnSet);
        CycleLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PeriodSettingsHome.this, SetCycleLength.class);

                startActivity(intent);
            }
        });
    }

    public void OvulaionlengthSet(View view){

        OvuLength = findViewById(R.id.button3);
        OvuLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PeriodSettingsHome.this , setOvulationLength.class);
                startActivity(intent);
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
            Intent intent = new Intent(PeriodSettingsHome.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(PeriodSettingsHome.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}
