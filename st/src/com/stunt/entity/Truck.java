package com.stunt.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.stunt.Game;
import com.stunt.Globals;
import com.stunt.handlers.MyInput;
import com.stunt.util.BodyCreationUtils;
import com.stunt.util.BodyUserData;

public class Truck extends Wheicle{

	private World world;
	

	private List<Body> rollers;
	private List<Body> tracks;
	private Body cargo;
	private Body truckBody;
	//private OrthographicCamera b2dCam;
	
	public Truck(World world, OrthographicCamera b2dCam, float x, float y)
	{
		this.world = world;
		rollers = new ArrayList<Body>();
		
		//cargo = BodyCreationUtils.rectangularBody(10f,world, 165 / Globals.PPM,218 / Globals.PPM,20 / Globals.PPM, 10 / Globals.PPM);	
		BodyUserData bud = new BodyUserData();
		bud.setHeight(10);
		bud.setWidth(20);
		//cargo.setUserData(bud);
		
		
		
		
		truckBody = createTruckBody(10f, world, x + 160 / Globals.PPM, y + 120 / Globals.PPM,33 / Globals.PPM, 3 / Globals.PPM);
	
		tracks = createTracks(x, y);
		createRollers(x, y);
		attachWheels(truckBody,x,y);
		
	}
	
	private void attachWheels(Body body, float x, float y)
	{
		Body d1 = BodyCreationUtils.rectangularBody(30f,world, x+110 / Globals.PPM,y+110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d2 = BodyCreationUtils.rectangularBody(30f,world, x+130 / Globals.PPM,y+110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d3 = BodyCreationUtils.rectangularBody(30f,world, x+150 / Globals.PPM,y+110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d4 = BodyCreationUtils.rectangularBody(30f,world, x+170 / Globals.PPM,y+110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d5 = BodyCreationUtils.rectangularBody(40f,world, x+190 / Globals.PPM,y+110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d6 = BodyCreationUtils.rectangularBody(30f, world,x+ 210 / Globals.PPM,y+120 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);
		
		BodyCreationUtils.vzhJoint(world, body, d1, -50/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.vzhJoint(world, body, d2, -30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);	
		BodyCreationUtils.vzhJoint(world, body, d3, -10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.vzhJoint(world, body, d4, 10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);	
		BodyCreationUtils.vzhJoint(world, body, d5, 30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.vzhJoint(world, body, d6, 50/Globals.PPM,10/Globals.PPM, 0,12/Globals.PPM);	
		
		BodyCreationUtils.distanceJoint(0.6f, world, body, d1, -50/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.distanceJoint(0.6f,world, body, d2, -30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);		
		BodyCreationUtils.distanceJoint(0.6f,world, body, d3, -10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.distanceJoint(0.6f,world, body, d4, 10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.distanceJoint(1f,world, body, d5, 30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.distanceJoint(0.9f,world, body, d6, 50/Globals.PPM,10/Globals.PPM, 0,12/Globals.PPM);
		
		BodyCreationUtils.revoluteJoint(world, rollers.get(0), d1, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(1), d2, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(2), d3, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(3), d4, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(4), d5, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(5), d6, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
	}	
	
	
	private void createRollers(float x, float y)
	{
		rollers.add(BodyCreationUtils.circularBody(world,x+ 110 / Globals.PPM,y+ 100 / Globals.PPM, 5 / Globals.PPM));		
		rollers.add(BodyCreationUtils.circularBody(world,x+ 130 / Globals.PPM,y+ 100 / Globals.PPM, 5 / Globals.PPM));
		rollers.add(BodyCreationUtils.circularBody(world,x+ 150 / Globals.PPM,y+ 100 / Globals.PPM, 5 / Globals.PPM));
		rollers.add(BodyCreationUtils.circularBody(world,x+ 170 / Globals.PPM,y+ 100 / Globals.PPM, 5 / Globals.PPM));
		rollers.add(BodyCreationUtils.circularBody(world,x+ 190 / Globals.PPM,y+ 100 / Globals.PPM, 5 / Globals.PPM));
		rollers.add(BodyCreationUtils.circularBody(world,x+ 210 / Globals.PPM,y+ 110 / Globals.PPM, 5 / Globals.PPM));
	}
	
	
	
	private Body createTruckBody(float density, World world, float x, float y, float width, float height)
	{
		BodyDef bdef = new BodyDef();		
		bdef.position.set(x, y);
		bdef.type  = BodyType.DynamicBody;
		Body body = world.createBody(bdef);		
		body.createFixture(BodyCreationUtils.createPolygonFixture(width, height, 0.4f, density, 0.8f, null, 0f));

		//kabina + body
		body.createFixture(BodyCreationUtils.createPolygonFixture(20 / Globals.PPM, 15 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(33 / Globals.PPM,18 / Globals.PPM), 0f));		
		body.createFixture(BodyCreationUtils.createPolygonFixture(8 / Globals.PPM, 6 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(42 / Globals.PPM,24 / Globals.PPM), 0f));
		body.createFixture(BodyCreationUtils.createPolygonFixture(2 / Globals.PPM, 6 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(51 / Globals.PPM,24 / Globals.PPM), 0f));
		body.createFixture(BodyCreationUtils.createPolygonFixture(2 / Globals.PPM, 2 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(54 / Globals.PPM,6 / Globals.PPM), 0f));		
		body.createFixture(BodyCreationUtils.createPolygonFixture(34 / Globals.PPM, 2 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(-20 / Globals.PPM,6 / Globals.PPM), 0f));		
		body.createFixture(BodyCreationUtils.createPolygonFixture(1 / Globals.PPM, 10 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(-55 / Globals.PPM,3 / Globals.PPM), 3.14f));
		body.createFixture(BodyCreationUtils.createPolygonFixture(1 / Globals.PPM, 5 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(57 / Globals.PPM,-2 / Globals.PPM), 0.6f));
		
		return body;
	}
	
	private List<Body> createTracks(float x, float y)
	{
		float tLength = 4f;
		float twidth = 1f;
		Body[] track = new Body[11];
		float length =(float)x + (float) (101  / Globals.PPM);
		float delta = (float) (tLength/Globals.PPM)*2;	
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(10f,world, length,(float)y + 103 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
		}	
		for (int i = 0; i < track.length; i++) {
			if(i!= track.length-1)
			{
				BodyCreationUtils.revoluteJoint(world, track[i], track[i+1], tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, twidth/Globals.PPM);
			}
		}
		
		
		
		
		 tLength = 4f;
		 twidth = 1f;
		Body[] trackttt = new Body[4];
		 length =(float)x+ (float) (190  / Globals.PPM);
		 delta = (float) (tLength/Globals.PPM)*2;	
		for (int i = 0; i < trackttt.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(10f,world, length,(float)y+ 114 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			trackttt[i] = tr;
		}	
		for (int i = 0; i < trackttt.length; i++) {
			if(i!= trackttt.length-1)
			{
				BodyCreationUtils.revoluteJoint(world, trackttt[i], trackttt[i+1], tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, twidth/Globals.PPM);
			}
		}
		
		BodyCreationUtils.revoluteJoint(world, track[track.length-1], trackttt[0], tLength/Globals.PPM, -twidth/Globals.PPM, -tLength/Globals.PPM, -twidth/Globals.PPM);
	
		
		
		
		
		
		
		 tLength = 4f;
		 twidth = 1f;
		Body[] trackl = new Body[14];
		 length =(float)x+  (float) (101  / Globals.PPM);
		 delta = (float) (tLength/Globals.PPM)*2;
		
		
		for (int i = 0; i < trackl.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(10f,world, length,(float)y+ 89 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			trackl[i] = tr;
		}
		
		for (int i = 0; i < trackl.length; i++) {
			if(i!= trackl.length-1)
			{
				BodyCreationUtils.revoluteJoint(world, trackl[i], trackl[i+1], tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, twidth/Globals.PPM);
			}
		}
		
		BodyCreationUtils.revoluteJoint(world, trackl[0], track[0], -tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, -twidth/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, trackttt[trackttt.length-1], trackl[trackl.length-1], tLength/Globals.PPM, -twidth/Globals.PPM, tLength/Globals.PPM, twidth/Globals.PPM);

		List<Body> tracks = new ArrayList<Body>(Arrays.asList(track));
		tracks.addAll(Arrays.asList(trackl));
		tracks.addAll(Arrays.asList(trackttt));
		return tracks;
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
	public Vector2 getCameraTarget()
	{
		return rollers.get(2).getPosition();
	}
	
	@Override
	public void render(SpriteBatch sb) {

		sb.begin();
		for(Body roller : rollers)
		{
			sb.draw(new TextureRegion(
					Game.res.getTexture("wheel10")),
					roller.getPosition().x- 5/Globals.PPM, roller.getPosition().y- 5/Globals.PPM,
					5/Globals.PPM, 5/Globals.PPM,
					10/Globals.PPM, 10/Globals.PPM,
					1, 1,
					roller.getAngle() * MathUtils.radiansToDegrees, false);
		}
		
		for(Body track : tracks)
		{
			sb.draw(new TextureRegion(
					Game.res.getTexture("trackEntity")),
					track.getPosition().x- 2/Globals.PPM, track.getPosition().y- 4f/Globals.PPM,
					1/Globals.PPM, 5f/Globals.PPM,
					4f/Globals.PPM, 10f/Globals.PPM,
					1, 1, 
					track.getAngle() * MathUtils.radiansToDegrees + 90f, false);
		}
		
		sb.draw(new TextureRegion(
				Game.res.getTexture("truck")),
				truckBody.getPosition().x- 35/Globals.PPM, truckBody.getPosition().y- 60/Globals.PPM,
				35f/Globals.PPM, 60f/Globals.PPM,
				60f/Globals.PPM, 120f/Globals.PPM,
				1, 1, 
				truckBody.getAngle() * MathUtils.radiansToDegrees - 90f, false);
		
		
		
		
		if(cargo != null)
		{
			sb.draw(new TextureRegion(
					Game.res.getTexture("box25")),
					cargo.getPosition().x- ((BodyUserData)cargo.getUserData()).getHeight()/(Globals.PPM * 2), cargo.getPosition().y- ((BodyUserData)cargo.getUserData()).getWidth()/(Globals.PPM * 2),
					((BodyUserData)cargo.getUserData()).getHeight()/(Globals.PPM * 2),((BodyUserData)cargo.getUserData()).getWidth()/(Globals.PPM * 2),
					((BodyUserData)cargo.getUserData()).getHeight()/Globals.PPM, ((BodyUserData)cargo.getUserData()).getWidth()/Globals.PPM,
					2, 2,
					cargo.getAngle() * MathUtils.radiansToDegrees + 90f, false);

		}
	
		
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public List<Body> getRollers() {
		return rollers;
	}

	public void setRollers(List<Body> rollers) {
		this.rollers = rollers;
	}

	public Body getCargo() {
		return cargo;
	}

	public void setCargo(Body cargo) {
		this.cargo = cargo;
	}

	public Body getTruckBody() {
		return truckBody;
	}

	public void setTruckBody(Body truckBody) {
		this.truckBody = truckBody;
	}



}
