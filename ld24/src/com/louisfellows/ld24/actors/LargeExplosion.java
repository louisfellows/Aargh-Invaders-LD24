package com.louisfellows.ld24.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LargeExplosion extends Explosion {
	public LargeExplosion(float x, float y) {
		texture = new Texture(Gdx.files.internal("data/asplode-big.png"));
		setX(x);
		setY(y);
	}
}
