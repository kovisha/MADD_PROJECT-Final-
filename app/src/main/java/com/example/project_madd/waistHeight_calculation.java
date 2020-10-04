package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

public class waistHeight_calculation extends AppCompatActivity {

    TextView tv,comment;
    TextView val1,val2,val3,val4;
    TextView val11,val21,val31,val41;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waist_height_calculation);

        Intent i = getIntent();

        comment = findViewById(R.id.waist_comment);

        Button done ;
        done = findViewById(R.id.waist_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(waistHeight_calculation.this, bmi_Home.class);
                startActivity(intent);
            }
        });


        /*****************retrieve wth from database *************/

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();

        Cursor cursor = dbOpenHelper.readWTH(sqLiteDatabase);

        while(cursor.moveToNext()){

            String wth = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_WHPERCENTAGE));
            double Wth = Double.parseDouble(wth);
            String lastvar = String.format("%.2f",Wth)+ "%";
            tv =findViewById(R.id.WTHpercentage);
            tv.setText(lastvar);


            if(Wth < 43)
            {

                val1 = findViewById(R.id.waist_col1);
                val1.setTextColor(Color.GREEN);
                val11 = findViewById(R.id.waist_col12);
                val11.setTextColor(Color.GREEN);
                comment.setText("You are under weight..");
            }

            else if(Wth >= 43 && Wth < 52)
            {

                val2 = findViewById(R.id.waist_col2);
                val2.setTextColor(Color.GREEN);
                val21 = findViewById(R.id.waist_col22);
                val21.setTextColor(Color.GREEN);
                comment.setText("You are perfectly Healthy ..");
            }

            else if(Wth >= 53 && Wth <= 63)
            {

                val3 = findViewById(R.id.waist_col3);
                val3.setTextColor(Color.RED);
                val31 = findViewById(R.id.waist_col32);
                val31.setTextColor(Color.RED);
                comment.setText("Over weight.. ");
            }

            else
            {
                val4 = findViewById(R.id.waist_col4);
                val4.setTextColor(Color.RED);
                val41 = findViewById(R.id.waist_col42);
                val41.setTextColor(Color.RED);
                comment.setText("Please maintain your health...");
            }


        }



    }

    /********************************************************Menu code************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(waistHeight_calculation.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }
        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(waistHeight_calculation.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(waistHeight_calculation.this , User_profile.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }


}