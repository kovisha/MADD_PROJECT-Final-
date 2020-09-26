package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class tips_and_more extends AppCompatActivity {

    Button btnFlavour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_and_more);


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
            Intent intent = new Intent(tips_and_more.this , Settings.class);
            startActivity(intent);
            return true;
            //startActivity(new Intent(this,viewMyWater.class));
        }

        else if(menuId == R.id.home_icon){
            Intent intent = new Intent(tips_and_more.this , DashBoard.class);
            startActivity(intent);
            return true;
        }

        else
            return super.onOptionsItemSelected(item);
    }//end of menu options


    /********************************************* ON CLICK METHOD TO MOVE TO NEXT ACTIVITY ********************************************************/
    public void moreTips(View view){
        btnFlavour = findViewById(R.id.btnFlavourWater);
        btnFlavour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tips_and_more.this,Flavored_Water.class);
                startActivity(intent);
            }
        });


    }

}