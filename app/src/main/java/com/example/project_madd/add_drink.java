package com.example.project_madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class add_drink extends AppCompatActivity {

    Button btnAddNewDrink;
    TextView tv1;
    SeekBar sk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink);

        sk = findViewById(R.id.seekBarAmt);
        tv1 = findViewById(R.id.txtAmt);
        int progress;

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sk.setProgress(i);
                progress = i;
                tv1.setText(i + " ml");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv1.setText(progress);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv1.setText(progress);
            }
        });

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

    public void addNewDrink(View view) {
        btnAddNewDrink = findViewById(R.id.btnNewDrink);
        btnAddNewDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(add_drink.this
                        , viewMyWater.class);

                startActivity(intent);
            }
        });
    }

}