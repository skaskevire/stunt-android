package com.stunt.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.stunt.Globals;

public class WheicleFactory {

	public static Wheicle create(WheicleEnum wheicle,  World world, OrthographicCamera b2dCam)
	{
		Wheicle createdWheicle = null;
		switch(wheicle)
		{
		case EIGHT_WHEELER:
			createdWheicle = new EightWheeler(world, b2dCam, 0, 1330 / Globals.PPM);
			break;
		case TRUCK:
			createdWheicle = new Truck(world, b2dCam, 0, 1330 / Globals.PPM);
			break;
		}
		
		return createdWheicle;
	}
}
