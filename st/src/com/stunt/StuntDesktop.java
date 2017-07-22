package com.stunt;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class StuntDesktop {
	public static void main(String [] args)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Globals.GAME_TITLE;
		cfg.width = Globals.V_WIDTH * Globals.SCALE;
		cfg.height = Globals.V_HEIGHT * Globals.SCALE;
		
		new LwjglApplication(new Game(), cfg);
	}
}
