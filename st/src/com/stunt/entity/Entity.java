package com.stunt.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
	void update(float dt);
	void render(SpriteBatch sb);
	void dispose();
}
