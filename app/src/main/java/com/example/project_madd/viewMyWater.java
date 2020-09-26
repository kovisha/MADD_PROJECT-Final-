package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

public class viewMyWater extends AppCompatActivity {

    //attributes
    Button btnTips;
    Button btnAddDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_water);

        //instance of db helper
        DBOpenHelper dbHelper=new DBOpenHelper(this);

        //db instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.getInfo(db);

        //retrieving values when the class is created
        while(cursor.moveToNext()){
            //retrieving total amount
            String totalAmt = cursor.getString(cursor.getColumnIndex(DBStructure.Water2.COL3_WATER2));
            //getting the id of text view
            TextView txtTotal = findViewById(R.id.totalAmt);
            //setting the value textView
            txtTotal.setText(totalAmt+" ml");

            //retrieving the drank amount
            String drankAmt = cursor.getString(cursor.getColumnIndex(DBStructure.Water2.COL4_WATER2));
            //getting the id of text view
            TextView txtDrank = findViewById(R.id.DrankAmt);
            //setting the value textView
            txtDrank.setText(drankAmt+" ml");

            //retrieving the remaining amount
            String remainingAmt = cursor.getString(cursor.getColumnIndex(DBStructure.Water2.COL5_WATER2));
            //getting the id of text view
            TextView txtRemain = findViewById(R.id.remainingAmt);
            //setting the value textView
            txtRemain.setText(remainingAmt+" ml");

        }


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
            Intent intent = new Intent(viewMyWater.this , Settings.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(viewMyWater.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }//end of menu options

    /********************************************* ON CLICK METHODS TO VIEW TIPS ********************************************************/
    public void viewTips(View view){
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
    public void addDrink(View view){
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