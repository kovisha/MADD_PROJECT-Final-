package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PeriodSettingsHome extends AppCompatActivity {

    Button PeriodLength , CycleLength , OvuLength;
    String periodLength , PCycleLength , fertilityLength ,  AvgCycleLength;
    TextView tvOvule , tvGo,tvPeriod;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_settings_home);

        Intent intent = getIntent();
        Intent myintent = getIntent();



        /**************************************setting the user preferred cyclelength*************************************************/
        tvGo = findViewById(R.id.getCycleLength);

        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);

        String value = result.getString("Value","Data Not found");

        tvGo.setText(value);
        /*********************************************End of user preferred cycleLength*********************************************/




        /**************************************setting the user preferred ovulationLength*************************************************/
        tvOvule = findViewById(R.id.getOvulationLength); //setting the user decided period length

        SharedPreferences result2 = getSharedPreferences("SaveData2", Context.MODE_PRIVATE);

        String value2 = result2.getString("Value2","Data Not found");

        tvOvule.setText(value2);

        /*********************************************End of user preferred ovuleLength*********************************************/


        /**************************************setting the user preferred PeriodLength*************************************************/

        tvPeriod=findViewById(R.id.getPeriodLength);

        SharedPreferences result3 = getSharedPreferences("SaveData3", Context.MODE_PRIVATE);

        String value3 = result3.getString("Value3","Data Not found");

        tvPeriod.setText(value3);


        /*********************************************End of user preferred PeriodLength*********************************************/










        /****************Handling the cycle length with avg and user entered value and switch*****************/

       /* PCycleLength = intent.getStringExtra("CycleSetLength"); //getting the user decided cycle length
        AvgCycleLength = switchIntent.getStringExtra("AverageValue");//getting the avg value through switch

        if(PCycleLength == ""){
            tv1 = findViewById(R.id.getCycleLength);
            tv1.setText(AvgCycleLength);
        }

        else {
            tv1 = findViewById(R.id.getCycleLength);
            tv1.setText(PCycleLength);
        }

        /* End of Handling the cycle length with avg and user entered value and switch*/

        //fertilityLength = intent.getStringExtra("FertilitySetLength"); //getting the user decided fertile length





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
