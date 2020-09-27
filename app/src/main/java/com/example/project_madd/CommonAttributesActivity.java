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

import com.example.project_madd.Database.DBOpenHelper;

public class CommonAttributesActivity extends AppCompatActivity {

    Button btnd;
    EditText age,height,weight,waist;
    RadioGroup Gender;
    RadioButton male,female;

    String gender = "";
    float bmr = 0;


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
    }


    /*insert values to the table*/
    public void addDetails(View view) {

        if (Gender.getCheckedRadioButtonId() == R.id.maleGender) {
            gender = "Male";


        } if (Gender.getCheckedRadioButtonId() == R.id.femaleGender) {
            gender = "Female";

        }

        String getH=height.getText().toString();
        String getW=weight.getText().toString();
        String getA = age.getText().toString();
        String getWaist = waist.getText().toString();



        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        if (getH.isEmpty() || getA.isEmpty() || getW.isEmpty() || getWaist.isEmpty()) {
            Toast.makeText(this,"Please fill all the fileds...",Toast.LENGTH_SHORT).show();
        }
        else {

            float H=Float.parseFloat(getH);
            float W=Float.parseFloat(getW);
            int A = Integer.parseInt(getA);
            float wa = Float.parseFloat(getWaist);


            // float newH=H/100;
            float bmi=BMIcalc(H,W);

            if(gender.equals("Male")){

                bmr  = BMRforMen(W,H,A);
                Intent intent =new Intent(CommonAttributesActivity.this,Dashboard_Male.class);
                startActivity(intent);

            }

            if(gender.equals("Female")){

                bmr  = BMRforwoMen(W,H,A);
                Intent intent =new Intent(CommonAttributesActivity.this,DashBoard.class);
                startActivity(intent);

            }

            float waistpercentage = WTHcalc(H,W);



            long val = dbOpenHelper.addDetails(height.getText().toString(), weight.getText().toString(), age.getText().toString(), gender, waist.getText().toString(), bmi, bmr, waistpercentage);


            if (val > 0) {

                Toast.makeText(this, "Data successfully inserted!!!", Toast.LENGTH_SHORT).show();


            } else {

                Toast.makeText(this, "Data not inserted!!!", Toast.LENGTH_SHORT).show();

            }


        }


    }


    public float BMIcalc(float height , float weight){

        float h = (height/100)*(height/100) ;
        float bmiFinal =  weight / h;
        return bmiFinal ;

    }

    public float BMRforMen(float weight , float height , int age ){


        float bmrFinalmen = (float) ((10 * weight)+(6.25 * height)-(5 * age)+5 );
        return bmrFinalmen ;
    }

    public float BMRforwoMen(float weight , float height , int age ){


        float bmrFinalwomen = (float) (66.5+(13.75 * weight)+(5.0 * height)-(6.7 *age));
        return bmrFinalwomen ;
    }


    public float WTHcalc(float height, float weight){

        float waistpercentage = ((weight/height)* 100);

        return waistpercentage;
    }


}