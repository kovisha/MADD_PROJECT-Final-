package com.example.project_madd;

import androidx.annotation.NonNull;
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
import android.widget.TextView;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

public class bmi_tips extends AppCompatActivity {

    TextView tip1, tip2, tip3, tip4, tip5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_tips);

        Button done;
        done = findViewById(R.id.tips_gotIt);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bmi_tips.this, bmi_Home.class);
                startActivity(intent);
            }
        });

        /*****************retrieve bmi and show tips  *************/
        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();

        Cursor cursor = dbOpenHelper.readBmi(sqLiteDatabase);

        while (cursor.moveToNext()) {

            String bmi = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_BMI));
            double v = Double.parseDouble(bmi);

            tip1 = findViewById(R.id.tip1);
            tip2 = findViewById(R.id.tip2);
            tip3 = findViewById(R.id.tip4);
            tip4 = findViewById(R.id.tip5);
            tip5 = findViewById(R.id.tip6);


            if (v < 18.5) {
                tip1.setText("Eat more frequently");
                tip2.setText("Choose nutrient-rich foods.");
                tip3.setText("Don’t skip breakfast and eat on time..");
                tip4.setText("Eat whole grains more often..");
                tip5.setText("Watch when you drink.-..");

            } else if (v >= 18.5 && v < 25) {
                tip1.setText("Maintain your calorie level..");
                tip2.setText("Make half your plate !! fruits and vegetables..");
                tip3.setText("Switch to fat free...");
                tip4.setText("Eat whole grains more often..");
                tip5.setText("Always Listen to your body..");

            } else {
                tip1.setText("Encourage eating slowly and only when hungry...");
                tip2.setText("Swap a few foods at a time");
                tip3.setText(" Consult a dietitian..");
                tip4.setText("Increase your protein intake");
                tip5.setText("Exercise daily...");
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
            Intent intent = new Intent(bmi_tips.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(bmi_tips.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(bmi_tips.this , User_profile.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}