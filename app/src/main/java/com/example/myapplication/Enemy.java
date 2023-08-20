package com.example.myapplication;

public class Enemy extends Circle {
    public static final double MAX_SPEED = (Player.SPEED_PIXELS_PER_SECOND*0.6)/GameLoop.MAX_UPS;
    private Player player;
    private double actuatorX;
    private double actuatorY;

    public Enemy(Player player,double positionX, double positionY, int radius, int color) {
        super(positionX, positionY, radius, color);
        this.player = player;
    }

    @Override
    protected void update() {
        double distanceFromPlayerX = player.getPositionX() - this.positionX;
        double distanceFromPlayerY = player.getPositionY() - this.positionY;

        double distanceFromPlayer = getDistanceBetween(this,player);

        //NORMALIZED
        actuatorX = distanceFromPlayerX/distanceFromPlayer;
        actuatorY = distanceFromPlayerY/distanceFromPlayer;

        velocityX = MAX_SPEED*actuatorX;
        velocityY = MAX_SPEED*actuatorY;

        this.positionX += velocityX;
        this.positionY += velocityY;

    }
}
