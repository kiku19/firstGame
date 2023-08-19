package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private final Context context;
    private double positionX;
    private double positionY;
    private int radius;
    public Player(Context context,double positionX,double positionY,int radius){
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.context = context;
    }
    public void update() {
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context,R.color.player);
        paint.setColor(color);
        canvas.drawCircle((float)positionX,(float) positionY,(float) radius,paint);
    }

    public void setPosition(float x, float y) {
        positionY = y;
        positionX = x;
    }
}
