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

        /*btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               validateFields();

            }
        });*/

    }

/*    private void validateFields() {

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
*/

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


        float H=Float.parseFloat(getH);
        float W=Float.parseFloat(getW);
        int A = Integer.parseInt(getA);
        float wa = Float.parseFloat(getWaist);


        float newH=H/100;
        float bmi=W/(newH*newH);

        if(gender.equals("Male")){

            bmr  = (float) ((10 * W)+(6.25 * H)-(5 * A)+5 );
            Intent intent =new Intent(CommonAttributesActivity.this,page3.class);
            startActivity(intent);

        }

        if(gender.equals("Female")){

            bmr  = (float) (66.5+(13.75 * W)+(5.0 * H)-(6.7 *A ));
            Intent intent =new Intent(CommonAttributesActivity.this,page4.class);
            startActivity(intent);

        }




        float waistpercentage = ((W/H)* 100 );

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        long val = dbOpenHelper.addDetails(height.getText().toString(),weight.getText().toString(),age.getText().toString(),gender,waist.getText().toString(),bmi,bmr,waistpercentage);


        if (val > 0) {

            Toast.makeText(this, "Data successfully inserted!!!", Toast.LENGTH_SHORT).show();


        }

        else {

            Toast.makeText(this, "Data not inserted!!!", Toast.LENGTH_SHORT).show();

        }





    }


}