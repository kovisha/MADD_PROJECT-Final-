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

public class amount_selection extends AppCompatActivity {


    SeekBar seekBar;
    TextView txtViewAmt;
    Button btnWaterSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_selection);

        seekBar = findViewById(R.id.seekBarAmnt);
        txtViewAmt = findViewById(R.id.textAmount);
        btnWaterSelected = findViewById(R.id.btnAmountSelected);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                txtViewAmt.setText(progress+" ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


       /* btnWaterSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i = new Intent(amount_selection.this,viewMyWater.class);
                //startActivity(i);

                //Creating the LayoutInflater instance
                LayoutInflater li = getLayoutInflater();

                //Getting the View object as defined in the custom toast.xml file
                View layout = li.inflate(R.layout.custom_toast_water,(ViewGroup) findViewById(R.id.custom_toast_water));

                //Creating the Toast object
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.setView(layout);
                toast.show();

                Intent i = new Intent(amount_selection.this,viewMyWater.class);
                startActivity(i);
            }
        });*/
    }


    //Update method to call the update in DB HELPER----------------------------------------------------
    public void updateAmount(View view){
        DBOpenHelper dbHelper=new DBOpenHelper(this);

        Double amount = Double.parseDouble(txtViewAmt.getText().toString());


        int val=dbHelper.updateInfo(amount);

        if(val>0)
        {

            //Creating the LayoutInflater instance
            LayoutInflater li = getLayoutInflater();

            //Getting the View object as defined in the custom toast.xml file
            View layout = li.inflate(R.layout.custom_toast_water,(ViewGroup) findViewById(R.id.custom_toast_water));

            //Creating the Toast object
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.setView(layout);
            toast.show();

            Intent i = new Intent(amount_selection.this,viewMyWater.class);
            startActivity(i);

        }
        else
        {
            Toast.makeText(this,"Could Not update! ",Toast.LENGTH_SHORT).show();
        }
    }



    //--------------------------------------------- MENU ---------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(amount_selection.this , Settings.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(amount_selection.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }

    //------------------------------------- END OF MENU -----------------------------------------------------------------------------------------


    public void cancelDrink(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Cancel Drink!!");
        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.warning);
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Do you really want to cancel??");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent i = new Intent(amount_selection.this,select_drink.class);
                startActivity(i);
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(amount_selection.this, "You clicked No! Please select the amount", Toast.LENGTH_SHORT).show();
            }
        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}