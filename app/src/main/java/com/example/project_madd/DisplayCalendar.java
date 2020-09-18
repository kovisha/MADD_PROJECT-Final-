package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class DisplayCalendar extends AppCompatActivity {

    CalendarView calendarview;
    TextView myDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_calendar);

        Intent intent = getIntent(); //getting intent from home

        calendarview = findViewById(R.id.DisplayCalendar);
        myDate = findViewById(R.id.presentDate);



        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1)  + "/" + i2 + "/" + i;
                myDate.setText(date);
            }
        });




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