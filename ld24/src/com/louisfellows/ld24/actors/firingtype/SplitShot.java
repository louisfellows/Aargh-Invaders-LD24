package com.louisfellows.ld24.actors.firingtype;

import java.util.Arrays;
import java.util.List;

import com.louisfellows.ld24.actors.Shot;
import com.louisfellows.ld24.actors.Shot.Direction;

public class SplitShot extends FiringType {

	public SplitShot(Direction direction, boolean player) {
		super(direction, player);
	}

	@Override
	public List<Shot> fire(float x, float y) {
		return Arrays.asList(new Shot(x, y, direction, player,4),new Shot(x, y, direction, player,-4));
	}

}
