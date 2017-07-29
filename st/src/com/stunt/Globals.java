package com.stunt;

public class Globals {
	
	public static final String GAME_TITLE = "Stunt";
	public static  int V_HEIGHT = 240;
	public static  int V_WIDTH = 320;
	public static final int SCALE = 2;
	
	//Game states
	public static final int PLAY_GS = 0;
	//Game states
	public static final int MAINMENU_GS = 1;
	
	public static final int CHOOSE_LEVEL_MENU_GS = 2;
	
	//Game configurations
	public static final float STEP = 1 / 60f;

	
	public static final float PPM = 100;
	
	private Globals()
	{
		//class for constants only
	}
}
