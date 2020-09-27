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

public class amount_milk extends AppCompatActivity {


    SeekBar seekBarMilkAmt;
    TextView txtMilkAmt;
    Button btnMilkAmtSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_milk);

        //getting the ids of required components
        seekBarMilkAmt = findViewById(R.id.seekBarMilkAmount);
        txtMilkAmt = findViewById(R.id.txtMilkAmount);
        btnMilkAmtSelected = findViewById(R.id.btnMilkAmount);

        //setting the method when seek bar value is changed
        seekBarMilkAmt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                txtMilkAmt.setText(progress+" ");//setting the progress value to text view
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    /**************************** MENU ****************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(amount_milk.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(amount_milk.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(amount_milk.this , User_profile.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }
    /************************************************* END OF MENU *************************************************************************/



    /************************************* METHOD add milk ************************************************************/

    public void updateAmount(View view) {
        DBOpenHelper dbHelper = new DBOpenHelper(this);

        Double drankAlready = dbHelper.getDrank();
        Double totDrank = (Double.parseDouble(txtMilkAmt.getText().toString())*0.88) + drankAlready;

        //Double amount = Double.parseDouble(txtViewAmt.getText().toString());

        Double remainingAlready = dbHelper.getRemainingAmt();
        Double totRemaining = remainingAlready - (Double.parseDouble(txtMilkAmt.getText().toString())*0.88);


        int val = dbHelper.updateInfo(totDrank, totRemaining);

        if (val > 0) {

            //Creating the LayoutInflater instance
            LayoutInflater li = getLayoutInflater();

            //Getting the View object as defined in the custom toast.xml file
            View layout = li.inflate(R.layout.custom_toast_milk, (ViewGroup) findViewById(R.id.custom_toast_milk));

            //Creating the Toast object
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setView(layout);
            toast.show();

            Intent i = new Intent(amount_milk.this, viewMyWater.class);
            startActivity(i);

        } else {
            Toast.makeText(this, "Could Not update! ", Toast.LENGTH_SHORT).show();
        }
    }

    /************************************ END of add milk *************************************************************/




    /***************************** Method cancel Add drink **********************************************************************/
    public void cancelMilk(View v){
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
                Intent i = new Intent(amount_milk.this,select_drink.class);
                startActivity(i);
            }
        });

        //positive response action
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(amount_milk.this, "You clicked No! Please select the amount", Toast.LENGTH_SHORT).show();
            }
        });

        //creating and displaying alert dialog box
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /************************* END of cancel Drink ******************************************************************/


}