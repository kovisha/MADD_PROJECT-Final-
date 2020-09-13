package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenstrualHome extends AppCompatActivity {

    Button btn1 ,btn2 , btn3;
    ImageView v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menstrual_home);

        Intent intent = getIntent(); //get intent from dashboard page
    }

    /*public void addRecord(View view) { //clicking add record button will redirect to page where start date is prompted

        btn1 = findViewById(R.id.addrecord);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenstrualHome.this, AddPeriodActivity.class);
                startActivity(intent);
            }
        });
    }*/

    public void addRecord(View view) { //tap image to  redirect to page where start date is prompted

        v1 = findViewById(R.id.imageView);
        v1.setOnClickListener(new View.OnClickListener() {
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

    }@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(MenstrualHome.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(MenstrualHome.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }




}