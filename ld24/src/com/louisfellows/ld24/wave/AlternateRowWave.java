package com.louisfellows.ld24.wave;

import com.louisfellows.ld24.actors.firingtype.FiringType;
import com.louisfellows.ld24.actors.movement.EnemyMovement;

public class AlternateRowWave extends Wave {
	
	public AlternateRowWave() {
		// Do Nowt, Superclass will handle it
	}

	public AlternateRowWave(FiringType firingType, EnemyMovement upperMovement,
			EnemyMovement lowerMovement) {
		super(firingType, upperMovement, lowerMovement);
	}

	@Override
	public void setupPositions() {
		positions = new Boolean[][]{{false,		false,		false,		false,		false},
									{true,		false,		false,		false,		false},
									{false,		true,		false,		false,		false},
									{true,		false,		false,		false,		false},
									{false, 	true,		false,		false,		false},
									{false,		true,		false,		false,		false},
									{true,		false,		false,		false,		false},
									{false,		true,		false,		false,		false},
									{true,		false,		false,		false,		false},
									{false,		false,		false,		false,		false}};
	}

	@Override
	public Wave getNewOfThisClass() {
		return new AlternateRowWave();
	}
}