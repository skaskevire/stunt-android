package com.stunt.handlers;

import java.util.HashMap;
import java.util.Map;

public class MyInput {
	public static boolean[] keys;
	public static boolean[] pkeys;
	
	public static final int NUM_KEYS = 2;
	public static final int BUTTON1 = 0;
	public static final int BUTTON2 = 1;
	
	private static Map<Integer, MonitorCoordinate> pointers;
	
	static
	{
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
		pointers = new HashMap<Integer, MonitorCoordinate>();
	}
	
	public static void update()
	{
		for(int i = 0; i < NUM_KEYS; i++)
		{
			pkeys[i] = keys[i];
		}
	}
	
	public static void setKey(int i, boolean b)
	{
		keys[i] = b;
	}
	
	public static boolean isDown(int i)
	{
		return keys[i];
	}
	
	public static boolean isPressed(int i)
	{
		return keys[i] && !pkeys[i];
	}
	
	public static Map<Integer, MonitorCoordinate> getActivePointers()
	{
		return pointers;
	}
	
	public static void touchDown(int screenX, int screenY, int pointer)
	{
		pointers.put(pointer, new MyInput.MonitorCoordinate(screenX, screenY));
	}
	
	public static void touchUp(int pointer)
	{
		pointers.remove(pointer);
	}
	
	public static class MonitorCoordinate
	{
		int x;
		int y;		
		
		public MonitorCoordinate(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
	}
}
