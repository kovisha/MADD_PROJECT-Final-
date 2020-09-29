package com.example.project_madd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PeriodReminder extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.periodreminder);

        /***************Once user clicks notiication,redirect to this page************************/

        Button periodReminder = findViewById(R.id.NotiicationSeen);
        periodReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PeriodReminder.this , MenstrualHome.class);
                startActivity(intent);
            }
        });
    }
}
