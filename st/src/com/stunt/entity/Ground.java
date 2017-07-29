package com.stunt.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ShortArray;
import com.stunt.Game;
import com.stunt.Globals;
import com.stunt.util.BodyCreationUtils;
import com.stunt.util.BodyUserData;

public class Ground implements Entity {
	private World world;
	private List<Body> obstacles;
	private Body ground;
	OrthographicCamera b2dCam;
	private Body finishLine;
	
	
	private PolygonSprite groundPolygon;
	 PolygonSpriteBatch polyBatch;
	
	
	Vector2[] vectorArray;
	
	public Ground(World world, OrthographicCamera b2dCam, PolylineMapObject ta, RectangleMapObject ra, MapObjects obstacles1)
	{	
		List<Body> o1list = new ArrayList<Body>();
		try
		{
			for(MapObject obstacle : obstacles1)
			{
				RectangleMapObject rmo = (RectangleMapObject) obstacle;

				BodyUserData bud = new BodyUserData();
				bud.setHeight(rmo.getRectangle().height/2);
				bud.setWidth(rmo.getRectangle().width/2);
				
			
				
				
				
				
				
				Body rb = BodyCreationUtils.rectangularBody(80f, world, (rmo.getRectangle().x + rmo.getRectangle().width/2)/ Globals.PPM, (rmo.getRectangle().y + rmo.getRectangle().height/2)/ Globals.PPM, rmo.getRectangle().width/ (Globals.PPM*2), rmo.getRectangle().height/ (Globals.PPM*2));
				rb.setUserData(bud);
				o1list.add(rb);
			}

		}catch(Throwable e)
		{
			System.out.println("dsds");
		}

		this.world = world;
		this.b2dCam = b2dCam;

		
		float[] vert = ta.getPolyline().getVertices();
		vectorArray = new Vector2[vert.length / 2];
		for(int i = 0,  vaI = 0 ;i < vert.length; i+=2)
		{
			vectorArray[vaI++] = new Vector2(vert[i]/ Globals.PPM + ta.getPolyline().getX()/ Globals.PPM, vert[i+1]/ Globals.PPM + ta.getPolyline().getY()/ Globals.PPM);
		}
		
		
		ground = createTerrain(world, 0,0 / Globals.PPM, vectorArray);
		
		
		finishLine  = BodyCreationUtils.rectangularStaticBody(world, (ra.getRectangle().x + ra.getRectangle().width / 2)/ Globals.PPM, (ra.getRectangle().y + ra.getRectangle().height /2 )/ Globals.PPM, ra.getRectangle().width / (Globals.PPM*2), ra.getRectangle().height/ (Globals.PPM*2));
		BodyUserData bud = new BodyUserData();
		bud.setHeight(ra.getRectangle().height /2 );
		bud.setWidth(ra.getRectangle().width/2);
		bud.setName("finishLine");
		finishLine.setUserData(bud);
		obstacles = o1list;
		obstacles.add(finishLine);
		
		
		//create ground polygon
		EarClippingTriangulator a = new EarClippingTriangulator();

		 float[] vertices = new float[vectorArray.length * 2];
		int vl = 0;
		 for (int i = 0; i < vectorArray.length ; i++) {
		        vertices[vl] = vectorArray[i].x;
		        vl++;
		        vertices[vl] = vectorArray[i].y;
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
