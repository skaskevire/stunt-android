package com.stunt;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.os.Bundle;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();

       Globals.V_HEIGHT = screenHeight;
       Globals.V_WIDTH = screenWidth;
        
        
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
       
        cfg.useAccelerometer=false;

        cfg.useCompass=false;
        cfg.useGyroscope = false;

        initialize(new Game(), cfg);
    }
}