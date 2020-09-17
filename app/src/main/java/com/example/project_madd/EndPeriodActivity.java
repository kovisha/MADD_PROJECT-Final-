package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import java.util.Calendar;

public class EndPeriodActivity extends AppCompatActivity {

    DatePickerDialog picker;
    TextView tvw;
    EditText eText;
    Button btnGet , eConfirm , delete;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_period);

        myDialog = new Dialog(this);
        Intent intent = getIntent(); //get intent from start date display page


        tvw = (TextView) findViewById(R.id.textView6);
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
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btnGet = findViewById(R.id.endDatebtn);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(EndPeriodActivity.this, "Your record is being processed!",Toast.LENGTH_SHORT).show();
                tvw.setText(eText.getText()); //remove this later .
            }
        });
    }


    /***********************************************navigate back to home page ****************************************************/
    public void endClick(View view){   // navigate back to home after adding end date
        eConfirm = findViewById(R.id.endConfirm);
        eConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //display message
                Toast.makeText(EndPeriodActivity.this, "Adding record!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EndPeriodActivity.this , MenstrualHome.class);
                startActivity(intent);
            }
        });
    }
/*************
    public void showPopup(View view){
        Button confirm , cancel;
        myDialog.setContentView(R.layout.confirmpopup);

        cancel = myDialog.findViewById(R.id.cnclDelete);
        confirm = myDialog.findViewById(R.id.cnfrmdlt);

        myDialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndPeriodActivity.this , MenstrualHome.class);
                startActivity(intent);
            }
        });
    }****************/

/*************************display alert before deleting record****************************/
    public void deleteAlert(View view){
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

                Toast.makeText(getApplicationContext(),"Deleting record",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EndPeriodActivity.this , MenstrualHome.class);
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