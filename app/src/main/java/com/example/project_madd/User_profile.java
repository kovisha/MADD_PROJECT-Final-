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

   

    public void showPopup(View view){

       Button delete;

        delete = findViewById(R.id.btn_delete);

        myDialog.show();

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Button confirm , cancel;

                myDialog.setContentView(R.layout.popup);

                confirm = myDialog.findViewById(R.id.btn_delete_yes);
                cancel = myDialog.findViewById(R.id.btn_delete_no);

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
        });




    }


}