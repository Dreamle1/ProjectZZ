package com.p.andrew.projectzz;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BackGround {

    private Bitmap image;

    public BackGround(Bitmap bitmap) {
        image = bitmap;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, 0, 0, null);
    }
}


