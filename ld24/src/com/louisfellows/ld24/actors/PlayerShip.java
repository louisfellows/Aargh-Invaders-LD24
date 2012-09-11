package com.louisfellows.ld24.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.louisfellows.ld24.ScoringSingleton;
import com.louisfellows.ld24.actors.Shot.Direction;
import com.louisfellows.ld24.actors.firingtype.StraightShot;

public class PlayerShip extends AbstractShip {
	
	private long lastShot;
	private static final long SHOT_SPEED = 100;
	
	public PlayerShip() {
		super();
		texture = new Texture(Gdx.files.internal("data/ship.png"));	
		setX(0);
		setY(0);
		lastShot = System.currentTimeMillis();
		firingType = new StraightShot(Direction.RIGHT, true);
		health = 500;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			setY(getY() - MOVE_OFFSET);
			if (getY() < 0) {
				setY(0);
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			setY(getY() + MOVE_OFFSET);
			if (getY() > (600 - texture.getWidth())) {
				setY(600 - texture.getWidth());
			}
		}	
		
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			setX(getX() + MOVE_OFFSET);
			if (getX() > (400 - texture.getHeight())) {
				setX(400 - texture.getHeight());
			}
		}	
		
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			setX(getX() - MOVE_OFFSET);
			if (getX() < 0) {
				setX(0);
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			fireShot();
		}
		
		super.draw(batch, parentAlpha);
	}
	
	@Override
	protected void fireShot() {
		if (System.currentTimeMillis() > lastShot + SHOT_SPEED) { 
			for (Actor act : firingType.fire(getX() + (texture.getHeight()/2), getY() + texture.getWidth())) {
				getStage().addActor(act);
			}
			laser.play(1.0f);
			lastShot = System.currentTimeMillis();
		}
	}

	@Override
	public boolean isHitBy(Shot shot) {
		if (!shot.isPlayerFired()) {
			shot.expolde();
			health -= shot.getDamage();
			ScoringSingleton.getInstance().addScoreToCurrentWave(-shot.getDamage());
			if (health <= 0) {
				getStage().addActor(new LargeExplosion(getX(), getY()));
				setX(0);
				setY(0);
				health = 500;
			}
		}
		return false;
	}

	@Override
	public boolean isHitBy(AbstractShip ship) {
		if (ship instanceof EnemyShip) {
			health -= 100;
			ScoringSingleton.getInstance().addScoreToCurrentWave(-100);
			if (health <= 0) {
				getStage().addActor(new LargeExplosion(getX(), getY()));
				setX(0);
				setY(0);
				health = 500;
			}
		}
		return false;
	}
	
	@Override
	public boolean isAtPosition(float x, float y) {
		return hit(x, y) != null;
	}

	@Override
	public int getDeathScore() {
		return -100;
	}


}
