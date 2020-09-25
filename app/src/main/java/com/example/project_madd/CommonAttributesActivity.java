package com.example.project_madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CommonAttributesActivity extends AppCompatActivity {

    Button btnd;
    EditText age,height,weight,activity;
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



    }

    public void toDashboard(View view){

        btnd = findViewById(R.id.dashboardbtn);
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                
                Intent intent = new Intent(CommonAttributesActivity.this , bmi_Home.class);

                startActivity(intent);
            }
        });

    }


    public void calcBmi(View v){

        String height_val = height.getText().toString();
        String weight_val = weight.getText().toString();

        if(height_val != null && !"".equals(height_val) && weight_val != null && !"".equals(weight_val))
        {
            float height_result = Float.parseFloat(height_val) / 100;
            float weight_result = Float.parseFloat(height_val);

            float bmi = weight_result / (height_result*height_result);

        }

    }


    public void calcBmr(View v){

        String height_val = height.getText().toString();
        String weight_val = weight.getText().toString();
        String age_val = age.getText().toString();

        if(height_val != null && !"".equals(height_val) && weight_val != null && !"".equals(weight_val))
        {

            float height_result = Float.parseFloat(height_val);
            float weight_result = Float.parseFloat(height_val);
         //   int age

            if(Gender.getCheckedRadioButtonId() == R.id.maleGender){

           //     float bmr = 88.362 + (13.397 * weight_result) + (4.799 * height_result) ;

            }else if (Gender.getCheckedRadioButtonId() == R.id.femaleGender){


            }
       //     float height_result = Float.parseFloat(height_val) / 100;
        //    float weight_result = Float.parseFloat(height_val);

            float bmi = weight_result / (height_result*height_result);

        }


    }

}