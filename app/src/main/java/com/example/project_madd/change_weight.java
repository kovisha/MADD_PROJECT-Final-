package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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