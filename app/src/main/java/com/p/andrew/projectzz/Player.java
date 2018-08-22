package com.p.andrew.projectzz;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player extends Sprite {

    private Bitmap image;
    private int offset;

    private int up;
    private int player;

    public Player(Bitmap res,int p) {
        height = res.getHeight();
        width = res.getWidth();
        offset = 100;
        if (p == 1) {
            y = (SIGame.HEIGHT / 2) - (height / 2);
            x = SIGame.WIDTH - offset;
        } else {
            y = (SIGame.HEIGHT / 2) - (height / 2);
            x = offset;
        }

        image = res;

    }



    public void setUp (int up) {
        if(up > 0) {
            y = up - (height / 2);
        }
    }

    public void update () {

    }

    public int getOffset(){
        return offset;
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
    }
}
