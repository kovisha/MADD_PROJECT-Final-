package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;

import java.util.Calendar;

public class AddPeriodActivity extends AppCompatActivity {


    DatePickerDialog picker; /*declaring calendar date picker*/
    EditText eText;
    Button btnGet ;
    TextView tvw;
    EditText getStartDate;
    Integer pLength,cLength,OLength;

    public String StartDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_period);

        Intent intent = getIntent(); //get intent from menstrual home .

        /********************************************function to get date through calendar and display it in date format.*********************/

        tvw = (TextView) findViewById(R.id.displayDate);
        eText = findViewById(R.id.enterDateInput);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddPeriodActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                               // eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                eText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth );
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btnGet = (Button) findViewById(R.id.addDatebtn);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                addStartDate(view);

            }
        });

       // confirmEnd = findViewById(R.id.endconfirmbtn);
       // confirmEnd.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View view) {
           //     Intent secondIntent = new Intent(AddPeriodActivity.this, DisplayStartDateHome.class);//navigate back to home page

             //   startActivity(secondIntent);
           // }
        //});


    }


    /**********Start date insertion method************************/
    public void addStartDate(View view) {

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

        getStartDate = findViewById(R.id.enterDateInput); //get date input in addPeriod activity

        StartDate= getStartDate.getText().toString();
        pLength = 5;
        cLength = 28;
        OLength = 14;

        if(!StartDate.isEmpty()) {

            long val = dbOpenHelper.addMenstrualStartDate(StartDate,pLength,cLength,OLength);

            if (val > 0) {
                Toast.makeText(getApplicationContext(), " Period Tracking Initiated", Toast.LENGTH_SHORT).show();
                //tvw.setText("Tap the tick icon to confirm"); //remove this after data insertion is done
            } else {
                Toast.makeText(getApplicationContext(), " Initiations Failed", Toast.LENGTH_SHORT).show();
            }

            Intent secondIntent = new Intent(AddPeriodActivity.this, DisplayStartDateHome.class);//navigate back to home page

            startActivity(secondIntent);
        }

        else{
            Toast.makeText(AddPeriodActivity.this, "Please Enter Start Date", Toast.LENGTH_SHORT).show();
        }
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
            Intent intent = new Intent(AddPeriodActivity.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(AddPeriodActivity.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(AddPeriodActivity.this , User_profile.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }




}