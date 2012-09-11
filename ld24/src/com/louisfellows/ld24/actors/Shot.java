package com.louisfellows.ld24.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Shot extends LD24Actor {

	private Texture texture;
	private Direction direction;
	private static final float SHOT_SPEED = 5;
	private float shotAngle = 0;
	private int damage = 10;
	private boolean playerFired;
		
	public Shot(float startX, float startY, Direction direction, boolean playerFired){
		texture = new Texture(Gdx.files.internal("data/shot.png"));	
		setX(startX);
		setY(startY);
		this.direction = direction;
		this.playerFired = playerFired;
	}
	
	public Shot(float startX, float startY, Direction direction, boolean playerFired, float xSpeed){
		texture = new Texture(Gdx.files.internal("data/shot.png"));	
		setX(startX);
		setY(startY);
		this.direction = direction;
		this.playerFired = playerFired;
		shotAngle = xSpeed;
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		switch (direction) {
		case LEFT:
			setY(getY() - SHOT_SPEED);
			break;
		case RIGHT:
			setY(getY() + SHOT_SPEED);
			break;
		}
		if (getY() > 620 || getY() < -20) {
			remove();
		}
		setX(getX() + shotAngle);
		
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture,getY(),getX());
	}
	
	public enum Direction {
		LEFT, RIGHT;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isPlayerFired() {
		return playerFired;
	}
	
	@Override
	public boolean isAtPosition(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void expolde() {
		getStage().addActor(new SmallExplosion(getX(), getY()));
		remove();
	}
}
