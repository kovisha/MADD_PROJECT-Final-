package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

public class View_Delete_PeriodRecord extends AppCompatActivity {

    TextView startDate,endDate,bleedDays,PLength,CLength;
    String start ,end ;
    int bleed ,pLength,cLength;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__delete__period_record);


        startDate = findViewById(R.id.myStartDatte);
        endDate = findViewById(R.id.MyEndDate);
        bleedDays = findViewById(R.id.MyMenstraul);
        PLength = findViewById(R.id.MyPLength);
        CLength = findViewById(R.id.MyCycleLength);


        /*************Retrieve data orperiod records  here**********************/

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.MenstrualRecords(database);

        while(cursor.moveToNext()) {
            start = cursor.getString(cursor.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_START_DATE));
            end = cursor.getString(cursor.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_END_DATE));
            bleed = cursor.getInt(cursor.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_BLEEDING_DAYS));
            pLength = cursor.getInt(cursor.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_P_LENGTH));
            cLength = cursor.getInt(cursor.getColumnIndex(DBStructure.PeriodTracker.COLUMN_NAME_C_LENGTH));


        }

        String bleedingDays = Integer.toString(bleed);
        String PeriodicLength = Integer.toString(pLength);
        String CyclicLength = Integer.toString(cLength);

        startDate.setText(start);
        endDate.setText(end);
        bleedDays.setText(bleedingDays);
        PLength.setText(PeriodicLength);
        CLength.setText(CyclicLength);



    }


    public void deletePeriodRecord(View view){
        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

        long val = dbOpenHelper.deletePeriodRecord();

        if(val > 0){
            Toast.makeText(getApplicationContext(), " Record deleted successfully", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(getApplicationContext(), " Record Deletion  failed", Toast.LENGTH_SHORT).show();
        }
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


                Intent intent = new Intent(View_Delete_PeriodRecord.this , DashBoard.class);
                startActivity(intent);
            }
        });



        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getApplicationContext(),"You clicked on Cancel",Toast.LENGTH_SHORT).show();
            }
        });

        Dialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
}