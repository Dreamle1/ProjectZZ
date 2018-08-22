package com.p.andrew.projectzz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;

public abstract class Sprite {

    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int width;
    protected int height;
    protected Bitmap bitmap;

    public void setX(int x) {this.x = x;}

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Rect getrectangle(){
        return new Rect(x,y, x+width,y+height);
    }

    public void draw(Canvas canvas){

    }
}
