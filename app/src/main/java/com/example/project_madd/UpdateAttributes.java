package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

public class UpdateAttributes extends AppCompatActivity {

    EditText height_edit, weight_edit, waist_edit;
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

             double w = Double.parseDouble(weight);
             double h = Double.parseDouble(height);
             double wh = Double.parseDouble(waist);


            height_edit.setText(String.format("%.2f", h) );
            weight_edit.setText(String.format("%.2f", w));
            waist_edit.setText(String.format("%.2f", wh));


        }


    }


    public void updateInfo(View view) {

        DBOpenHelper dbHelper = new DBOpenHelper(this);

        int val = dbHelper.updateValues(height_edit.getText().toString(),weight_edit.getText().toString(),waist_edit.getText().toString());

        if (val > 0) {

            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateAttributes.this, bmi_Home.class);
            startActivity(intent);

        } else {

            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }


    }

}
