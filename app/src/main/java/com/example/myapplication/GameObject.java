package com.example.myapplication;

import android.graphics.Canvas;

public abstract class  GameObject {
    protected double velocityX;
    protected double velocityY;

    protected double positionX;
    protected double positionY;

    public GameObject(double positionX,double positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }
    protected abstract void draw(Canvas canvas);

    protected abstract void update();

}
