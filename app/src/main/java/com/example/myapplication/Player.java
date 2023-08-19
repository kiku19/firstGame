package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class Player extends Circle {
    public static final int SPEED_PIXELS_PER_SECOND = 1000;
    public static final int MAX_SPEED = (int) (SPEED_PIXELS_PER_SECOND/GameLoop.MAX_UPS);


    Joystick joystick;

    public Player(Joystick joystick,Context context,double positionX,double positionY,int radius){
        super(positionX,positionY,radius,ContextCompat.getColor(context,R.color.player));

        this.joystick = joystick;
    }
    public void update() {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;

        positionX += velocityX;
        positionY += velocityY;

    }
}
