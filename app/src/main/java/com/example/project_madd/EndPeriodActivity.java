package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
                tvw.setText(eText.getText());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //create options menu
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