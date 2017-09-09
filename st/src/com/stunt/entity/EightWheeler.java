package com.stunt.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.stunt.Game;
import com.stunt.Globals;
import com.stunt.handlers.MyInput;
import com.stunt.util.BodyCreationUtils;

public class EightWheeler  extends Wheicle
{
	private Body ewBody;
	private List<Body> rollers;
	private World world;
	private String skin;
	public EightWheeler(World world, OrthographicCamera b2dCam, float x, float y, String skin)
	{
		this.skin = skin;
		this.world = world;
		rollers = new ArrayList<Body>();
		
		ewBody = createTruckBody(10f, world, x + 160 / Globals.PPM, y + 120 / Globals.PPM,33 / Globals.PPM, 3 / Globals.PPM);
		createRollers(x, y);
		attachWheels(ewBody,x,y);
	}
	
	private void attachWheels(Body body, float x, float y)
	{
		Body d1 = BodyCreationUtils.rectangularBody(30f,world, x+115 / Globals.PPM,y+110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d2 = BodyCreationUtils.rectangularBody(30f,world, x+140 / Globals.PPM,y+110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d4 = BodyCreationUtils.rectangularBody(30f,world, x+175 / Globals.PPM,y+110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d5 = BodyCreationUtils.rectangularBody(40f,world, x+200 / Globals.PPM,y+110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		
		BodyCreationUtils.vzhJoint(world, body, d1, -45/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.vzhJoint(world, body, d2, -20/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);	
		BodyCreationUtils.vzhJoint(world, body, d4, 15/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);	
		BodyCreationUtils.vzhJoint(world, body, d5, 40/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		
		BodyCreationUtils.distanceJoint(0.6f, world, body, d1, -45/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.distanceJoint(0.6f,world, body, d2, -20/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);		
		BodyCreationUtils.distanceJoint(0.6f,world, body, d4, 15/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.distanceJoint(1f,world, body, d5, 40/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		
		BodyCreationUtils.revoluteJoint(world, rollers.get(0), d1, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(1), d2, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(2), d4, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(3), d5, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
			}	
	
	
	private void createRollers(float x, float y)
	{
		rollers.add(BodyCreationUtils.circularBody(world,x+ 115 / Globals.PPM,y+ 100 / Globals.PPM, 10 / Globals.PPM, 1f));		
		rollers.add(BodyCreationUtils.circularBody(world,x+ 140 / Globals.PPM,y+ 100 / Globals.PPM, 10 / Globals.PPM, 1f));
		rollers.add(BodyCreationUtils.circularBody(world,x+ 175 / Globals.PPM,y+ 100 / Globals.PPM, 10 / Globals.PPM, 1f));
		rollers.add(BodyCreationUtils.circularBody(world,x+ 200 / Globals.PPM,y+ 100 / Globals.PPM, 10 / Globals.PPM, 1f));
	}
	
	private Body createTruckBody(float density, World world, float x, float y, float width, float height)
	{
		BodyDef bdef = new BodyDef();		
		bdef.position.set(x, y);
		bdef.type  = BodyType.DynamicBody;
		Body body = world.createBody(bdef);		
		body.createFixture(BodyCreationUtils.createPolygonFixture(width, height, 0.4f, density, 0.8f, null, 0f));

		body.createFixture(BodyCreationUtils.createPolygonFixture(45 / Globals.PPM, 4 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(0 / Globals.PPM,6 / Globals.PPM), 0f));		
		body.createFixture(BodyCreationUtils.createPolygonFixture(1 / Globals.PPM, 15 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(60 / Globals.PPM,5 / Globals.PPM), 1.3f));
		body.createFixture(BodyCreationUtils.createPolygonFixture(1 / Globals.PPM, 12 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(65 / Globals.PPM,-13 / Globals.PPM), -0.6f));
		body.createFixture(BodyCreationUtils.createPolygonFixture(1 / Globals.PPM, 12 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(-67 / Globals.PPM,-11 / Globals.PPM), 0.1f));
		body.createFixture(BodyCreationUtils.createPolygonFixture(1 / Globals.PPM, 8 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(-60 / Globals.PPM,6 / Globals.PPM), 1.6f));

		return body;
	}

	@Override
	public void update(float dt) {
		float force = 0.0007f;
		if(MyInput.isDown(MyInput.BUTTON1))
		{
			for(Body roller : rollers)
			{
				roller.applyAngularImpulse(force, true);
			}		
		}
		if(MyInput.isDown(MyInput.BUTTON2))
		{
			for(Body roller : rollers)
			{
				roller.applyAngularImpulse(-force, true);
			}
		}
		
		if(Gdx.input.isTouched(1))
		{
			System.out.println("dsadasd");
		}
		
		
		for (Entry<Integer, MyInput.MonitorCoordinate> entry : MyInput.getActivePointers().entrySet()) {
			System.out.println(entry.getValue().getX() + " " + entry.getValue().getY());
			if (entry.getValue().getX() < Globals.V_WIDTH / 2
					&& entry.getValue().getY() > Globals.V_HEIGHT / 2) {
				for(Body roller : rollers)
				{
					roller.applyAngularImpulse(force, true);
				}		
		
			}
			if (entry.getValue().getX() > Globals.V_WIDTH / 2
					&& entry.getValue().getY() > Globals.V_HEIGHT / 2) {
				for(Body roller : rollers)
				{
					roller.applyAngularImpulse(-force, true);
				}		
		
			}

		}
	}

	@Override
	public void render(SpriteBatch sb)
	{
		sb.begin();
		
		sb.draw(new TextureRegion(
				Game.res.getTexture(skin)),
				ewBody.getPosition().x- 10/Globals.PPM, ewBody.getPosition().y- 72/Globals.PPM,
				10f/Globals.PPM, 72f/Globals.PPM,
				35f/Globals.PPM, 150f/Globals.PPM,
				1, 1, 
				ewBody.getAngle() * MathUtils.radiansToDegrees - 90f, false);

		for(Body roller : rollers)
		{
			sb.draw(new TextureRegion(
					Game.res.getTexture("wheelEW")),
					roller.getPosition().x- 10/Globals.PPM, roller.getPosition().y- 10/Globals.PPM,
					10/Globals.PPM, 10/Globals.PPM,
					20/Globals.PPM, 20/Globals.PPM,
					1, 1,
					roller.getAngle() * MathUtils.radiansToDegrees, false);
		}
		
		sb.end();
	}
	
	

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector2 getCameraTarget()
	{
		return ewBody.getPosition();
	}

}
