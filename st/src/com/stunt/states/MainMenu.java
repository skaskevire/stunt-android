package com.stunt.states;

import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.stunt.Globals;
import com.stunt.handlers.GameStateManager;
import com.stunt.handlers.MyInput;

public class MainMenu extends GameState {

	private BitmapFont play = new BitmapFont();
	private boolean playTapped = false;
	private BitmapFont stunt = new BitmapFont();
	private BitmapFont settings = new BitmapFont();

	public MainMenu(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void handleInput()
	{
		playTapped = true;
		for (Entry<Integer, MyInput.MonitorCoordinate> entry : MyInput.getActivePointers().entrySet()) {
			System.out.println(entry.getValue().getX() + " " + entry.getValue().getY());
			if (entry.getValue().getX() > 232 - 10 && entry.getValue().getX() < 232 + 10
					&& entry.getValue().getY() < 295 + 10 && entry.getValue().getY() > 295 - 10) {
				play.setColor(Color.GOLD);

				playTapped = true;
			}

		}
		if (MyInput.getActivePointers().values().size() == 0) {
			play.setColor(Color.WHITE);
			if (playTapped == true) {
				gsm.setState(Globals.PLAY_GS);
			}
		}

	}

	@Override
	public void update(float dt)
	{
		handleInput();
	}

	@Override
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		stunt.setColor(Color.BLUE);
		stunt.draw(sb, "Stunt", 100, 300);
		play.draw(sb, "Play", 100, 180);
		settings.setColor(Color.WHITE);
		settings.draw(sb, "Settings", 100, 160);
		sb.end();

	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

}
