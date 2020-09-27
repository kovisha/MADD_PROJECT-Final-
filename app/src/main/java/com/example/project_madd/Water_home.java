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
import android.widget.Button;
import android.widget.Toast;

public class Water_home extends AppCompatActivity {

    //Attributes
    Button btnWater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_home);
    }


    /*************************************** MENU OPTION START ********************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(Water_home.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;

        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(Water_home.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }//end of menu options


    /************************************ on click method to move to next activity ***************************************/
    public void CalcWater(View view){

        //getting the ID of button
        btnWater = findViewById(R.id.btnWaterDone);
        //on click listener for the button
        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Water_home.this , water_calculation.class);//intent to move to next activity
                Toast.makeText(getApplicationContext(),"Your goals are our goals!",Toast.LENGTH_SHORT).show(); //toast to convey messages
                startActivity(intent);//start of intent
            }
        });

    }

    /***************************** ON click method when user wants to cancel and go back************************************/
    public void exit(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Cancel Goals Settings!!");

        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.warning);

        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Do you really want to cancel??");
        alertDialogBuilder.setCancelable(false);

        //setting positive response action
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent i = new Intent(Water_home.this,DashBoard.class);
                startActivity(i);
            }
        });

        //setting negative response action
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Water_home.this, "Great! You changed your mind", Toast.LENGTH_SHORT).show();
            }
        });

        //creating and displaying the alert dialog box
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }




}