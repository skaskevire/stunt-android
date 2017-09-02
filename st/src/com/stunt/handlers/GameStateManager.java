package com.stunt.handlers;

import java.util.Stack;

import com.stunt.Game;
import com.stunt.Globals;
import com.stunt.entity.WheicleEnum;
import com.stunt.states.ChooseLevelMenu;
import com.stunt.states.ChooseWheicleMenu;
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
		pushState(Globals.MAINMENU_GS, null, null);
		
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
	public GameState getState(int state, String currentLevelPath, WheicleEnum wheicle)
	{
		if(state == Globals.PLAY_GS)
		{
			return new Play(this, currentLevelPath, wheicle);
		}
		if(state == Globals.MAINMENU_GS)
		{
			return new MainMenu(this);
		}
		if(state == Globals.CHOOSE_LEVEL_MENU_GS)
		{
			return new ChooseLevelMenu(this);
		}
		if(state == Globals.CHOOSE_WHEICLE_MENU_GS)
		{
			return new ChooseWheicleMenu(this, currentLevelPath);
		}
		
		return null;
	}
	
	public void setState(int state, String currentLevelPath, WheicleEnum wheicle)
	{
		popState();
		pushState(state, currentLevelPath, wheicle);
	}
	
	public void pushState(int state, String currentLevelPath, WheicleEnum wheicle)
	{
		gameStates.push(getState(state, currentLevelPath, wheicle));
	}
	
	public void popState()
	{
		GameState g = gameStates.pop();
		g.dispose();
	}
}
