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
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DisplayCalendar extends AppCompatActivity {

    CustomCalendarView customCalendarView;
    String startDate;
    TextView viewMyStartDate,viewExpectedEndDate;
    int periodLength;
    Button periodRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_calendar);

        customCalendarView = (CustomCalendarView) findViewById(R.id.custom_calendar_view);




        /*******************************Retrieve start date and expected end date in calendar view*********************************************/

        /******************************Retrieve start date from database*********************************************************/

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readStartDate(database);

        while(cursor.moveToNext()) {
            startDate = cursor.getString(cursor.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_START_DATE));

        }
        /*******************************End of start date retrieval****************************************************/

            viewMyStartDate = findViewById(R.id.viewMyStartDate);

            viewMyStartDate.setText(startDate); //retrieve start date from database and display it here

        /******************************Retrieve period length from database*********************************************************/

        DBOpenHelper dbOpenHelper2 = new DBOpenHelper(this);
        SQLiteDatabase database2 = dbOpenHelper2.getReadableDatabase();
        Cursor cursor2 = dbOpenHelper2.readPeriodLength(database2);

        while(cursor2.moveToNext()) {
            periodLength = cursor2.getInt(cursor2.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_P_LENGTH));
        }
        /*******************************End of period length retrieval****************************************************/


            /******************************Call method to Calculate expected period end date *********************************************************/

            String finalExpectedEndDate = expectedPeriodEndDate(startDate,periodLength);

            viewExpectedEndDate = findViewById(R.id.viewMyEndDate);
            viewExpectedEndDate.setText(finalExpectedEndDate); //setting the expected  end date which is the addition of start date and 5 days of average period days.

        periodRecords = findViewById(R.id.recordsBtn);
        periodRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayCalendar.this,View_Delete_PeriodRecord.class);
                startActivity(intent);
            }
        });


    }






    /************************Calculating expected period end date*********************************************************/

    public String expectedPeriodEndDate(String startDate , int bleedingDays){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(startDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        c.add(Calendar.DAY_OF_MONTH,bleedingDays); //add  bleeding days to predict the end date of period

        String expectedEndDate=sdf.format(c.getTime());

        return expectedEndDate;
    }




    /******************menu code******************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(DisplayCalendar.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(DisplayCalendar.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}