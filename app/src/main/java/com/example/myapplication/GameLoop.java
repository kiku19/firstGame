package com.example.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.zip.Adler32;

public class GameLoop extends Thread {
    private static final double MAX_UPS = 60;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS; //TIME FOR 1 UPDATE

    private SurfaceHolder surfaceHolder;
    private boolean isRunning = false;
    private Game game;
    private double averageUPS;
    private double averageFPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        this.game = game;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();
        Canvas canvas = null;

        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        startTime = System.currentTimeMillis();

        while(isRunning){
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    game.update();
                    updateCount++;
                    game.draw(canvas);
                }
            }
            catch (IllegalArgumentException e){
                e.printStackTrace();
            }
            finally {
                if(canvas != null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    frameCount++;
                }
            }


            //PREVENTS FAST PROCESSING IN HIGH PERFORMANCE SYSTEM
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long)(updateCount * UPS_PERIOD) - elapsedTime;

            if(sleepTime>0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            //IN SLOW PERFORMANCE SYSTEM IT SKIPS FRAME - FPS will be reduced
            while(sleepTime<0){
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount * UPS_PERIOD) - elapsedTime;
            }

            elapsedTime = System.currentTimeMillis()-startTime;
            if(elapsedTime>=1000){
                averageUPS = updateCount /(1E-3 *elapsedTime); //PER SECOND
                averageFPS = frameCount /(1E-3 *elapsedTime); //PER SECOND

                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }
}
