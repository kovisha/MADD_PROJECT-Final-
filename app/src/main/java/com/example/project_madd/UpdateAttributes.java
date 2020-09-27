package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

public class UpdateAttributes extends AppCompatActivity {

    EditText height_edit, weight_edit, waist_edit;
    double bmi = 0;
    double bmr = 0;
    double wth = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_attributes);

        height_edit = findViewById(R.id.heightedit);
        weight_edit = findViewById(R.id.weightedit);
        waist_edit = findViewById(R.id.waistedit);

        /*****************retrieve bmi from database *************/
        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();

        Cursor cursor = dbOpenHelper.UserProfile(sqLiteDatabase);

        while (cursor.moveToNext()) {
            String weight = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_WEIGHT));
            String height = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_HEIGHT));
            String  waist = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_WAIST));
            String gender  = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_GENDER));
            String age = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_AGE));

            double w = Double.parseDouble(weight);
            double h = Double.parseDouble(height);
            double wh = Double.parseDouble(waist);
            int a = Integer.parseInt(age);
            bmi = (w/((h/100)*(h/100)));

            if(gender.equals("Male")){

                bmr  = (double) ((10 * w)+(6.25 * h)-(5 * a)+5 );


            }

            if(gender.equals("Female")){

                bmr  = (float) (66.5+(13.75 * w)+(5.0 * h)-(6.7 *a ));


            }

            wth  = ((w/h)* 100);


            height_edit.setText(String.format("%.2f", h) );
            weight_edit.setText(String.format("%.2f", w));
            waist_edit.setText(String.format("%.2f", wh));


        }


    }


    public void updateInfo(View view) {

        String bmi_update = Double.toString(bmi);
        String bmr_update = Double.toString(bmr);
        String wth_update = Double.toString(wth);


        DBOpenHelper dbHelper = new DBOpenHelper(this);

        int val = dbHelper.updateValues(height_edit.getText().toString(),weight_edit.getText().toString(),waist_edit.getText().toString(),bmi_update,bmr_update,wth_update);

        if (val > 0) {

            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateAttributes.this, bmi_Home.class);
            startActivity(intent);

        } else {

            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(UpdateAttributes.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(UpdateAttributes.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(UpdateAttributes.this , User_profile.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }



}