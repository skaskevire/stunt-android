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
		for (Entry<Integer, MyInput.MonitorCoordinate> entry : MyInput.getActivePointers().entrySet()) {
			System.out.println(entry.getValue().getX() + " " + entry.getValue().getY());
			if (entry.getValue().getX() > Globals.V_WIDTH / 2.5f - Globals.V_WIDTH / 20 && entry.getValue().getX() < Globals.V_WIDTH / 2.5f + Globals.V_WIDTH / 20
					&& entry.getValue().getY() < Globals.V_HEIGHT / 1.8f  && entry.getValue().getY() > Globals.V_HEIGHT / 2.5f - Globals.V_HEIGHT/28) {
				play.setColor(Color.GOLD);

				playTapped = true;
			}

		}
		if (MyInput.getActivePointers().values().size() == 0) {
			play.setColor(Color.WHITE);
			if (playTapped == true) {
				gsm.setState(Globals.CHOOSE_LEVEL_MENU_GS);
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
		stunt.draw(sb, "Stunt", Globals.V_WIDTH / 2.5f,  Globals.V_HEIGHT/1.1f);
		play.draw(sb, "Play",  Globals.V_WIDTH / 2.5f, Globals.V_HEIGHT / 1.8f);
		settings.setColor(Color.WHITE);
		settings.draw(sb, "Settings", Globals.V_WIDTH / 2.5f, Globals.V_HEIGHT / 2.2f);
		sb.end();

	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

}
