package com.stunt.handlers;

import java.util.Stack;

import com.stunt.Game;
import com.stunt.Globals;
import com.stunt.states.ChooseLevelMenu;
import com.stunt.states.GameState;
import com.stunt.states.MainMenu;
import com.stunt.states.Play;
import com.stunt.states.PlayEW;

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
	public GameState getState(int state, Object ... objects)
	{
		if(state == Globals.PLAY_GS)
		{
			String currentLevelPath = (String) objects[0];
			if(currentLevelPath.contains("level3"))
			{
				return new PlayEW(this, currentLevelPath);
			}
			return new Play(this, currentLevelPath);
		}
		if(state == Globals.MAINMENU_GS)
		{
			return new MainMenu(this);
		}
		if(state == Globals.CHOOSE_LEVEL_MENU_GS)
		{
			return new ChooseLevelMenu(this);
		}
		return null;
	}
	
	public void setState(int state, Object ... objects)
	{
		popState();
		pushState(state, objects);
	}
	
	public void pushState(int state, Object ... objects)
	{
		gameStates.push(getState(state, objects));
	}
	
	public void popState()
	{
		GameState g = gameStates.pop();
		g.dispose();
	}
}
