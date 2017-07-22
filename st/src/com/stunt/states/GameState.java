package com.stunt.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stunt.Game;
import com.stunt.handlers.GameStateManager;

public abstract class GameState {
	protected GameStateManager gsm;
	protected Game game;
	
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected OrthographicCamera hudCam;
	
	protected GameState(GameStateManager gsm)
	{
		this.gsm = gsm;
		game = gsm.getGame();
		sb = game.getSb();
		cam = game.getCam();
		hudCam = game.getHudCam();
	}
	
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
	
}
