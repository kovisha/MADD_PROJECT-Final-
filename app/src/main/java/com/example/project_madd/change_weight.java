package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;

public class change_weight extends AppCompatActivity {

    EditText edtWgt;
    Button btnResetWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_weight);

        //getting the ids of required components
        edtWgt = findViewById(R.id.editTextWeight);
        btnResetWeight = findViewById(R.id.btnChangeWeight);
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
            Intent intent = new Intent(change_weight.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(change_weight.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }//end of menu

    /********************************************* THE UPDATE WEIGHT METHOD ********************************************************/
    @SuppressLint("ShowToast")
    public void updateWeight(View view){

        //instance of db
        DBOpenHelper dbHelper=new DBOpenHelper(this);

        //converting to String
        String weight = edtWgt.getText().toString();

        //validation
        if(!weight.isEmpty()){

            Integer wgt = Integer.parseInt(edtWgt.getText().toString());

            long val=dbHelper.updateWgt(wgt);

            if(val>0)
            {
                Intent i = new Intent(change_weight.this,Settings.class);
                Toast.makeText(getApplicationContext(),"Updated!!",Toast.LENGTH_SHORT).show();
                startActivity(i);
                //myDialog.dismiss();
            }
            else
            {
                Intent i = new Intent(change_weight.this,Settings.class);
                Toast.makeText(this,"Failed to update!!",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(change_weight.this,"Please enter weight!",Toast.LENGTH_SHORT);
        }
    }
}