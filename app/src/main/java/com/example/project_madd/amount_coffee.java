package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;

public class amount_coffee extends AppCompatActivity {

    SeekBar seekCoffee;
    TextView txtCoffee;
    Button btnCoffeeAmtSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_coffee);

        //getting the ids of required components
        seekCoffee = findViewById(R.id.seekBarCoffeeAmt);
        txtCoffee = findViewById(R.id.textAmount);
        btnCoffeeAmtSelected = findViewById(R.id.btnCoffeeAmnt);

        //setting the method when seek bar value is changed
        seekCoffee.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                txtCoffee.setText(progress+" ");//setting the progress value to text view
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    /********************************************* UPDATING AMOUNT WHEN A NEW AMOUNT IS ADDED ********************************************************/
    public void updateAmount(View view){
        DBOpenHelper dbHelper=new DBOpenHelper(this);

        Double drankAlready = dbHelper.getDrank();
        // Double totDrank = (Double.parseDouble(txtCoffee.getText().toString())*0.8)+drankAlready;

        Double amt = Double.parseDouble(txtCoffee.getText().toString());

        Double totDrank = calcDrank(drankAlready,amt);

        Double remainingAlready = dbHelper.getRemainingAmt();
        Double totRemaining = calRemaining(remainingAlready,amt);
        // Double totRemaining = remainingAlready - (Double.parseDouble(txtCoffee.getText().toString())*0.8);


        int val=dbHelper.updateInfo(totDrank,totRemaining);

        if(val>0)
        {

            //Creating the LayoutInflater instance
            LayoutInflater li = getLayoutInflater();

            //Getting the View object as defined in the custom toast.xml file
            View layout = li.inflate(R.layout.custom_toast_coffee,(ViewGroup) findViewById(R.id.custom_toast_coffee));

            //Creating the Toast object
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.setView(layout);
            toast.show();

            Intent i = new Intent(amount_coffee.this,viewMyWater.class);
            startActivity(i);

        }
        else
        {
            Toast.makeText(this,"Could Not update! ",Toast.LENGTH_SHORT).show();
        }
    }//end of method

    /******************************************** CALCULATING THE DRANK AMOUNT *******************************************************************/
    public Double calcDrank(Double drankAlready,Double amountNow){
        return (drankAlready+(amountNow*0.8));
    }

    /******************************************** CALCULATING THE REMAINING AMOUNT *******************************************************************/
    public Double calRemaining(Double remain, Double amt) {
        return(remain - (amt*0.8));
    }



    /********************************************* MENU OPTIONS ********************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(amount_coffee.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(amount_coffee.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(amount_coffee.this , User_profile.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }//end of menu



    /********************************************* ON CLICK METHOD WHEN CANCELLED ADDITION OF DRINK ********************************************************/
    public void cancelCoffee(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Cancel Drink!!");

        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.warning);

        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Do you really want to cancel??");
        alertDialogBuilder.setCancelable(false);

        //positive response action
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent i = new Intent(amount_coffee.this,select_drink.class);
                startActivity(i);
            }
        });

        //negative response action
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked No! Please select the amount", Toast.LENGTH_SHORT).show();
            }
        });

        //creating and displaying alert dialog box
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}