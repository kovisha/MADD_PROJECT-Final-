package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class select_drink extends AppCompatActivity {


    ImageView imageWater , imageCoffee , imageMilk , imageTea, imageJuice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_drink);

        //getting the id's of image views
        imageWater = findViewById(R.id.imgWater);
        imageCoffee = findViewById(R.id.imgCoffee);
        imageMilk = findViewById(R.id.imgMilk);
        imageTea = findViewById(R.id.imgTea);
        imageJuice = findViewById(R.id.imgJuice);

        //setting on click methods for image clicks
        imageWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(select_drink.this,amount_selection.class);
                Toast.makeText(getApplicationContext(),"You Selected Water",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(select_drink.this,amount_coffee.class);
                Toast.makeText(getApplicationContext(),"You Selected Coffee",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageMilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(select_drink.this,amount_milk.class);
                Toast.makeText(getApplicationContext(),"You Selected Milk",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(select_drink.this,amount_tea.class);
                Toast.makeText(getApplicationContext(),"You Selected Tea",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageJuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(select_drink.this,amount_juice.class);
                Toast.makeText(getApplicationContext(),"You Selected Juices",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }

    /********************************************* MENU OPTIONS ********************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.settings_icon){
            Intent intent = new Intent(select_drink.this , Settings_Home_Common.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(select_drink.this , DashBoard.class);
            startActivity(intent);
            return true;
        }
        else if(menuId == R.id.profile_icon){
            Intent intent = new Intent(select_drink.this , User_profile.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }//end of menu
}