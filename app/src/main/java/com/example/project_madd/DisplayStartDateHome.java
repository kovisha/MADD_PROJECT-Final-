package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayStartDateHome extends AppCompatActivity {

    String takeExtra1;
    Button btn2;
    ImageView v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_start_date_home);

        Intent intent = getIntent(); //get intent from add Period activity

        takeExtra1 = intent.getStringExtra("MAIN_EXTRA"); //get the value passed from add period activity

        TextView tv = findViewById(R.id.getStartDate);
        tv.setText("Start date" + takeExtra1); //display start date in home


    }

    /*public void addEndRecord(View view) { //adding end date record
        btn2 = findViewById(R.id.addEndRecord);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //navigates to end period adding page
                Intent intent = new Intent(DisplayStartDateHome.this, EndPeriodActivity.class);
                startActivity(intent);
            }
        });


    }*/

    public void addEndRecord(View view) { //adding end date record
      v2 = findViewById(R.id.imageView2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //navigates to end period adding page
                Intent intent = new Intent(DisplayStartDateHome.this, EndPeriodActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //options menu created
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_1:

                return true;
            case R.id.menu_2:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}