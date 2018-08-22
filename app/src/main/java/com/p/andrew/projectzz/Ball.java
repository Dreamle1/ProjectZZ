package com.p.andrew.projectzz;

import android.graphics.Bitmap;
import android.graphics.Canvas;



public class Ball extends Sprite{




    public Ball(Bitmap bitmap) {
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        this.bitmap = bitmap;
        x = ((SIGame.WIDTH / 2) - (width / 2) - 1);
        y = ((SIGame.HEIGHT / 2) - (height / 2) - 1);
        dx = 2;
        dy = 2;
    }

    public void update () {
        x += dx;
        y += dy;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,x,y,null);
    }
}
