package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

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
}