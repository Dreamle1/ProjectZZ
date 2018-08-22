package com.p.andrew.projectzz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class ScorePanel extends SurfaceView implements SurfaceHolder.Callback{

    private long startTime;
    private static int score;
    boolean temp = false;
    private Button pauseButton;

    public ScorePanel(Context context, AttributeSet attrs ) {
        super(context);
        getHolder().addCallback(this);
        MainThread.getInstance().setScorePanel(this,getHolder());
        setFocusable(true);
        score = 0;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap pauseButton = BitmapFactory.decodeResource(getResources(),R.drawable.pausebutton);
        Bitmap pauseScaled = Bitmap.createScaledBitmap(pauseButton,getHeight(),getHeight(),false);
        this.pauseButton = new Button (getWidth()-getHeight(),0,pauseScaled);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        pauseButton.OnTouchEvent(event);
        if (pauseButton.getClicked()){
            SIGame.pause();
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas){
        if (canvas!= null) {
            final int savedState = canvas.save();

            drawBackground(canvas);
            drawScore(canvas);
            pauseButton.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

    public void update() {

        if (SIGame.isPlaying()) {
            score++;
        }
    }

    public void drawScore(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize((float) (getHeight()));
        canvas.drawText(Integer.toString(score), getWidth() / 2, getHeight() - 10, paint);
        paint.setTextAlign(Paint.Align.LEFT);
        if (SIGame.getHighScore() != 0) {
            canvas.drawText(Integer.toString(SIGame.getHighScore()), 0, getHeight()-10, paint);
        }
    }



    private void drawBackground(Canvas canvas){
        Bitmap bitbg = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bg1),getWidth(),getHeight(),false);
        BackGround b = new BackGround(bitbg);
        b.draw(canvas);
    }

    public static void setScore(int s) {
        score = s;
    }

    public static int getScore() {
        return score;
    }



    public int refactorX (int x){
        return (int) (x);
    }

    public int refactorY(int y){
        return (int) (y );
    }
}
