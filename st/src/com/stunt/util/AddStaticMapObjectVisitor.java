package com.stunt.util;

import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.stunt.Globals;

public class AddStaticMapObjectVisitor implements MapObjectVisitor
{
	private MapObjectManager mapObjectManager;
	private World world;
	
	public AddStaticMapObjectVisitor(MapObjectManager mapObjectManager, World world)
	{
		this.mapObjectManager = mapObjectManager;
		this.world = world;
	}
	
	@Override
	public void visit(RectangleMapObject rmo)
	{
		String rotationStr = rmo.getProperties().get("rotation", String.class);
		Float rotation = null;
		Float deltay = 0f;
		if(rotationStr != null)
		{
			rotation = Float.parseFloat(rmo.getProperties().get("rotation", String.class));
			rotation = -( 3.14f * rotation ) / 180f;
			
			
			deltay = (float) (Math.sin(rotation) * rmo.getRectangle().width / 2);
		}
		
		System.out.println(rotation);
		BodyUserData bud = new BodyUserData();
		bud.setHeight(rmo.getRectangle().height/2);
		bud.setWidth(rmo.getRectangle().width/2);
		
		Body rb = BodyCreationUtils.rectangularStaticBody( world, (rmo.getRectangle().x + rmo.getRectangle().width/2)/ Globals.PPM, (rmo.getRectangle().y + rmo.getRectangle().height/2)/ Globals.PPM + deltay / Globals.PPM, rmo.getRectangle().width/ (Globals.PPM*2), rmo.getRectangle().height/ (Globals.PPM*2),rotation);
		rb.setUserData(bud);
		mapObjectManager.getRectangularBodyList().add(rb);	
	}

	@Override
	public void visit(CircleMapObject cmo)
	{
		
		
	}

	@Override
	public void visit(PolygonMapObject cmo)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(EllipseMapObject cmo)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TextureMapObject cmo)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(PolylineMapObject cmo)
	{
		// TODO Auto-generated method stub
		
	}
}
