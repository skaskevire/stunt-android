package com.stunt.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ShortArray;
import com.stunt.Game;
import com.stunt.Globals;
import com.stunt.util.BodyCreationUtils;
import com.stunt.util.BodyUserData;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Ground implements Entity {
	private World world;
	private List<Body> obstacles;
	private Body ground;
	OrthographicCamera b2dCam;
	
	
	private PolygonSprite groundPolygon;
	 PolygonSpriteBatch polyBatch;
	
	
	Vector2[] vectorArray;
	
	public Ground(World world, OrthographicCamera b2dCam, PolylineMapObject ta)
	{
		
		this.world = world;
		this.b2dCam = b2dCam;
		obstacles = createObstacles();
		
		float[] vert = ta.getPolyline().getVertices();
		vectorArray = new Vector2[vert.length / 2];
		for(int i = 0,  vaI = 0 ;i < vert.length; i+=2)
		{
			vectorArray[vaI++] = new Vector2(vert[i]/ Globals.PPM, vert[i+1]/ Globals.PPM);
		}
		
		
		ground = createTerrain(world, 0,-40 / Globals.PPM, vectorArray);
		
		
		
		
		
		
		
		
		
		
		
		
		
		//create ground polygon
		EarClippingTriangulator a = new EarClippingTriangulator();

		 float[] vertices = new float[vectorArray.length * 2];
		int vl = 0;
		 for (int i = 0; i < vectorArray.length ; i++) {
		        vertices[vl] = vectorArray[i].x;
		        vl++;
		        vertices[vl] = vectorArray[i].y - 40/ Globals.PPM;
		        vl++;
		    }
		 ShortArray sar = a.computeTriangles(vertices);
		 short[] shortarray = new short[sar.size];
		    for (int i = 0; i < sar.size; i++)
		        shortarray[i] = sar.get(i);
		 
		    PolygonRegion pr = new PolygonRegion(new TextureRegion(
					Game.res.getTexture("terrain1")), vertices, shortarray);
		    groundPolygon = new PolygonSprite(pr);
		    
		    
		    
		     polyBatch = new PolygonSpriteBatch();
		    
	}
	
	
	
	
	public Ground(World world, OrthographicCamera b2dCam)
	{
		this.world = world;
		this.b2dCam = b2dCam;
		vectorArray = createTerrainCurve();
		obstacles = createObstacles();
	}
	
	private Vector2[] createTerrainCurve()
	{
		Vector2[] vectorArray = new Vector2[24];
		vectorArray[0] = new Vector2(0,30/ Globals.PPM);
		vectorArray[1] = new Vector2(100/ Globals.PPM,60/ Globals.PPM);
		vectorArray[2] = new Vector2(110/ Globals.PPM,70/ Globals.PPM);
		vectorArray[3] = new Vector2(200/ Globals.PPM,40/ Globals.PPM);
		vectorArray[4] = new Vector2(220/ Globals.PPM,50/ Globals.PPM);
		vectorArray[5] = new Vector2(620/ Globals.PPM,50/ Globals.PPM);
		vectorArray[6] = new Vector2(720/ Globals.PPM,100/ Globals.PPM);
		vectorArray[7] = new Vector2(720/ Globals.PPM,50/ Globals.PPM);
		vectorArray[8] = new Vector2(900/ Globals.PPM,40/ Globals.PPM);
		vectorArray[9] = new Vector2(1200/ Globals.PPM,0/ Globals.PPM);
		vectorArray[10] = new Vector2(5000/Globals.PPM,0/ Globals.PPM);
		vectorArray[11] = new Vector2(5000/Globals.PPM,1000/ Globals.PPM);
		

		vectorArray[12] = new Vector2(5000/Globals.PPM,800/ Globals.PPM);
		vectorArray[13] = new Vector2(5000/Globals.PPM,-200/ Globals.PPM);
		vectorArray[14] = new Vector2(1200/ Globals.PPM,-200/ Globals.PPM);
		vectorArray[15] = new Vector2(900/ Globals.PPM,-160/ Globals.PPM);
		vectorArray[16] = new Vector2(720/ Globals.PPM,-150/ Globals.PPM);
		vectorArray[17] = new Vector2(720/ Globals.PPM,-100/ Globals.PPM);
		vectorArray[18] = new Vector2(620/ Globals.PPM,-150/ Globals.PPM);
		vectorArray[19] = new Vector2(220/ Globals.PPM,-150/ Globals.PPM);
		vectorArray[20] = new Vector2(200/ Globals.PPM,-160/ Globals.PPM);
		vectorArray[21] = new Vector2(110/ Globals.PPM,-130/ Globals.PPM);
		vectorArray[22] = new Vector2(100/ Globals.PPM,-140/ Globals.PPM);
		vectorArray[23] = new Vector2(0,-170/ Globals.PPM);
		ground = createTerrain(world, 0,-40 / Globals.PPM, vectorArray);
		
		return vectorArray;
	}
	
	
	private Body createTerrain(World world, float x, float y, Vector2[] vectorArray)
	{
		ChainShape shape = new ChainShape();
		shape.createChain(vectorArray);
		FixtureDef fdef = new FixtureDef();
		BodyDef bdef = new BodyDef();
		bdef.position.set(x,y);
		bdef.type = BodyType.StaticBody;
		
		Body body = world.createBody(bdef);
		fdef.shape = shape;
		fdef.density = 1;
		fdef.friction = 1.9f;
		body.createFixture(fdef);
		
		return body;
	}
	
	private List<Body> createObstacles()
	{
		
		float tLength = 4f;
		float twidth = 1f;
		Body[] track = new Body[3];
		float length = (float) (101  / Globals.PPM);
		float delta = (float) (tLength/Globals.PPM)*2;	
		
		
		
		
		 tLength = 5f;
		 twidth = 5f;
		 track = new Body[28];
		 length = (float) (634  / Globals.PPM);
		 delta = 8 / Globals.PPM ;
		
		
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
			
			
			BodyUserData bud = new BodyUserData();
			bud.setHeight(twidth);
			bud.setWidth(tLength);
			track[i].setUserData(bud);
		}
		
		
		
		
		
		List<Body> tracks = new ArrayList<Body>(Arrays.asList(track));
		
		
		 tLength = 1f;
		 twidth = 14f;
		 track = new Body[20];
		 length = (float) (634  / Globals.PPM);
		 delta = 8 / Globals.PPM ;
		
		
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
			
			BodyUserData bud = new BodyUserData();
			bud.setHeight(twidth);
			bud.setWidth(tLength);
			track[i].setUserData(bud);
		}
		
		
		
		
		
		tracks.addAll(Arrays.asList(track));
		
		
		
		
		 tLength = 10;
		 twidth = 10f;
		 track = new Body[8];
		 length = (float) (1234  / Globals.PPM);
		 delta = 20 / Globals.PPM ;
		
		
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
			
			BodyUserData bud = new BodyUserData();
			bud.setHeight(twidth);
			bud.setWidth(tLength);
			track[i].setUserData(bud);
		}
		
		
		tracks.addAll(Arrays.asList(track));
		
		
		 tLength = 25f;
		 twidth = 25f;
		 track = new Body[3];
		 length = (float) (1434  / Globals.PPM);
		 delta = 27 / Globals.PPM ;
		
		
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
			
			BodyUserData bud = new BodyUserData();
			bud.setHeight(twidth);
			bud.setWidth(tLength);
			track[i].setUserData(bud);
		}
		
		
		
		tracks.addAll(Arrays.asList(track));
	

		return tracks;
	}
	
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch sb) {		    

		polyBatch.setProjectionMatrix(sb.getProjectionMatrix());
		    
		    
		    polyBatch.begin();
		    groundPolygon.draw(polyBatch);
		    polyBatch.end();
		
		
		

		
		sb.begin();
		
		if(obstacles != null)
		{
			for(Body obstacle : obstacles)
			{

					sb.draw(new TextureRegion(
							Game.res.getTexture("box25")),
							obstacle.getPosition().x- ((BodyUserData)obstacle.getUserData()).getHeight()/(Globals.PPM * 2), obstacle.getPosition().y- ((BodyUserData)obstacle.getUserData()).getWidth()/(Globals.PPM * 2),
							((BodyUserData)obstacle.getUserData()).getHeight()/(Globals.PPM * 2),((BodyUserData)obstacle.getUserData()).getWidth()/(Globals.PPM * 2),
							((BodyUserData)obstacle.getUserData()).getHeight()/Globals.PPM, ((BodyUserData)obstacle.getUserData()).getWidth()/Globals.PPM,
							2, 2,
							obstacle.getAngle() * MathUtils.radiansToDegrees + 90f, false);
			}
		}

		
		
		
		sb.end();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
