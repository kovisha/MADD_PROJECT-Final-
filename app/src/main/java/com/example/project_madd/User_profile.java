package com.example.project_madd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_madd.Database.DBOpenHelper;
import com.example.project_madd.Database.DBStructure;

public class User_profile extends AppCompatActivity {

    private ImageView img ;
    private  static final int REQUEST_IMAGE_CAPTURE = 101;


    Dialog myDialog;

    TextView tv1,tv2,tv3,tv4,tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        img = findViewById(R.id.imgview);

        /*****************retrieve userprofile from database *************/
        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();

        Cursor cursor = dbOpenHelper.UserProfile(sqLiteDatabase);

        while(cursor.moveToNext()){

            String weight = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_WEIGHT));
            String height = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_HEIGHT));
            String age = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_AGE));
            String gender = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_GENDER));
            String waist = cursor.getString(cursor.getColumnIndex(DBStructure.BMITracker.COLUMN_NAME_WAIST));

            double w = Double.parseDouble(weight);
            double h = Double.parseDouble(height);
            double wh = Double.parseDouble(waist);

            tv1 =findViewById(R.id.prof_height);
            tv2 = findViewById(R.id.prof_weight);
            tv3 = findViewById(R.id.prof_Age);
            tv4 = findViewById(R.id.prof_gender);
            tv5 = findViewById(R.id.prof_waist);

            tv1.setText(String.format("%.2f",h)+"cm");
            tv2.setText(String.format("%.2f",w)+"Kg");
            tv3.setText(age);
            tv4.setText(gender);
            tv5.setText(String.format("%.2f",wh)+"cm");


        }



    }

    public void takepicture(View view){

        Intent imagecapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(imagecapture.resolveActivity(getPackageManager()) !=null){

            startActivityForResult(imagecapture,REQUEST_IMAGE_CAPTURE);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap)extras.get("data");
            img.setImageBitmap(imageBitmap);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void deleteData(View view){

        DBOpenHelper dbOpenHelper =new DBOpenHelper(this);
        dbOpenHelper.deleteInfo();

        Toast.makeText(this,"Deleted Successfully!!" ,Toast.LENGTH_LONG).show();

    }

   

    public void showPopup(View view){

       Button delete;

        delete = findViewById(R.id.btn_delete);

        myDialog.show();

      /*  delete.setOnClickListener(new View.OnClickListener() {

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
                        Intent intent = new Intent(User_profile.this,bmi_Home.class);
                        startActivity(intent);
                    }
                });


            }
        });*/




    }





}