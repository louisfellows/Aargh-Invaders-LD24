package com.louisfellows.ld24.wave;

import com.louisfellows.ld24.actors.firingtype.FiringType;
import com.louisfellows.ld24.actors.movement.EnemyMovement;

public class FullRowWave extends Wave {
	
	public FullRowWave() {
		// Do Nowt, Superclass will handle it
	}

	public FullRowWave(FiringType firingType, EnemyMovement upperMovement,
			EnemyMovement lowerMovement) {
		super(firingType, upperMovement, lowerMovement);
	}

	@Override
	public void setupPositions() {
		positions = new Boolean[][]{{true,		false,		true,		false,		false},
									{true,		false,		true,		false,		false},
									{true,		false,		true,		false,		false},
									{true,		false,		true,		false,		false},
									{true, 		false,		true,		false,		false},
									{true,		false,		true,		false,		false},
									{true,		false,		true,		false,		false},
									{true,		false,		true,		false,		false},
									{true,		false,		true,		false,		false},
									{true,		false,		true,		false,		false}};
	}

	@Override
	public Wave getNewOfThisClass() {
		return new FullRowWave();
	}
}
