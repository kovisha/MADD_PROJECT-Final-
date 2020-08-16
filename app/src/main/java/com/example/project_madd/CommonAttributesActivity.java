package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CommonAttributesActivity extends AppCompatActivity {

    Button btnDash;
    Button btnSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_attributes);

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

    public void toDashboard(View view){

        btnDash = findViewById(R.id.dashboardbtn);
        btnDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommonAttributesActivity.this , DashBoard.class);

                startActivity(intent);
            }
        });

    }

    public void goToSettings(View view){

        btnSets = findViewById(R.id.btnSettings);
        btnSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CommonAttributesActivity.this , Settings.class);
                startActivity(i);
            }
        });


    }

}