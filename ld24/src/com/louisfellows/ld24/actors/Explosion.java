package com.louisfellows.ld24.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Explosion extends Actor {
	private int ttl = 5;
	protected Texture texture;
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture,getY(),getX());
        
        ttl--;
        
        if (ttl <= 0) {
        	remove();
        }
	}	
}
