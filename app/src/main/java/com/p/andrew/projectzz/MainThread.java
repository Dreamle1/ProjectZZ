package com.p.andrew.projectzz;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.p.andrew.projectzz.GamePanel;


public class MainThread extends Thread {
    private static MainThread instance;
    private int FPS = 60;
    private double averageFPS;
    private static SurfaceHolder surfaceHolder;
    private static SurfaceHolder surfaceHoldersp;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread (){
        super();
    }

    public void setGamePanel(GamePanel gamePanel, SurfaceHolder surfaceHolder){
        this.gamePanel = gamePanel;
        this.surfaceHolder = surfaceHolder;
    }

    public void setScorePanel(ScorePanel scorePanel, SurfaceHolder surfaceHolder){
        this.scorePanel = scorePanel;
        this.surfaceHoldersp = surfaceHolder;
    }

    public static MainThread getInstance(){
        if (instance == null){
            instance = new MainThread();
        }
        return instance;
    }

    @Override
    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int framecount = 0;
        long targetTime = 1000/FPS;

        while(running){
            startTime = System.nanoTime();


            canvas = null;

            try {
                canvas = this.surfaceHoldersp.lockCanvas();
                synchronized (surfaceHoldersp)
                {
                    this.scorePanel.update();
                    this.scorePanel.draw(canvas);
                }
            } catch(Exception e){

            }finally {
                if (canvas!=null){
                    try{surfaceHoldersp.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            canvas = null;

            //locking canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch(Exception e){

            }finally {
                if (canvas!=null){
                    try{surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime-timeMillis;

            try{
                this.sleep(waitTime);
            }catch(Exception e){}


            totalTime += System.nanoTime() - startTime;
            framecount++;
            if(framecount == FPS){
                averageFPS = 1000/((totalTime/framecount) / 1000000);
                framecount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }

    public void setRunning(boolean b){
        running = b;
    }
}
