package com.louisfellows.ld24.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.louisfellows.ld24.ScoringSingleton;
import com.louisfellows.ld24.actors.firingtype.FiringType;

public abstract class AbstractShip extends LD24Actor {

	protected static final float MOVE_OFFSET = 5;
	
	protected Texture texture;
	protected FiringType firingType;
	protected Integer health;
	protected Sound	laser = Gdx.audio.newSound(Gdx.files.internal("data/LD24Laser.wav"));
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture,getY(),getX());
	}
	
	@Override
	public Actor hit(float x, float y) {
		if (x >= getX() && x <= getX() + texture.getHeight()
				&& y >= getY() && y <= getY() + texture.getWidth()) {
			return this;
		}
		return null;
	}
	
	public Integer getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}

	protected void explode() {
		ScoringSingleton.getInstance().addScoreToCurrentWave(getDeathScore());
		getStage().addActor(new LargeExplosion(getX(), getY()));
		remove();
	}

	protected abstract void fireShot();
	
	/**
	 * 
	 * @param damage
	 * @return true if the ship is destroyed
	 */
	public abstract boolean isHitBy(Shot shot);
	
	public abstract boolean isHitBy(AbstractShip ship);
	
	public abstract int getDeathScore();
	
}
