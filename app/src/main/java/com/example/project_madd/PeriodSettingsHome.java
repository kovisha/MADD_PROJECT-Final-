package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PeriodSettingsHome extends AppCompatActivity {

    Button PeriodLength , CycleLength , OvuLength ,createNotifications;
    String periodLength , PCycleLength , fertilityLength ,  AvgCycleLength;
    TextView tvOvule , tvGo,tvPeriod;
    Switch notifySwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_settings_home);

        Intent intent = getIntent();
        Intent myintent = getIntent();


        /**********************Saving the state of switch for notiications******************************************/

        notifySwitch = findViewById(R.id.notifySwitch);

        SharedPreferences sharedPreferences = getSharedPreferences("SwitchEnable",MODE_PRIVATE);
        notifySwitch.setChecked(sharedPreferences.getBoolean("switchValue",true));

        notifySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notifySwitch.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("SwitchEnable",MODE_PRIVATE).edit();
                    editor.putBoolean("switchValue",true);
                    editor.apply();
                    notifySwitch.setChecked(true);
                    Toast.makeText(getApplicationContext(), "Period Reminders On", Toast.LENGTH_SHORT).show();
                }

                else{
                    SharedPreferences.Editor editor = getSharedPreferences("SwitchEnable",MODE_PRIVATE).edit();
                    editor.putBoolean("switchValue",false);
                    editor.apply();
                    notifySwitch.setChecked(false);
                    Toast.makeText(getApplicationContext(), "Reminders Off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**********************Emd of Saving the state of switch for notiications******************************************/


        /**************************************setting the user preferred cyclelength*************************************************/
        tvGo = findViewById(R.id.getCycleLength);

        SharedPreferences result = getSharedPreferences("SaveData", Context.MODE_PRIVATE);

        String value = result.getString("Value","Data Not found");

        tvGo.setText(value + " Days");
        /*********************************************End of user preferred cycleLength*********************************************/




        /**************************************setting the user preferred ovulationLength*************************************************/
        tvOvule = findViewById(R.id.getOvulationLength); //setting the user decided period length

        SharedPreferences result2 = getSharedPreferences("SaveData2", Context.MODE_PRIVATE);

        String value2 = result2.getString("Value2","Data Not found");

        tvOvule.setText(value2 +" Days");

        /*********************************************End of user preferred ovuleLength*********************************************/


        /**************************************setting the user preferred PeriodLength*************************************************/

        tvPeriod=findViewById(R.id.getPeriodLength);

        SharedPreferences result3 = getSharedPreferences("SaveData3", Context.MODE_PRIVATE);

        String value3 = result3.getString("Value3","Data Not found");

        tvPeriod.setText(value3 + " Days");


        /*********************************************End of user preferred PeriodLength*********************************************/



        /****************************************************************************************/
        /*********************************Creating notification Manager*************************/


        createNotifications = findViewById(R.id.notify);
        createNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/************************Getting the predicted next period date via a shared preference******************************************/
                SharedPreferences startDate = getSharedPreferences("SaveStartDate", Context.MODE_PRIVATE);
                String myDate = startDate.getString("StartDate","Data Not found");


                int deductionDays = 2;

                final Calendar c = Calendar.getInstance();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    Date date1 =sdf.parse(myDate);
                    c.setTime(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                c.add(Calendar.DATE, (deductionDays * -1));  // number of days to subtract


                Intent notifyIntent = new Intent(getApplicationContext(),Period_Notification_reciever.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),101,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);


            }
        });





    }

    public void PeriodLengthSet(View view){ //to set period length navigate to period length setting page

        PeriodLength = findViewById(R.id.PLengthSet);
        PeriodLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PeriodSettingsHome.this , SetPeriodLength.class);

                startActivity(intent);
            }
        });

    }

    public void CycleLengthSet(View view){

        CycleLength = findViewById(R.id.PCyclelnSet);
        CycleLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PeriodSettingsHome.this, SetCycleLength.class);

                startActivity(intent);
            }
        });
    }



    public void OvulaionlengthSet(View view){

        OvuLength = findViewById(R.id.button3);
        OvuLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PeriodSettingsHome.this , setOvulationLength.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(PeriodSettingsHome.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(PeriodSettingsHome.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(PeriodSettingsHome.this , User_profile.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}
