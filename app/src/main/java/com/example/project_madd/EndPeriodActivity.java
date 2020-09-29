package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.project_madd.Database.DBStructure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class EndPeriodActivity extends AppCompatActivity {

    DatePickerDialog picker;

    EditText eText,getEndDate;
    Button btnGet , eConfirm ;
    Dialog myDialog;
    TextView displayStartDate , displayEndDate , BleedDisplay;
    String startDate,endDate,bleeding;
    long bleedingDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_period);

        myDialog = new Dialog(this);
        Intent intent = getIntent(); //get intent from start date display page



        /******************************Retrieve start date from database*********************************************************/

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readStartDate(database);

        while(cursor.moveToNext()) {
            startDate = cursor.getString(cursor.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_START_DATE));

            displayStartDate = findViewById(R.id.displayStart);

            displayStartDate.setText(startDate); //retrieve start date from database and display it here
        }
        /*******************************End of start date retrieval****************************************************/



        eText = findViewById(R.id.enterEndDateInput);
        eText.setInputType(InputType.TYPE_NULL);

        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //add end date using calendar
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(EndPeriodActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //eText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                eText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btnGet = findViewById(R.id.endDatebtn);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*********************************************calling the update end date method during add end date button click***********************************************************************/
                updateEndDate(view);

/***************************************Calculating the bleedingDays**************************************************************************************/
                try {
                   long finalCalculation =  calculateMyPeriod(startDate,endDate);

                   String dayDifference = Long.toString(finalCalculation);
                    BleedDisplay = findViewById(R.id.textBleed);
                     BleedDisplay.setText(dayDifference);

                    updateBleedingDays(dayDifference);


                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });



    }

    /*******************************Bleeding days update method***********************************************************************************/

    public void updateBleedingDays(String bleedingDays) {

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

        long val = dbOpenHelper.updateBleedingDays(bleedingDays);

        if (val > 0) {
            Toast.makeText(getApplicationContext(), "Prediction processed!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Something went wrong ,Retry!", Toast.LENGTH_SHORT).show();
        }


    }


/*******************************End date update method***********************************************************************************/

    public  void updateEndDate(View view){
        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

       // SQLiteDatabase database = dbOpenHelper.getReadableDatabase();

        getEndDate = findViewById(R.id.enterEndDateInput);

        endDate = getEndDate.getText().toString();

        if(!endDate.isEmpty()) {


            long val = dbOpenHelper.updatePeriodEndDate(endDate);

            if (val > 0) {
                Toast.makeText(getApplicationContext(), " End Date Updated", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), " Update Failed.Please Retry", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(EndPeriodActivity.this, "Please enter End Date", Toast.LENGTH_SHORT).show();
        }


    }

    public void deletePeriodRecord(View view){
        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

        long val = dbOpenHelper.deletePeriodRecord();

        if(val > 0){
            Toast.makeText(getApplicationContext(), " Record deleted successfully", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(getApplicationContext(), " Delete Failed.Please Retry ", Toast.LENGTH_SHORT).show();
        }
    }


    public  long calculateMyPeriod(String startDate,String endDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date firstDate = sdf.parse(startDate);
        Date secondDate = sdf.parse(endDate);

        long difInMillies= Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(difInMillies,TimeUnit.MILLISECONDS);


        return  diff;
    }



    /***********************************************navigate back to home page ****************************************************/
    public void endClick(View view){   // navigate back to home after adding end date
        eConfirm = findViewById(R.id.endConfirm);
        eConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EndPeriodActivity.this , MenstrualHome.class);
                startActivity(intent);
            }
        });
    }


/*************************display alert before deleting record****************************/
    public void deleteAlert(final View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Confirm Delete..!!!");

        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_help_24);

        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Are you sure,You want to delete record?");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                deletePeriodRecord(view);


                Intent intent = new Intent(EndPeriodActivity.this , MenstrualHome.class);
                startActivity(intent);
            }
        });



        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getApplicationContext(),"Great! You have changed your mind",Toast.LENGTH_SHORT).show();
            }
        });

        Dialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    /**************************************Menu code************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(EndPeriodActivity.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(EndPeriodActivity.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(EndPeriodActivity.this , User_profile.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }


    /*public void DeleteRecord(View view){ //delete record will navigate to home page
        delete = findViewById(R.id.buttonDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(EndPeriodActivity.this , "Deleting record!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EndPeriodActivity.this , MenstrualHome.class);
                startActivity(intent);
            }
        });
    }*/
}