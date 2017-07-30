package com.stunt.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.stunt.Globals;

public final class BodyCreationUtils {
	private BodyCreationUtils()
	{
		//it's no need for object creation
	}
	
	public static Body rectangularBody(float density, World world, float x, float y, float width, float height)
	{
		PolygonShape psh = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		BodyDef bdef = new BodyDef();		
		bdef.position.set(x, y);
		bdef.type  = BodyType.DynamicBody;
		Body body = world.createBody(bdef);		
		psh.setAsBox(width, height);
		fdef.shape = psh;	
		fdef.restitution = 0.4f;
		fdef.density = density;
		fdef.friction = 0.8f;
		body.createFixture(fdef);
		
		return body;
	}
	
	public static Body rectangularStaticBody(World world, float x, float y, float width, float height)
	{
		PolygonShape psh = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		BodyDef bdef = new BodyDef();		
		bdef.position.set(x, y);
		bdef.type  = BodyType.StaticBody;
		Body body = world.createBody(bdef);	
		psh.setAsBox(width, height);
		fdef.shape = psh;	
		fdef.restitution = 0.4f;
		fdef.density = 1f;
		fdef.friction = 0.8f;
		//fdef.filter.maskBits = 0;
		body.createFixture(fdef);
		return body;
	}
	
	public static FixtureDef createPolygonFixture(float width, float height, float restitution, float density, float friction, Vector2 directionCenter, float angle)
	{
		PolygonShape psh = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		if(directionCenter == null)
		{
			psh.setAsBox(width, height);
		}
		else
		{
			psh.setAsBox(width, height, directionCenter, angle);
		}
		
		fdef.shape = psh;
		fdef.restitution = restitution;
		fdef.density = density;
		fdef.friction = friction;
		
		return fdef;		
	}
	
	public static PrismaticJoint vzhJoint(World world, Body a, Body b, float lax, float lay, float lbx, float lby)
	{
		
		com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef revoluteJointDef2 = new PrismaticJointDef();
		revoluteJointDef2.lowerTranslation = 0f/Globals.PPM;
		revoluteJointDef2.upperTranslation = 8f/Globals.PPM;
		revoluteJointDef2.enableLimit=true;
		revoluteJointDef2.collideConnected = false;
		revoluteJointDef2.localAxisA.add(0, 12);
		  revoluteJointDef2.bodyA = a;
		  revoluteJointDef2.bodyB = b;
		  revoluteJointDef2.localAnchorA.set(lax, lay);//the top right corner of the box
		  revoluteJointDef2.localAnchorB.set(lbx, lby);//center of the circle


		  return (PrismaticJoint)world.createJoint( revoluteJointDef2);
	}
	
	
	public static DistanceJoint distanceJoint(float dr, World world, Body a, Body b, float lax, float lay, float lbx, float lby)
	{
		
		com.badlogic.gdx.physics.box2d.joints.DistanceJointDef revoluteJointDef2 = new DistanceJointDef();
		revoluteJointDef2.length = 0/ Globals.PPM;
		revoluteJointDef2.dampingRatio = dr;
		revoluteJointDef2.frequencyHz = 4f;
		  revoluteJointDef2.bodyA = a;
		  revoluteJointDef2.bodyB = b;
		  revoluteJointDef2.collideConnected = false;
		  revoluteJointDef2.localAnchorA.set(lax, lay);//the top right corner of the box
		  revoluteJointDef2.localAnchorB.set(lbx, lby);//center of the circle


		  return (DistanceJoint)world.createJoint( revoluteJointDef2);
	}
	
	
	
	
	public static RevoluteJoint revoluteJoint(World world, Body a, Body b, float lax, float lay, float lbx, float lby)
	{
		RevoluteJointDef revoluteJointDef2 = new RevoluteJointDef();
		  revoluteJointDef2.bodyA = a;
		  revoluteJointDef2.bodyB = b;
		  revoluteJointDef2.collideConnected = false;
		  revoluteJointDef2.localAnchorA.set(lax, lay);//the top right corner of the box
		  revoluteJointDef2.localAnchorB.set(lbx, lby);//center of the circle
		  return (RevoluteJoint)world.createJoint( revoluteJointDef2);
	}
	
	public static Body circularBody(World world, float x, float y, float radius)
	{
		CircleShape cshape = new CircleShape();	
		cshape.setRadius(radius);
		BodyDef bdef = new BodyDef();
		bdef.position.set(x, y);		
		bdef.type  = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = cshape;	
		fdef.restitution = 0.35f;
		fdef.density = 10f;
		fdef.friction = 10;
		body.createFixture(fdef);
		
		return body;
	}
	
	
	public static Body circularBody(World world, float x, float y, float radius, float density)
	{
		CircleShape cshape = new CircleShape();	
		cshape.setRadius(radius);
		BodyDef bdef = new BodyDef();
		bdef.position.set(x, y);		
		bdef.type  = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = cshape;	
		fdef.restitution = 0.35f;
		fdef.density = density;
		fdef.friction = 10;
		body.createFixture(fdef);
		
		return body;
	}
	
}
