package com.project.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        TextView btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), Activity4.class));
        });
    }
}