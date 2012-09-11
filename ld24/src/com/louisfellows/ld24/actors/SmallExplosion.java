package com.louisfellows.ld24.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SmallExplosion extends Explosion {
	public SmallExplosion(float x, float y) {
		texture = new Texture(Gdx.files.internal("data/asplode-sm.png"));
		setX(x);
		setY(y);
	}
}
