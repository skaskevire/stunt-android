package com.stunt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stunt.handlers.Content;
import com.stunt.handlers.GameStateManager;
import com.stunt.handlers.MyInput;
import com.stunt.handlers.MyInputProcessor;

public class Game implements ApplicationListener{
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	
	private GameStateManager gsm;
	
	private float accum;
	
	public static Content res;
	
	

	@Override
	public void create() {
		res = new Content();
		res.loadTexture("res/images/box.gif", "box25");
		res.loadTexture("res/images/wheel.gif", "wheel10");
		res.loadTexture("res/images/wheelEW.gif", "wheelEW");
		res.loadTexture("res/images/track.gif", "trackEntity");
		res.loadTexture("res/images/pritsep.gif", "pritsep");
		res.loadTexture("res/images/truck.gif", "truck");
		res.loadTexture("res/images/ew.gif", "ew");
		res.loadTexture("res/images/circle.gif", "circle");
		res.loadTexture("res/images/btr60.gif", "btr60");
		res.loadTexture("res/images/tilableterraintexturefavella2.jpg", "terrain1");
		res.loadTexture("res/images/background.jpg", "background");
		
		
		Gdx.input.setInputProcessor(new MyInputProcessor());
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Globals.V_WIDTH, Globals.V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, Globals.V_WIDTH, Globals.V_HEIGHT);
		
		
		gsm = new GameStateManager(this);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
	    
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= Globals.STEP)
		{
			accum -= Globals.STEP;
			gsm.update(Globals.STEP);
			gsm.render();
			MyInput.update();
		}
		
		
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public SpriteBatch getSb() {
		return sb;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public OrthographicCamera getHudCam() {
		return hudCam;
	}

}
