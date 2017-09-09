package com.stunt.util;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class MapObjectManager
{
	private List<Body> circleBodyList;
	private List<Body> rectangularBodyList;
	
	private AddStaticMapObjectVisitor addStaticMOVisitor;
	private AddMapObjectVisitor addMOVisitor;

	public MapObjectManager(World world)
	{
		rectangularBodyList = new ArrayList<Body>();
		circleBodyList = new ArrayList<Body>();
		addStaticMOVisitor = new AddStaticMapObjectVisitor(this, world);
		addMOVisitor = new AddMapObjectVisitor(this, world);

	}
    public List<Body> getCircleBodyList()
	{
		return circleBodyList;
	}

	public List<Body> getRectangularBodyList()
	{
		return rectangularBodyList;
	}

	public void addStaticMO(MapObject mo) {
    	mo.accept(addStaticMOVisitor);
    }
	
	public void addMO(MapObject mo) {
    	mo.accept(addMOVisitor);
    }
}
