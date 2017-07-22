package com.stunt.handlers;

import java.util.Stack;

import com.stunt.Game;
import com.stunt.Globals;
import com.stunt.states.GameState;
import com.stunt.states.MainMenu;
import com.stunt.states.Play;

public class GameStateManager {
	private Game game;
	private Stack<GameState> gameStates;
	
	public GameStateManager(Game game)
	{
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(Globals.MAINMENU_GS);
		
	}
	
	public Game getGame() {
		return game;
	}

	public void update(float dt)
	{
		gameStates.peek().update(dt);
	}
	
	public void render()
	{
		gameStates.peek().render();
	}
	public GameState getState(int state)
	{
		if(state == Globals.PLAY_GS)
		{
			return new Play(this);
		}
		if(state == Globals.MAINMENU_GS)
		{
			return new MainMenu(this);
		}
		return null;
	}
	
	public void setState(int state)
	{
		popState();
		pushState(state);
	}
	
	public void pushState(int state)
	{
		gameStates.push(getState(state));
	}
	
	public void popState()
	{
		GameState g = gameStates.pop();
		g.dispose();
	}
}
