package com.louisfellows.ld24.wave;

import com.louisfellows.ld24.actors.firingtype.FiringType;
import com.louisfellows.ld24.actors.movement.EnemyMovement;

public class FlyingVWave extends Wave {
	
	public FlyingVWave() {
		// Do Nowt, Superclass will handle it
	}

	public FlyingVWave(FiringType firingType, EnemyMovement upperMovement,
			EnemyMovement lowerMovement) {
		super(firingType, upperMovement, lowerMovement);
	}

	@Override
	public void setupPositions() {
		positions = new Boolean[][]{{false,		false,		false,		false,		false},
									{false,		false,		false,		true,		true},
									{false,		false,		true,		false,		false},
									{false,		true,		false,		false,		false},
									{true, 		false,		false,		false,		false},
									{true,		false,		false,		false,		false},
									{false,		true,		false,		false,		false},
									{false,		false,		true,		false,		false},
									{false,		false,		false,		true,		true},
									{false,		false,		false,		false,		false}};
	}

	@Override
	public Wave getNewOfThisClass() {
		return new FlyingVWave();
	}
}
