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

    protected double getPositionX(){
            return positionX;
    }

    protected double getPositionY(){
        return positionY;
    }

    protected double getDistanceBetween(GameObject ob1,GameObject ob2){
        return Math.sqrt(Math.pow(ob1.getPositionX() - ob2.getPositionX(),2)+Math.pow(ob1.getPositionY()-ob2.getPositionY(),2));
    }
}
