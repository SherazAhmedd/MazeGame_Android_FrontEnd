package com.project.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        TextView btn1 = findViewById(R.id.btn3);

        btn1.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), Login.class));
        });
    }
}