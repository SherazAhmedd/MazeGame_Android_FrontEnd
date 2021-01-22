package com.project.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class GameViewMedium extends View {
    private float margin;

    private enum Direction{
        UP, DOWN, LEFT, RIGHT
    }
    private Cell[][] cells;
    private Cell player, exit;
    private static final int COLS=9, ROWS=9;
    private static final float wallThickness=5;
    private float cellSize, horizontalMargin, verticalMargin;
    private Paint wallPaint, playerPaint, exitPaint, visitedPaint;
    private Random random;
    public GameViewMedium(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        wallPaint=new Paint();
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStrokeWidth(wallThickness);

        playerPaint=new Paint();
        playerPaint.setColor(Color.GREEN);

        exitPaint=new Paint();
        exitPaint.setColor(Color.RED);

        visitedPaint=new Paint();
        visitedPaint.setColor(Color.BLUE);

        random=new Random();

        createMaze();
    }
    private GameViewMedium.Cell getNeighbour(GameViewMedium.Cell cell){
        ArrayList<GameViewMedium.Cell> neighbours=new ArrayList<>();
        //left neighbour
        if(cell.col>0)
            if(!cells[cell.col-1][cell.row].visited)
                neighbours.add(cells[cell.col-1][cell.row]);
        //right neighbour
        if(cell.col<COLS-1)
            if(!cells[cell.col+1][cell.row].visited)
                neighbours.add(cells[cell.col+1][cell.row]);
        //top neighbour
        if(cell.row>0)
            if(!cells[cell.col][cell.row-1].visited)
                neighbours.add(cells[cell.col][cell.row-1]);
        //bottom neighbour
        if(cell.row<ROWS-1)
            if(!cells[cell.col][cell.row+1].visited)
                neighbours.add(cells[cell.col][cell.row+1]);
        if(neighbours.size()>0){
            int index=random.nextInt(neighbours.size());
            return neighbours.get(index);
        }
        return null;
    }
    private void removeWall(GameViewMedium.Cell current, GameViewMedium.Cell next){
        //for below
        if(current.col==next.col && current.row==next.row+1){
            current.topWall=false;
            next.bottomWall=false;
        }
        //for above
        if(current.col==next.col && current.row==next.row-1){
            current.bottomWall=false;
            next.topWall=false;
        }
        //for right
        if(current.col==next.col+1 && current.row==next.row){
            current.leftWall=false;
            next.rightWall=false;
        }
        //for left
        if(current.col==next.col-1 && current.row==next.row){
            current.rightWall=false;
            next.leftWall=false;
        }
    }
    private void createMaze(){
        Stack<GameViewMedium.Cell> stack=new Stack<>();
        GameViewMedium.Cell current, next;

        cells=new GameViewMedium.Cell[COLS][ROWS];
        for(int x=0; x<COLS; x++){
            for(int y=0; y<ROWS; y++){
                cells[x][y]=new GameViewMedium.Cell(x,y);
            }
        }

        player=cells[0][0];
        exit=cells[COLS-1][ROWS-1];

        current=cells[0][0];
        current.visited=true;
        do {
            next=getNeighbour(current);
            if (next!=null) {
                removeWall(current, next);
                stack.push(current);
                current=next;
                current.visited = true;
            }
            else {
                current=stack.pop();
            }
        }while(!stack.empty());
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY);

        int width=getWidth();
        int height=getHeight();
        if(width/height<COLS/ROWS){
            cellSize=width/(COLS+1);
        }
        else {
            cellSize=height/(ROWS+1);
        }
        horizontalMargin=(width-COLS*cellSize)/2;
        verticalMargin=(height-ROWS*cellSize)/2;

        canvas.translate(horizontalMargin, verticalMargin);

        for(int x=0; x<COLS; x++){
            for(int y=0; y<ROWS; y++){
                if(cells[x][y].topWall){
                    canvas.drawLine(
                            x*cellSize,
                            y*cellSize,
                            (x+1)*cellSize,
                            y*cellSize,
                            wallPaint);
                }
                if(cells[x][y].leftWall){
                    canvas.drawLine(
                            x*cellSize,
                            y*cellSize,
                            x*cellSize,
                            (y+1)*cellSize,
                            wallPaint);
                }
                if(cells[x][y].bottomWall){
                    canvas.drawLine(
                            x*cellSize,
                            (y+1)*cellSize,
                            (x+1)*cellSize,
                            (y+1)*cellSize,
                            wallPaint);
                }
                if(cells[x][y].rightWall){
                    canvas.drawLine(
                            (x+1)*cellSize,
                            y*cellSize,
                            (x+1)*cellSize,
                            (y+1)*cellSize,
                            wallPaint);
                }
            }
        }

        margin=cellSize/15;

        canvas.drawRect(
                player.col*cellSize+margin,
                player.row*cellSize+margin,
                (player.col+1)*cellSize-margin,
                (player.row+1)*cellSize-margin,
                playerPaint);
        canvas.drawRect(
                exit.col*cellSize+margin,
                exit.row*cellSize+margin,
                (exit.col+1)*cellSize-margin,
                (exit.row+1)*cellSize-margin,
                exitPaint);
    }
    private void movePlayer(GameViewMedium.Direction direction){
        switch (direction){
            case UP:
                if(!player.topWall)
                    player=cells[player.col][player.row-1];
                break;
            case DOWN:
                if(!player.bottomWall)
                    player=cells[player.col][player.row+1];
                break;
            case LEFT:
                if(!player.leftWall)
                    player=cells[player.col-1][player.row];
                break;
            case RIGHT:
                if(!player.rightWall)
                    player=cells[player.col+1][player.row];
        }
        checkExit();
        invalidate();
    }
    private void checkExit(){
        if(player==exit){
            Intent intent=new Intent(getContext(), PopUpActivity.class);
            getContext().startActivity(intent);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    createMaze();
                }
            }, 1000);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
            return true;
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            float x=event.getX();
            float y=event.getY();

            float playerCenterX=horizontalMargin+(player.col+0.5f)*cellSize;
            float playerCenterY=verticalMargin+(player.row+0.5f)*cellSize;

            float dx=x-playerCenterX;
            float dy=y-playerCenterY;

            float absoluteDx=Math.abs(dx);
            float absoluteDy=Math.abs(dy);

            if(absoluteDx>cellSize || absoluteDy>cellSize){
                if(absoluteDx>absoluteDy){
                    //move in x-direction
                    if(dx>0)
                        //move to the right
                        movePlayer(GameViewMedium.Direction.RIGHT);
                    else
                        //move to the left
                        movePlayer(GameViewMedium.Direction.LEFT);
                }
                else{
                    //move in y-direction
                    if(dy>0)
                        //move down
                        movePlayer(GameViewMedium.Direction.DOWN);
                    else
                        //move up
                        movePlayer(GameViewMedium.Direction.UP);
                }
            }
            return true;
        }
        return super.onTouchEvent(event);
    }
    private class Cell{
        boolean
                topWall=true,
                leftWall=true,
                bottomWall=true,
                rightWall=true,
                visited=false;
        String color="";

        int col, row;
        public Cell(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
}