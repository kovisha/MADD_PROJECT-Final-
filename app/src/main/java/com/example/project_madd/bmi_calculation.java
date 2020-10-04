package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

    TextView bmi_value , ideal_w;
    TextView tv;

    TextView result1,result2,result3,result4,result5,result6,result7,result8;
    TextView result1_1,result2_1,result3_1,result4_1,result5_1,result6_1,result7_1,result8_1;
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


            if(v<16){
                result1= findViewById(R.id.textclass1_1);
                result1.setTextColor(Color.RED);
                result1_1 = findViewById(R.id.textclass1_2);
                result1_1.setTextColor(Color.RED);

            }
            else if(16<=v && v<=16.9){
                result2= findViewById(R.id.textclass2_1);
                result2.setTextColor(Color.RED);
                result2_1 = findViewById(R.id.textclass2_2);
                result2_1.setTextColor(Color.RED);

            }
            else if(17<=v && v<=18.4){
                result3= findViewById(R.id.textclass3_1);
                result3.setTextColor(Color.RED);
                result3_1 = findViewById(R.id.textclass3_2);
                result3_1.setTextColor(Color.RED);

            }
            else if(18.5<=v && v<=24.9){
                result4= findViewById(R.id.textclass4_1);
                result4.setTextColor(Color.GREEN);
                result4_1 = findViewById(R.id.textclass4_2);
                result4_1.setTextColor(Color.GREEN);

            }
            else if(25<=v && v<=29.9){
                result5= findViewById(R.id.textclass5_1);
                result5.setTextColor(Color.RED);
                result5_1 = findViewById(R.id.Textclass5_2);
                result5_1.setTextColor(Color.RED);

            }
            else if(30<=v && v<=34.9){
                result6= findViewById(R.id.textclass6_1);
                result6.setTextColor(Color.RED);
                result6_1 = findViewById(R.id.textclass6_2);
                result6_1.setTextColor(Color.RED);

            }

            else if(35<=v && v>39.9){
                result7= findViewById(R.id.textclass7_1);
                result7.setTextColor(Color.RED);
                result7_1 = findViewById(R.id.textclass7_2);
                result7_1.setTextColor(Color.RED);

            }

            else{
                result8= findViewById(R.id.textclass8_1);
                result8.setTextColor(Color.RED);
                result8_1 = findViewById(R.id.textclass8_2);
                result8_1.setTextColor(Color.RED);

            }



        }


    }

    /********************************************************Menu code************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(bmi_calculation.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(bmi_calculation.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(bmi_calculation.this , User_profile.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }


}