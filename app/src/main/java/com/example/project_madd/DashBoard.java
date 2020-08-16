package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DashBoard extends AppCompatActivity {

    Button btnP ;
    Button btnW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Intent myIntent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.menuHome){
            startActivity(new Intent(this,viewMyWater.class));
        }

        if(menuId == R.id.menuSettings){
            startActivity(new Intent(this,Settings.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void PeriodHome(View view){
         btnP = findViewById(R.id.btnMens);
         btnP.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(DashBoard.this , MenstrualHome.class);

                 startActivity(intent);
             }
         });

    }

    public void WaterHome(View view){
        btnW = findViewById(R.id.btnWater);
        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this , Water_home.class);

                startActivity(intent);
            }
        });

    }
}