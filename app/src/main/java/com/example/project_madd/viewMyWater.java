package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class viewMyWater extends AppCompatActivity {

    //attributes
    Button btnTips;
    Button btnAddDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_water);

        //instance of db helper
        DBOpenHelper dbHelper = new DBOpenHelper(this);

        //db instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        final Cursor cursor = dbHelper.getInfo(db);

        //retrieving values when the class is created
        while (cursor.moveToNext()) {
            //retrieving total amount
            String totalAmt = cursor.getString(cursor.getColumnIndex(DBStructure.Water2.COL3_WATER2));
            //getting the id of text view
            TextView txtTotal = findViewById(R.id.totalAmt);
            //setting the value textView
            txtTotal.setText(totalAmt + " ml");


            //retrieving the drank amount
            String drankAmt = cursor.getString(cursor.getColumnIndex(DBStructure.Water2.COL4_WATER2));
            //getting the id of text view
            TextView txtDrank = findViewById(R.id.DrankAmt);
            //setting the value textView
            txtDrank.setText(drankAmt + " ml");

//            //Instantiate new Timer
//            final Timer timer = new Timer();
//            // Creates a Calendar object that specifies a specific time of day
//            Calendar cal = Calendar.getInstance();
//            cal.setTimeInMillis(System.currentTimeMillis());
////            cal.set(Calendar.HOUR_OF_DAY, 20);
////            cal.set(Calendar.MINUTE, 57);
//            cal.set(Calendar.SECOND, 60);
////            cal.set(Calendar.MILLISECOND, 00);
//            final Date date = cal.getTime();
//            System.out.println("here look");
//            System.out.println(date);
//            System.out.println(cal.getTime());
//            try {
//
//                runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        TimerTask timerTask = new TimerTask() {
//                            @Override
//                            public void run() {
//                                TextView txtDrank = findViewById(R.id.DrankAmt);
//                                //setting the value textView
//                                txtDrank.setText(0 + " ml");
//                            }
//                        };
//                        timer.schedule(timerTask, date);
//                    }
//                });
//
//            } catch (Exception e) {
//                System.out.println(e);
//            }


            //retrieving the remaining amount
            String remainingAmt = cursor.getString(cursor.getColumnIndex(DBStructure.Water2.COL5_WATER2));
            //getting the id of text view
            TextView txtRemain = findViewById(R.id.remainingAmt);
            //setting the value textView
            if (Double.parseDouble(remainingAmt) <= 0.00) {
                txtRemain.setText("0 ml");//if amount reached set to zero
            } else {
                txtRemain.setText(remainingAmt + " ml");//else set the remaining
            }

            if (Double.parseDouble(totalAmt) <= Double.parseDouble(drankAmt)) {
                //Creating the LayoutInflater instance
                LayoutInflater li = getLayoutInflater();

                //Getting the View object as defined in the custom toast.xml file
                View layout = li.inflate(R.layout.custom_toast_welldone, (ViewGroup) findViewById(R.id.custom_toast_welldone));

                //Creating the Toast object
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setView(layout);
                toast.show();
            }

        }


    }


    /********************************************* MENU OPTIONS ********************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.settings_icon) {
            Intent intent = new Intent(viewMyWater.this, Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        } else if (menuId == R.id.home_icon) {
            Intent intent = new Intent(viewMyWater.this, DashBoard.class);
            startActivity(intent);
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }//end of menu options

    /********************************************* ON CLICK METHODS TO VIEW TIPS ********************************************************/
    public void viewTips(View view) {
        btnTips = findViewById(R.id.btnMoreWater);
        btnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewMyWater.this
                        , tips_and_more.class);

                startActivity(intent);
            }
        });
    }//end of method

    /******************************************* ON CLICK METHOD TO ADD DRINK **********************************************************/
    public void addDrink(View view) {
        btnAddDrink = findViewById(R.id.btnAddDrink);
        btnAddDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewMyWater.this
                        , select_drink.class);

                startActivity(intent);
            }
        });

    }//end of method


}