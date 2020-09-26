package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

public class bmr_calculation extends AppCompatActivity {

    TextView tv;
    EditText bmrEt ,cal ;
    RadioGroup group ;
    double constant = 0;
    double calorie = 0;
    double v = 0;
    double val =0 ;
    double getVal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr_calculation);

        Intent i = getIntent();

        Button done,ins;
        done = findViewById(R.id.bmr_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bmr_calculation.this, bmi_Home.class);
                startActivity(intent);
            }
        });

        ins = findViewById(R.id.instructions);
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bmr_calculation.this,bmr_instructions.class);
                startActivity(intent);
            }
        });





        /*****************retrieve bmr from database *************/
        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();

        Cursor cursor = dbOpenHelper.readBmr(sqLiteDatabase);



        while (cursor.moveToNext()) {

            String bmr = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_BMR));
            String bmi = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_BMI));

            v = Double.parseDouble(bmr);
            String v1 = String.format("%.2f", v);

            val = Double.parseDouble(bmi);

            bmrEt = findViewById(R.id.bmrval);
            bmrEt.setText(v1);

            tv = findViewById(R.id.bmr_comment);


            if (val < 18.5) {
                tv.setText("Follow Group C Instructions ...");
            } else if (val >= 18.5 && val < 25) {
                tv.setText("Follow Group A Instructions ...");
            } else {
                tv.setText("Follow Group B Instructions ...");
            }

        }


    }

    public void caloriesCalculate(View view){

        group = findViewById(R.id.activity);
        if(group.getCheckedRadioButtonId() == R.id.sedentary){
            constant = 1.2 ;
            calorie = v * constant;
        }
        if(group.getCheckedRadioButtonId() == R.id.light){
            constant = 1.37;
            calorie = v * constant;
        }
        if(group.getCheckedRadioButtonId() == R.id.moderate){
            constant = 1.55 ;
            calorie = v * constant;
        }
        if(group.getCheckedRadioButtonId() == R.id.active){
            constant = 1.72 ;
            calorie = v * constant;
        }
        if(group.getCheckedRadioButtonId() == R.id.extremeActive){
            constant = 1.9 ;
            calorie = v * constant;
        }


        String calo = String.format("%.2f",calorie);
        cal = findViewById(R.id.calPerDay);
        cal.setText(calo);




    }


}