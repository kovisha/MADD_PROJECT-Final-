package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DisplayStartDateHome extends AppCompatActivity {

    String startDate;
    ImageView v2;
    TextView displayStartDate ,startDateCaption, displayNextStartDate;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_start_date_home);

        SharedPreferences sharedPreferences;


        Intent intent = getIntent(); //get intent from add Period activity

        /*************Retrieve start date values from retrieve method here**********************/

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readStartDate(database);

        while(cursor.moveToNext()) {
             startDate = cursor.getString(cursor.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_START_DATE));

            startDateCaption = findViewById(R.id.getStartDate);
            startDateCaption.setText("Start Date");

            displayStartDate = findViewById(R.id.displayStartDate);
            displayStartDate.setText(startDate);//display the retrieved start date here


           /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

          int menstrualDays = 28;

            Calendar c = Calendar.getInstance();
            try{
               c.setTime(sdf.parse(startDate));

            } catch (ParseException e) {
                e.printStackTrace();
            }


            c.add(Calendar.DAY_OF_MONTH, menstrualDays); //add  days to predict the next period date.

            String nextPeriodDate=sdf.format(c.getTime());*/

/****************************Calling the method to calculate and predict next period date*****************************************/
           String finalNextDate = nextStartDate(startDate,28);

            displayNextStartDate = findViewById(R.id.displayNextPeriodDate);
            displayNextStartDate.setText(finalNextDate); // display the retrieved next start date here

            /**********************************Save nextStart date as a shared preference*************************************************************/
            sharedPreferences = getSharedPreferences("SaveStartDate", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("StartDate",displayNextStartDate.getText().toString());
            editor.apply();
        }


    }

    public String nextStartDate(String startDate , int menstrualDays){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(startDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        c.add(Calendar.DAY_OF_MONTH, menstrualDays); //add  days to predict the next period date.

        String nextPeriodDate=sdf.format(c.getTime());

        return nextPeriodDate;
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


    /*********************************************tap calendar button to  redirect to page where calendar is displayed*************************/
    public void goToCalendar(View view){

        btn2 = findViewById(R.id.calendarNavigaetBtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayStartDateHome.this , DisplayCalendar.class);
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