package com.project.game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PopUpActivity extends Activity {

    Button backButton, homeButton, nextButtonForEasy, nextButtonForMedium, nextButtonForHard;
    Class createMaze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Activity3.class);
                startActivity(intent);
                finish();
            }
        });

        homeButton=findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nextButtonForEasy=findViewById(R.id.nextButton);
        nextButtonForEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {Log.i("MY","START");
                        createMaze = Class.forName("GameView");
                    Log.i("MY","START2");
                        Object object=createMaze.newInstance();
                    Log.i("MY","START3");
                        Method method=createMaze.getDeclaredMethod("createMaze", null);
                        method.setAccessible(true);
                        method.invoke(object, null);
                     }catch (Exception e) {
                    e.printStackTrace();
                }
                onBackPressed();
                finish();
            }
        });

        nextButtonForMedium=findViewById(R.id.nextButton);
        nextButtonForMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {Log.i("MY","START");
                    createMaze = Class.forName("GameViewMedium");
                    Log.i("MY","START2");
                    Object object=createMaze.newInstance();
                    Log.i("MY","START3");
                    Method method=createMaze.getDeclaredMethod("createMaze", null);
                    method.setAccessible(true);
                    method.invoke(object, null);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                onBackPressed();
                finish();
            }
        });

        nextButtonForHard=findViewById(R.id.nextButton);
        nextButtonForHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {Log.i("MY","START");
                    createMaze = Class.forName("GameViewHard");
                    Log.i("MY","START2");
                    Object object=createMaze.newInstance();
                    Log.i("MY","START3");
                    Method method=createMaze.getDeclaredMethod("createMaze", null);
                    method.setAccessible(true);
                    method.invoke(object, null);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                onBackPressed();
                finish();
            }
        });

        /*DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width=displayMetrics.widthPixels;
        int height=displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.7));
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;
        getWindow().setAttributes(params);*/
    }
}