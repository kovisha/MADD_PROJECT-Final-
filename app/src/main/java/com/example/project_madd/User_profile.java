package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class User_profile extends AppCompatActivity {


    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bmi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:

                return true;
            case R.id.item2:

                return true;

            case R.id.item3:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showPopup(View view){

        Button confirm , cancel;

        myDialog.setContentView(R.layout.popup);

        confirm = myDialog.findViewById(R.id.btn_delete_yes);
        cancel = myDialog.findViewById(R.id.btn_delete_no);

        myDialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_profile.this,User_profile.class);
                startActivity(intent);
            }
        });



    }


}