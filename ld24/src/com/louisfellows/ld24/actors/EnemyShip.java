package com.louisfellows.ld24.actors;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.louisfellows.ld24.ScoringSingleton;
import com.louisfellows.ld24.actors.Shot.Direction;
import com.louisfellows.ld24.actors.firingtype.FiringType;
import com.louisfellows.ld24.actors.firingtype.StraightShot;
import com.louisfellows.ld24.actors.movement.EnemyMovement;
import com.louisfellows.ld24.actors.movement.MoveOntoScreen;
import com.louisfellows.ld24.actors.movement.NoMovement;

public class EnemyShip extends AbstractShip {

	private EnemyMovement movement;
	
	private long lastShot;
	private static final long SHOT_SPEED = 500;
	
	public static EnemyShip NewEnemyShip(float x, float y, EnemyMovement movement, FiringType firingType) {
		return new EnemyShip(x, y, movement, firingType);
	}
	
	public EnemyShip(float x, float y) {
		this(x,y,new NoMovement(), new StraightShot(Direction.RIGHT, false));
	}
	
	public EnemyShip(float x, float y, EnemyMovement movement, FiringType firingType) {
		super();
		texture = new Texture(Gdx.files.internal("data/enemy.png"));	
		setX(x);
		setY(y);
		this.movement = movement;
		this.firingType = firingType;
		lastShot = System.currentTimeMillis() + (new Random().nextLong() % 500);
		addAction(new MoveOntoScreen().getActions());
		addAction(movement.getActions());
		health = 10;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (getActions().size == 0) {
			addAction(movement.getActions());
		}
		
		if (System.currentTimeMillis() > lastShot + SHOT_SPEED) { 
			fireShot();
		}
		
		super.draw(batch, parentAlpha);
	}
	
	@Override
	public Actor hit(float x, float y) {
		return super.hit(x, y);
	}
	
	@Override
	protected void fireShot() {
		for (Actor act: firingType.fire(getX() + (texture.getHeight()/2), getY() + texture.getWidth())) {
			getStage().addActor(act);
		}
		laser.play(0.5f);
		lastShot = System.currentTimeMillis();
	}

	@Override
	public boolean isHitBy(Shot shot) {
		if (shot.isPlayerFired()) {
			health -= shot.getDamage();
			if (health <= 0) {
				explode();
			}
			shot.expolde();
			return true;
		}
		return false;
	}

	@Override
	public boolean isHitBy(AbstractShip ship) {
		if (ship instanceof PlayerShip) {
			health -= 100;
			if (health <= 0) {
				explode();
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
		return 10;
	}

}
