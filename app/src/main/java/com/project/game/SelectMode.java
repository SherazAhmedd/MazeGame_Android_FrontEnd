package com.project.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelectMode extends AppCompatActivity {
    TextView singlePlayer, backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_mode);

        singlePlayer=findViewById(R.id.btn1);
        backButton=findViewById(R.id.btn4);
        singlePlayer.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), SelectDifficulty.class));
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}