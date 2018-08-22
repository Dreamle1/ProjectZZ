package com.p.andrew.projectzz;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;


public class Button {

    private int x;
    private int y;
    private int Width;
    private int Height;
    private Bitmap bitmap;
    private Boolean clicked;
    private Paint paint;
    private String text;

    public Button (int x,int y, Bitmap pressed){
        this.x = x;
        this.y = y;
        this.Width = pressed.getWidth();
        this.Height = pressed.getHeight();
        this.bitmap = pressed;
        clicked = false;
    }

    public Button (int x, int y, Paint paint,String text,int width,int height){
        this.x = x;
        this.y = y;
        this.Width = width;
        this.Height = height;
        this.paint = paint;
        this.text = text;
        clicked = false;
    }

    public void OnTouchEvent(MotionEvent event){
        if        ((int) event.getX() > x
                && (int) event.getX() < x + Width
                && (int) event.getY() > y
                && (int) event.getY() < y + Height) {
            /*if(!down) {
                if (clicked != true){
                    clicked = true;
                } else{
                    clicked = false;
                }
            } else {
                if (clicked == true){
                    down = false;
                    clicked = false;
                }else {
                    clicked = true;
                }
            }*/
            if (event.getAction() == event.ACTION_DOWN){
                clicked = true;
            }
            if (event.getAction() == event.ACTION_UP){
                clicked = false;
            }
        }
    }

    public void draw(Canvas canvas) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, x, y, null);
        }else{
            canvas.drawLine(x,y,x+Width,y,paint);
            canvas.drawLine(x+Width,y,x+Width,y+Height,paint);
            canvas.drawLine(x,y+Height,x,y,paint);
            canvas.drawLine(x,y+Height,x+Width,y+Height,paint);
            canvas.drawText(text,x + Width/2,y+Height,paint);
        }
    }

    public boolean getClicked(){
        return clicked;
    }

    public void setClicked(Boolean b){
        clicked = b;
    }
}
