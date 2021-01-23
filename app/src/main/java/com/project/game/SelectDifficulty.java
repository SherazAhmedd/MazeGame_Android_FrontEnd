package com.project.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectDifficulty extends AppCompatActivity {
    TextView title, selectMode, selectMode2, backButton;
    ImageView imageView;
    private View gameView, gameViewMedium, gameViewHard, btnStartEasy, btnStartMedium, btnStartHard, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_difficulty);
        title=findViewById(R.id.title);
        imageView=findViewById(R.id.logo);
        selectMode=findViewById(R.id.tv_select_mode);
        selectMode2=findViewById(R.id.tv_select_mode2);
        btnStartEasy=findViewById(R.id.btn1);
        gameView=findViewById(R.id.view);
        btnStartMedium=findViewById(R.id.btn2);
        gameViewMedium=findViewById(R.id.viewMedium);
        btnStartHard=findViewById(R.id.btn3);
        gameViewHard=findViewById(R.id.viewHard);
        btnBack=findViewById(R.id.btn4);
        backButton=findViewById(R.id.btn4);
        btnStartEasy.setOnClickListener(v->{
            easyMaze();
        });
        btnStartMedium.setOnClickListener(v ->{
            mediumMaze();
        });
        btnStartHard.setOnClickListener(v ->{
            hardMaze();
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SelectMode.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void easyMaze() {
        title.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        selectMode.setVisibility(View.INVISIBLE);
        selectMode2.setVisibility(View.INVISIBLE);
        btnStartEasy.setVisibility(View.INVISIBLE);
        btnStartMedium.setVisibility(View.INVISIBLE);
        btnStartHard.setVisibility(View.INVISIBLE);
        btnBack.setVisibility(View.INVISIBLE);
        gameView.setVisibility(View.VISIBLE);
    }
    public void mediumMaze(){
        title.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        selectMode.setVisibility(View.INVISIBLE);
        selectMode2.setVisibility(View.INVISIBLE);
        btnStartEasy.setVisibility(View.INVISIBLE);
        btnStartMedium.setVisibility(View.INVISIBLE);
        btnStartHard.setVisibility(View.INVISIBLE);
        btnBack.setVisibility(View.INVISIBLE);
        gameViewMedium.setVisibility(View.VISIBLE);
    }
    public void hardMaze() {
        title.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        selectMode.setVisibility(View.INVISIBLE);
        selectMode2.setVisibility(View.INVISIBLE);
        btnStartEasy.setVisibility(View.INVISIBLE);
        btnStartMedium.setVisibility(View.INVISIBLE);
        btnStartHard.setVisibility(View.INVISIBLE);
        btnBack.setVisibility(View.INVISIBLE);
        gameViewHard.setVisibility(View.VISIBLE);
    }
}