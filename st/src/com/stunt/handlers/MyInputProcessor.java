package com.stunt.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter{
	public boolean keyDown(int k)
	{
		if(k == Keys.Z)
		{
			MyInput.setKey(MyInput.BUTTON1, true);
		}
		if(k == Keys.X)
		{
			MyInput.setKey(MyInput.BUTTON2, true);
		}
		return true;
	}
	
	public boolean keyUp(int k)
	{
		if(k == Keys.Z)
		{
			MyInput.setKey(MyInput.BUTTON1, false);
		}
		if(k == Keys.X)
		{
			MyInput.setKey(MyInput.BUTTON2, false);
		}
		return true;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		MyInput.touchDown(screenX, screenY, pointer);
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		MyInput.touchUp(pointer);
		return super.touchUp(screenX, screenY, pointer, button);
	}
	
}
