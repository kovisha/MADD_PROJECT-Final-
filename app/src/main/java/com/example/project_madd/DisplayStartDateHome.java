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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DisplayStartDateHome extends AppCompatActivity {

    String takeExtra1;
    ImageView v2;
    TextView displayStartDate ,startDateCaption, displayNextStartDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_start_date_home);

        Intent intent = getIntent(); //get intent from add Period activity

        /*************Retrieve start date values from retrieve method here**********************/

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readStartDate(database);

        while(cursor.moveToNext()) {
            String startDate = cursor.getString(cursor.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_START_DATE));

            startDateCaption = findViewById(R.id.getStartDate);
            startDateCaption.setText("Start Date");
            displayStartDate = findViewById(R.id.displayStartDate);
            displayStartDate.setText(startDate);//display the retrieved start date here

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            try{
                c.setTime(sdf.parse(startDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            c.add(Calendar.DAY_OF_MONTH,34); //add  days to predict the next period date.

            String nextPeriodDate=sdf.format(c.getTime());

            displayNextStartDate = findViewById(R.id.displayNextPeriodDate);
            displayNextStartDate.setText(nextPeriodDate); // display the retrieved next start date here
        }


    }

   /******Navigate to add end date activity to register the end date of period by tapping the image*********/

    public void addEndRecord(View view) {
      v2 = findViewById(R.id.imageView2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //navigates to end period adding page
                Intent intent = new Intent(DisplayStartDateHome.this, EndPeriodActivity.class);
                startActivity(intent);
            }
        });


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
            Intent intent = new Intent(DisplayStartDateHome.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(DisplayStartDateHome.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }




}