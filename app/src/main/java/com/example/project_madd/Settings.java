package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

import java.util.Calendar;

public class Settings extends AppCompatActivity {

    Switch switch1;
    Button btnExercise, btnGoals , btnWeight;
    Dialog myDialog;
    RadioGroup rgNewTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //dialog instance for popup
        myDialog = new Dialog(this);

        //reminder Switch Method
        switch1 = (Switch) findViewById(R.id.reminderSwitch);

        //using shared preference to keep switch on/off state saved
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        switch1.setChecked(sharedPreferences.getBoolean("value", true));

        //on click method for switch
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if switch is on
                if(switch1.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    switch1.setChecked(true);
                    Toast.makeText(getApplicationContext(),"Reminders On" , Toast.LENGTH_SHORT).show();


                    //---------------------- NOTIFICATION -----------------------------------------------------------------
                    //getting calender instance for notifications
                    Calendar cal = Calendar.getInstance();

                    //setting time
                    cal.set(Calendar.HOUR_OF_DAY,16);
                    cal.set(Calendar.MINUTE,24);
                    cal.set(Calendar.SECOND,25);

                    Intent i = new Intent(getApplicationContext(),ReminderWater.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,i,PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_HOUR,pendingIntent);


                }//else if switch is off
                else{
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    switch1.setChecked(false);
                    Toast.makeText(getApplicationContext(),"Reminders Off" , Toast.LENGTH_SHORT).show();
                }


            }
        });




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
            Intent intent = new Intent(Settings.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(Settings.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }//end of menu options


    /********************************************* NAVIGATE TO CHANGE GOALS ********************************************************/
        public void changeGoals(View view){

            btnGoals = findViewById(R.id.btnChangeGoals);
            btnGoals.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(Settings.this,settings_goals.class);
                    startActivity(intent1);
                }
            });

        }


    /********************************************* POP UP TO CHANGE EXERCISE TIME ********************************************************/
       public void changeExercise(View view) {
           myDialog.setContentView(R.layout.popup);
           btnExercise = (Button) myDialog.findViewById(R.id.btnChangeExerciseTime);
           Button btnReset = (Button) myDialog.findViewById(R.id.btnSetTime);
           rgNewTime = (RadioGroup)myDialog.findViewById(R.id.timeGroup);


           btnReset.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   changeTime(rgNewTime);//calling update method to change the time
                   Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();

               }
           });

           myDialog.show();
       }//end of on click

    /********************************************* UPDATE METHOD TO CHANGE THE TIME ********************************************************/
    public void changeTime(RadioGroup rgNewTime){
        DBOpenHelper dbHelper=new DBOpenHelper(this);
        //System.out.println("Look here 2");
       // System.out.println(rgNewTime);
        RadioButton radioButton = (RadioButton) myDialog.findViewById(rgNewTime.getCheckedRadioButtonId());
        //System.out.println("look here 3:"+radioButton.getText().toString());

        //calling the update method in db helper
        long val=dbHelper.updateTime(Integer.parseInt(radioButton.getText().toString()));

        if(val>0)
        {
            myDialog.dismiss();

        }
        else
        {
            Toast.makeText(this,"Update Failed",Toast.LENGTH_SHORT).show();
            myDialog.dismiss();
        }
    }//end of method


    /********************************************* ON CLICK METHOD TO UPDATE WEIGHT ********************************************************/
       public void resetWeight(View view){
            btnWeight = findViewById(R.id.btnChangeWeight);
            btnWeight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Settings.this,change_weight.class);
                    startActivity(i);
                }
            });
       }//end of method


    /********************************************* DELETE RECORDS METHOD ********************************************************/
       public void deleteRecords(final View v){
           AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
           // Setting Alert Dialog Title
           alertDialogBuilder.setTitle("Delete Record!!");
           // Icon Of Alert Dialog
           alertDialogBuilder.setIcon(R.drawable.warning);
           // Setting Alert Dialog Message
           alertDialogBuilder.setMessage("Are You sure? Do you want to delete your water records?");
           alertDialogBuilder.setCancelable(false);

           //set positive response
           alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

               @Override
               public void onClick(DialogInterface arg0, int arg1) {
                    deleteData(v);//call te delete method

               }
           });

           //set negative response
           alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   Toast.makeText(Settings.this, "Great! Your records are safe!", Toast.LENGTH_SHORT).show();
               }
           });


           //create and show alert dialog box
           AlertDialog alertDialog = alertDialogBuilder.create();
           alertDialog.show();
       }//end of method

    /********************************************* THE DELETE METHOD ********************************************************/
    public void deleteData(View view)
    {
        //db instance
        DBOpenHelper dbHelper=new DBOpenHelper(this);

        //calling the delete method in db helper
        dbHelper.deleteRecord();

        //intent
        Intent i = new Intent(Settings.this,DashBoard.class);
        Toast.makeText(this,"Deleted successfully",Toast.LENGTH_SHORT).show();
        startActivity(i);

    }


    /********************************************* THE ON CLICK OF RESET ********************************************************/
       public void resetRecords(final View v){
           AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
           // Setting Alert Dialog Title
           alertDialogBuilder.setTitle("Reset Record!");
           // Icon Of Alert Dialog
           alertDialogBuilder.setIcon(R.drawable.warning);
           // Setting Alert Dialog Message
           alertDialogBuilder.setMessage("Reset Records???");
           alertDialogBuilder.setCancelable(false);

           alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

               @Override
               public void onClick(DialogInterface arg0, int arg1) {
                   resetInfo(v);
                   Toast.makeText(Settings.this, "Your records are reset!", Toast.LENGTH_SHORT).show();
               }
           });

           alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   Toast.makeText(Settings.this, "Your records were not reset!", Toast.LENGTH_SHORT).show();
               }
           });

            //create and show alert dialog box
           AlertDialog alertDialog = alertDialogBuilder.create();
           alertDialog.show();

       }

    /********************************************* THE RESET METHOD ********************************************************/
    public void resetInfo(View view){
        //instance of db helper
        DBOpenHelper dbHelper=new DBOpenHelper(this);

        Integer weight = dbHelper.getResetInfo();
        Integer exerciseTime = dbHelper.getResetExTime();

        Double total = weight / 30.0;
        Double extra = (exerciseTime / 30.0) * 0.35;
        Double resetTot = (total + extra) * 1000;

        long val=dbHelper.resetMyInfo(resetTot);

        if(val>0)
        {
            Intent i = new Intent(Settings.this,viewMyWater.class);
            startActivity(i);
        }
        else
        {
            Intent i = new Intent(Settings.this,Settings.class);
            Toast.makeText(this,"Reset failed!",Toast.LENGTH_SHORT).show();
            startActivity(i);
        }


    }





}