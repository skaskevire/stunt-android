package com.stunt.states;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.stunt.Globals;
import com.stunt.entity.Background;
import com.stunt.entity.Entity;
import com.stunt.entity.Ground;
import com.stunt.entity.Truck;
import com.stunt.handlers.GameStateManager;
import com.stunt.util.BodyCreationUtils;
import com.stunt.util.FinishLineListener;

public class Play extends GameState{
	
	private BitmapFont l = new BitmapFont();
	private BitmapFont r = new BitmapFont();

	
	
	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;
	private float tileSize;
	
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer tmr;
	private Map<String, Entity> entities;

	private Body finish;
	
	public Play(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0,-1.8f), true);
		b2dr = new Box2DDebugRenderer();
		//world.setContactListener(new FinishLineListener(gsm));
		
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Globals.V_WIDTH / Globals.PPM, Globals.V_HEIGHT / Globals.PPM );
		
		entities = new HashMap<String, Entity>();
		
		entities.put("truck", new Truck(world, b2dCam, 0, 1330/Globals.PPM));
		//entities.put("background", new Background());		
		
		//////////// < tiled staff >
		
		tileMap = new TmxMapLoader().load("res/maps/test.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap,1 /Globals.PPM);

		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get("Tile Layer 1");
		layer.setOffsetX(0f);
		layer.setOffsetY(808f);
		PolylineMapObject ta = (PolylineMapObject) tileMap.getLayers().get("Object Layer 1").getObjects().get("ground");
		
		
		//RectangleMapObject finishLine = (RectangleMapObject) tileMap.getLayers().get("Object Layer 1").getObjects().get("finishLine");
		
		
		//finish  = BodyCreationUtils.rectangularStaticBody(1f, world, finishLine.getRectangle().x / Globals.PPM, finishLine.getRectangle().y/ Globals.PPM, finishLine.getRectangle().width/ Globals.PPM, finishLine.getRectangle().height/ Globals.PPM);

		
		

		entities.put("ground", new Ground(world, b2dCam, ta));

		tileSize = layer.getTileWidth();
		
		///////////  </ tiled staff >
	}	

	@Override
	public void handleInput() {
		for(Entity entity: entities.values())
		{
			entity.update(0f);
		}
	}

	@Override
	public void update(float dt) {
		try{
			handleInput();
			world.step(dt, 6, 2);
			
			
			sb.setProjectionMatrix(b2dCam.combined);
		}catch(Exception e)
		{
			gsm.setState(Globals.MAINMENU_GS);
		}


	}

	@Override
	public void render() {
		try
		{
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			
			for(Entity entity: entities.values())
			{
				entity.render(sb);
			}

			tmr.setView(b2dCam);
			tmr.render();	
		}
		catch(Exception e)
		{
			gsm.setState(Globals.MAINMENU_GS);
		}
		

	//	b2dr.render(world,b2dCam.combined);
		

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
