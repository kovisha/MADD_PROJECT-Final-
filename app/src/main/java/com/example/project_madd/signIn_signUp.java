package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class signIn_signUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);
    }

    public void changeFragment(View view){

        if (view ==findViewById(R.id.btn_signUp) ){
            Fragment fragment = new SignUp();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frag,fragment);
            ft.commit();


        }

        if (view ==findViewById(R.id.btn_signIn) ){
            Fragment fragment = new SignIn();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frag,fragment);
            ft.commit();


        }


    }
}