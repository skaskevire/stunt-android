package com.stunt.util;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.stunt.Globals;
import com.stunt.handlers.GameStateManager;

public class FinishLineListener implements ContactListener{

	private GameStateManager gsm;
	
	public FinishLineListener(GameStateManager gsm)
	{
		this.gsm = gsm;
	}
	@Override
	public void beginContact(Contact c) {
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();
		
		if(fa.getBody().getUserData() != null &&fa.getBody().getUserData() instanceof BodyUserData)
		{
			BodyUserData dc = (BodyUserData)fa.getBody().getUserData();
			if(dc.getName()!=null && dc.getName().equals("finishLine"))
			{
				gsm.setState(Globals.MAINMENU_GS);
			}
		}
		if(fb.getBody().getUserData() != null &&fb.getBody().getUserData() instanceof BodyUserData)
		{
			BodyUserData dc = (BodyUserData)fb.getBody().getUserData();
			if(dc.getName()!=null && dc.getName().equals("finishLine"))
			{
				gsm.setState(Globals.MAINMENU_GS);
			}

		}
		
	}

	@Override
	public void endContact(Contact c) {
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	// collision detection, pre solve, handling, post solve
	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}

}
