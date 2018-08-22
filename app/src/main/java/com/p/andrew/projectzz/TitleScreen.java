package com.p.andrew.projectzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;


public class TitleScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.titlescreen);
    }


    public void onStart(View v) {
        if(v.getId() == R.id.startbutton){
            Intent i = new Intent(this, Game.class);
            startActivity(i);
        }
    }
}
