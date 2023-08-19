package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {

    private int innerCircleCenterX;
    private int innerCircleCenterY;
    private final int outerCircleRadius;
    private final int innerCircleRadius;
    private int outerCircleCenterX;
    private int outerCircleCenterY;
    private boolean isPressed;

    //DIRECTION OF MOVEMENT
    private double actuatorX;
    private double actuatorY;

    public Joystick(int circleCenterX,int circleCenterY,int outerCircleRadius,int innerCircleRadius){
        this.outerCircleCenterX = circleCenterX;
        this.outerCircleCenterY = circleCenterY;
        this.innerCircleCenterX = circleCenterX;
        this.innerCircleCenterY = circleCenterY;

        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

    }
    public void draw(Canvas canvas) {
        Paint paintOuterCircle = new Paint();
        paintOuterCircle.setColor(Color.GRAY);
        canvas.drawCircle(outerCircleCenterX,outerCircleCenterY,outerCircleRadius,paintOuterCircle);

        Paint paintInnerCircle = new Paint();
        paintInnerCircle.setColor(Color.BLUE);
        canvas.drawCircle(innerCircleCenterX,innerCircleCenterY,innerCircleRadius,paintInnerCircle);
    }



    public void update() {
        updateInnerCirclePostion();
    }

    private void updateInnerCirclePostion() {
        innerCircleCenterX = (int)(outerCircleCenterX + actuatorX*outerCircleRadius);
        innerCircleCenterY = (int)(outerCircleCenterY + actuatorY*outerCircleRadius);
    }

    public boolean isPressed(float x, float y) {
        double distanceFromCenterCircleToTouch = Math.sqrt(Math.pow(outerCircleCenterX-x,2)+Math.pow(outerCircleCenterY-y,2));
        if(distanceFromCenterCircleToTouch<outerCircleRadius) {
            return true;
        }
        return false;
    }

    public boolean getIsPressed() {
        return isPressed;
    }
    public void setIsPressed(boolean b) {
        isPressed = b;
    }

    public void setActuator(float x, float y) {
        double deltaX = (double) (x - outerCircleCenterX);
        double deltaY = (double) (y - outerCircleCenterY);
        double deltaDistance = (double) (Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2)));

        //NORMALIZATION - ACTUATOR WILL BE IN RANGE -1 TO 1 -- NOTHING BUT THE DIRECTION
        //ACTUATOR X - I VECTOR
        //ACTUATOR Y - J VECTOR
        if(deltaDistance<outerCircleRadius){
            actuatorX = deltaX/outerCircleRadius;
            actuatorY = deltaY/outerCircleRadius;
        }else{
            actuatorX = deltaX/deltaDistance;
            actuatorY = deltaY/deltaDistance;
        }
    }
    public void resetActuator() {
        actuatorX = 0;
        actuatorY = 0;
    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }
}
