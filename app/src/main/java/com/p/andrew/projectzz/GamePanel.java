package com.p.andrew.projectzz;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private SIGame game;
    private float scaleFactorX;
    private float scaleFactorY;


    public GamePanel(Context context, AttributeSet attrs) {
        super(context);
        getHolder().addCallback(this);
        MainThread.getInstance().setGamePanel(this,getHolder());
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        game = new SIGame(getResources());
        MainThread.getInstance().setRunning(true);
        MainThread.getInstance().start();
        game.setPause();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try {
                MainThread.getInstance().setRunning(false);
                MainThread.getInstance().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int count = event.getPointerCount();
        int y1 = -1;
        int y2 = -1;
            event.setLocation(refactorX((int) event.getX()), refactorY((int) event.getY()));
            if (!game.getPause()) {
                for(int i = 0;i < event.getPointerCount();i++) {
                    if (event.getX(i) > 2 * game.WIDTH / 3) {
                        y1 = (int) event.getY(i);
                    }
                    if (event.getX(i) < game.WIDTH / 3) {
                        y2 = (int) event.getY(i);
                    }
                }
                    if (y1 != -1) {
                        game.getPlayer1().setUp(y1);
                    }
                    if (y2 != -1) {
                        game.getPlayer2().setUp(y2);
                    }


                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (game.getStart()) {
                        game.start();
                    }
                    return true;
                }
                return super.onTouchEvent(event);
            } else {
                game.getBackButton().OnTouchEvent(event);
                game.getResumeButton().OnTouchEvent(event);
                if (game.getResumeButton().getClicked()) {
                    game.unPause();
                    game.getResumeButton().setClicked(false);
                }
            }
        return true;
    }

    @Override
    public void draw(Canvas canvas){
        if (canvas!= null){
            scaleFactorX = (float) getWidth()/ game.WIDTH;
            scaleFactorY = (float) getHeight()/ game.HEIGHT;
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX,scaleFactorY);
            game.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

    public void update() {
        game.update();
    }

    public int refactorX (int x){
        return (int) (x / scaleFactorX);
    }

    public int refactorY(int y){
        return (int) (y / scaleFactorY);
    }
}
