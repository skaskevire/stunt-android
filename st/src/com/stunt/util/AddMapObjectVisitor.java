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

public class AddMapObjectVisitor implements MapObjectVisitor
{
	
	private MapObjectManager mapObjectManager;
	private World world;
	
	public AddMapObjectVisitor(MapObjectManager mapObjectManager, World world)
	{
		this.mapObjectManager = mapObjectManager;
		this.world = world;
	}
	
	@Override
	public void visit(RectangleMapObject rmo)
	{
		BodyUserData bud = new BodyUserData();
		bud.setHeight(rmo.getRectangle().height/2);
		bud.setWidth(rmo.getRectangle().width/2);
		
		Body rb = BodyCreationUtils.rectangularBody(80f, world, (rmo.getRectangle().x + rmo.getRectangle().width/2)/ Globals.PPM, (rmo.getRectangle().y + rmo.getRectangle().height/2)/ Globals.PPM, rmo.getRectangle().width/ (Globals.PPM*2), rmo.getRectangle().height/ (Globals.PPM*2));
		rb.setUserData(bud);
		
		mapObjectManager.getRectangularBodyList().add(rb);
		
	}

	@Override
	public void visit(CircleMapObject cmo)
	{
/*		BodyUserData bud = new BodyUserData();
		bud.setHeight(cmo.getCircle().radius);
		bud.setWidth(cmo.getCircle().radius);
		
		Body rb = BodyCreationUtils.rectangularBody(80f, world, (rmo.getRectangle().x + rmo.getRectangle().width/2)/ Globals.PPM, (rmo.getRectangle().y + rmo.getRectangle().height/2)/ Globals.PPM, rmo.getRectangle().width/ (Globals.PPM*2), rmo.getRectangle().height/ (Globals.PPM*2));
		rb.setUserData(bud);
		
		mapObjectManager.getRectangularBodyList().add(rb);*/

		
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
