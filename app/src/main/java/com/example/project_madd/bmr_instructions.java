package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class bmr_instructions extends AppCompatActivity {

    Button btn;
    CheckBox chkbx;
    TextView tv1,tv2,tv3 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr_instructions);

        btn = findViewById(R.id.btn_ok);
        chkbx = findViewById(R.id.checkBox1);
        tv1 = findViewById(R.id.ins1);
        tv2 = findViewById(R.id.ins2);
        tv3 = findViewById(R.id.ins3);

        Intent intent = getIntent();
        String bmi = intent.getStringExtra("BMI");
        double val = Double.parseDouble(bmi);


        if (val < 18.5) {
            tv3.setTextColor(Color.GREEN);
        } else if (val >= 18.5 && val < 25) {
            tv1.setTextColor(Color.GREEN);
        } else {
            tv2.setTextColor(Color.GREEN);
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(chkbx.isChecked()) {
                    Intent intent = new Intent(bmr_instructions.this, bmi_Home.class);
                    startActivity(intent);
                }

                else {

                    Toast.makeText(bmr_instructions.this, "Check the Box", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}