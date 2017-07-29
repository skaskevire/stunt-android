package com.stunt.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stunt.Game;
import com.stunt.Globals;

public class Background implements Entity {

    private final TextureRegion textureRegion;
    private int speed = 100;

    public Background() {
        textureRegion = new TextureRegion(Game.res.getTexture("background"));
    }


	@Override
	public void update(float dt) {

		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(textureRegion, 0, -Globals.V_HEIGHT, Globals.V_WIDTH,
				Globals.V_HEIGHT*2);
		sb.end();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}