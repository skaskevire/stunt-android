package com.stunt;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "st";
		cfg.width = Globals.V_WIDTH * 2;
		cfg.height = Globals.V_HEIGHT * 2;
		
		new LwjglApplication(new Game(), cfg);
	}
}
