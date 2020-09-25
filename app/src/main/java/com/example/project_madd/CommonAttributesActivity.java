package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CommonAttributesActivity extends AppCompatActivity {

    Button btnd;
    EditText age,height,weight,waist;
    RadioGroup Gender;
    RadioButton male,female;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_attributes);

        Intent myIntent = getIntent();
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight =  findViewById(R.id.weight);
        Gender = findViewById(R.id.radioGender);
        male = findViewById(R.id.maleGender);
        female = findViewById(R.id.femaleGender);
        btnd = findViewById(R.id.Add);
        waist = findViewById(R.id.waist);

        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               validateFields();

            }
        });

    }

    private void validateFields() {

        String a = age.getText().toString();
        String h = height.getText().toString();
        String w = weight.getText().toString();
        String wa = waist.getText().toString();

        if (TextUtils.isEmpty(a)) {
            Toast.makeText(this,"Please Enter Age",Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(h)) {
            Toast.makeText(this,"Please Enter Height",Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(w)) {
            Toast.makeText(this,"Please Enter Weight",Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(wa)) {
            Toast.makeText(this,"Please Enter Waist",Toast.LENGTH_SHORT).show();
        }


    }


}