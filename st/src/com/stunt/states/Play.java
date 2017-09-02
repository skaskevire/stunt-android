
package com.stunt.states;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.stunt.Globals;
import com.stunt.entity.Background;
import com.stunt.entity.EightWheeler;
import com.stunt.entity.Entity;
import com.stunt.entity.Ground;
import com.stunt.entity.Truck;
import com.stunt.entity.Wheicle;
import com.stunt.entity.WheicleEnum;
import com.stunt.entity.WheicleFactory;
import com.stunt.handlers.GameStateManager;
import com.stunt.handlers.MyInputProcessor;
import com.stunt.util.FinishLineListener;

public class Play extends GameState
{

	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;

	private Map<String, Entity> entities;

	public Play(GameStateManager gsm, String levelPath)
	{
		super(gsm);
		Gdx.input.setInputProcessor(new MyInputProcessor());
		world = new World(new Vector2(0, -1.8f), true);
		b2dr = new Box2DDebugRenderer();
		world.setContactListener(new FinishLineListener(gsm));

		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Globals.V_WIDTH / Globals.PPM, Globals.V_HEIGHT / Globals.PPM);

		entities = new HashMap<String, Entity>();
		entities.put("playerWheicle", new Truck(world, b2dCam, 0, 1330 / Globals.PPM));
		// entities.put("background", new Background());

		entities.put("ground", new Ground(world, b2dCam, levelPath));
	}
	
	public Play(GameStateManager gsm, String levelPath, WheicleEnum playerWheicle)
	{
		super(gsm);
		Gdx.input.setInputProcessor(new MyInputProcessor());
		world = new World(new Vector2(0, -1.8f), true);
		b2dr = new Box2DDebugRenderer();
		world.setContactListener(new FinishLineListener(gsm));

		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Globals.V_WIDTH / Globals.PPM, Globals.V_HEIGHT / Globals.PPM);

		entities = new HashMap<String, Entity>();
		entities.put("playerWheicle", WheicleFactory.create(playerWheicle, world, b2dCam));
		// entities.put("background", new Background());

		entities.put("ground", new Ground(world, b2dCam, levelPath));
	}

	@Override
	public void handleInput()
	{
		for (Entity entity : entities.values())
		{
			entity.update(0f);
		}
	}

	@Override
	public void update(float dt)
	{
		try
		{
			handleInput();
			world.step(dt, 6, 2);
			sb.setProjectionMatrix(b2dCam.combined);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			gsm.setState(Globals.MAINMENU_GS, null, null);			
		}
	}

	@Override
	public void render()
	{
	    Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
	    
		b2dCam.position.set(new Vector3(((Wheicle)entities.get("playerWheicle")).getCameraTarget().x, ((Wheicle)entities.get("playerWheicle")).getCameraTarget().y, 0));
		b2dCam.update();
		try
		{
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			for (Entity entity : entities.values())
			{
				entity.render(sb);
			}

		}
		catch (Exception e)
		{
			gsm.setState(Globals.MAINMENU_GS, null, null);
		}

		// b2dr.render(world,b2dCam.combined);

	}

	@Override
	public void dispose()
	{
	}

}
