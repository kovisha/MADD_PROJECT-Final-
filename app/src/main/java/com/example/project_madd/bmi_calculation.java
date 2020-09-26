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
import android.widget.TextView;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

import java.text.DecimalFormat;

public class bmi_calculation extends AppCompatActivity {

  EditText bmi_value , ideal_w;
  TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculation);

        Intent i = getIntent();

        Button tips;
        tips = findViewById(R.id.tips_bmi);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bmi_calculation.this, bmi_tips.class);
                startActivity(intent);
            }
        });

        Button done;
        done = findViewById(R.id.bmi_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bmi_calculation.this, bmi_Home.class);
                startActivity(intent);
            }
        });


        /*****************retrieve bmi from database *************/
        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();

        Cursor cursor = dbOpenHelper.readBmi(sqLiteDatabase);

        while (cursor.moveToNext()) {

            String bmi = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_BMI));
            String height = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_HEIGHT));

            double v = Double.parseDouble(bmi);
            String v1 = String.format("%.2f" ,v);

            bmi_value = findViewById(R.id.val_bmi);
            bmi_value.setText(v1);

            double v2 = (Double.parseDouble(height) / 100);
            double percentage = ((2.2 * v) + (3.5*v)*(v2-1.5));

            String v3 = String.format("%.2f" ,percentage)+"Kg";


            ideal_w = findViewById(R.id.val_ideal);
            ideal_w.setText(v3);

            tv= findViewById(R.id.text_bmiComment);

            if (v<18.5)
            {
                tv.setText("You are under Weight !!!");
            }
            else if (v>=18.5&&v<25){
                tv.setText("Great Shape! Congratulations !!");
            }
            else {
                tv.setText("Over Weight , Time for a run!!!");
            }


        }


    }

}