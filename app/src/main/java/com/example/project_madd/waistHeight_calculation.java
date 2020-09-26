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
import android.widget.TextView;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

public class waistHeight_calculation extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waist_height_calculation);

        Intent i = getIntent();

        Button done ;
        done = findViewById(R.id.waist_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(waistHeight_calculation.this, bmi_Home.class);
                startActivity(intent);
            }
        });


    /*****************retrieve wth from database *************/

    DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
    SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();

    Cursor cursor = dbOpenHelper.readWTH(sqLiteDatabase);

    while(cursor.moveToNext()){

        String wth = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_WHPERCENTAGE));
        double Wth = Double.parseDouble(wth);
        String lastvar = String.format("%.2f",Wth)+ "%";
        tv =findViewById(R.id.WTHpercentage);
        tv.setText(lastvar);


    }



    }

}