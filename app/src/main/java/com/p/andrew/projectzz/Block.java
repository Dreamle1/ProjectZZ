package com.p.andrew.projectzz;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Block extends Sprite {


    public Block (Bitmap bitmap, int x, int y){
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,x,y,null);
    }
}
