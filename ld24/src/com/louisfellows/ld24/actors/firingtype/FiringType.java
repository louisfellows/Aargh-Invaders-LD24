package com.louisfellows.ld24.actors.firingtype;

import java.util.List;
import java.util.Random;

import com.louisfellows.ld24.actors.Shot;
import com.louisfellows.ld24.actors.Shot.Direction;

public abstract class FiringType {
	
	protected Direction direction;
	protected boolean player;
	
	public FiringType(Direction direction, boolean player) {
		this.direction = direction;
		this.player = player;
	}
	
	public abstract List<Shot> fire(float x, float y);
	
	public static FiringType getRandomFiringType(Direction direction, Boolean player) {
		int random = new Random().nextInt(4);
		switch (random) {
		case 0:
			return new StraightShot(direction, player);
		case 1:
			return new TwinFireShot(direction, player);
		case 2:
			return new SplitShot(direction, player);
		case 3:
			return new TripleShot(direction, player);
		}
		return null;
	}
}
