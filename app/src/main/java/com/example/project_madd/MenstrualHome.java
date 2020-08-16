package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenstrualHome extends AppCompatActivity {

    Button btn1 ,btn2 , btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menstrual_home);

        Intent intent = getIntent(); //get intent from dashboard page
    }

    public void addRecord(View view) { //clicking add record button will redirect to page where start date is prompted

        btn1 = findViewById(R.id.addrecord);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenstrualHome.this, AddPeriodActivity.class);
                startActivity(intent);
            }
        });
    }

    public void goToCalendar(View view){

        btn2 = findViewById(R.id.calendarNavigaetBtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenstrualHome.this , DisplayCalendar.class);
                startActivity(intent);
            }
        });
    }

    public void goToAddNotes(View view){
        btn3 = findViewById(R.id.addRecordNavigatebtn);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenstrualHome.this , AddNote.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Options menu is set for page .This option displays only options
        MenuInflater inflater = getMenuInflater(); // related to period app.(Not entire app settings)
        inflater.inflate(R.menu.my_menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //creating menu
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_1:
               return true;
            case R.id.menu_2:
                Intent intent = new Intent(MenstrualHome.this ,PeriodSettingsHome.class);
                startActivity(intent);
               return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}