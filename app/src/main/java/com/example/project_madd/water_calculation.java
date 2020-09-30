package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class water_calculation extends AppCompatActivity{


    //attributes
    Button waterCalc;
    EditText etWgt;
    RadioGroup rgTime;
    RadioButton rbTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_calculation);

        //getting the id's of required components
        etWgt = findViewById(R.id.edtWeight);
        rgTime = findViewById(R.id.ExTime);

    }

 /***************************************************** MENU OPTIONS *************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(water_calculation.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(water_calculation.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }//End of menu options


    /************************************on click method of button to calculate and insert values to db and move to next activity ************************************/
    public void waterCalcDone(View view){

        //getting the id of checked radio button
        int id = rgTime.getCheckedRadioButtonId();
        rbTime = (RadioButton)findViewById(id);

        //instance of the db helper class
        DBOpenHelper dbHelper=new DBOpenHelper(this);

        //convert weight obtained to string
        String weight = etWgt.getText().toString();

        //validation for null values
        if (!weight.isEmpty()) {

            //calculating the total amount
            //Double tot = (Integer.parseInt(etWgt.getText().toString()) / 30.0);
            //Double ex = ((Integer.parseInt(rbTime.getText().toString()) / 30.0) * 0.35);
            //Double finalTot = (tot + ex) * 1000;

            //calling the method for calculation
            Double finalTot = total(Integer.parseInt(weight),Integer.parseInt(rbTime.getText().toString()));

            //call to insert method
            long val = dbHelper.addWater(Integer.parseInt(etWgt.getText().toString()), Integer.parseInt(rbTime.getText().toString()), finalTot);

            //redirecting on success and remaining on failure
            if (val > 0) {
                Intent i = new Intent(water_calculation.this, viewMyWater.class);
                Toast.makeText(this, "Your Water Amount Calculated!", Toast.LENGTH_SHORT).show();
                startActivity(i);
            } else {
                Intent i = new Intent(water_calculation.this, water_calculation.class);
                Toast.makeText(this, "Failed to calculate water", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            //requesting input if field is null
            Toast.makeText(water_calculation.this,"Please enter your weight!",Toast.LENGTH_SHORT).show();
        }
    }//end of method

    /************************************** calculating total water needed ***********************************************/
    public Double total(Integer weight,Integer time){

        Double tot = weight / 30.0;
        Double ex = (time / 30.0) * 0.35;
        Double finalTot = (tot + ex) * 1000;
        return finalTot;
    }


    /*************************************** on click method when cancelled******************************************/
    public void exitWaterTrack(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Cancel!!");

        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.warning);

        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Do you really want to cancel??");
        alertDialogBuilder.setCancelable(false);

        //positive response action
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent i = new Intent(water_calculation.this,Water_home.class);
                Toast.makeText(water_calculation.this, "Hoping Your Comeback!", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        //negative response action
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(water_calculation.this, "Great! You changed your mind", Toast.LENGTH_SHORT).show();
            }
        });

        //creating and displaying alert dialog box
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



}