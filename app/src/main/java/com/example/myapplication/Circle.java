package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public abstract class Circle extends GameObject {

    private int radius;
    private int color;


    public Circle(double positionX,double positionY,int radius,int color
    ){
        super(positionX,positionY);
        this.radius = radius;
        this.color = color;
    }

    protected void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle((float)positionX,(float) positionY,(float) radius,paint);
    }
}
