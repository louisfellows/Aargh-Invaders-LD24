package com.louisfellows.ld24.wave;

import com.louisfellows.ld24.actors.firingtype.FiringType;
import com.louisfellows.ld24.actors.movement.EnemyMovement;

public class EdgeRowWave extends Wave {

	public EdgeRowWave() {
		//Do Nowt
	}
	
	public EdgeRowWave(FiringType firingType, EnemyMovement upperMovement,
			EnemyMovement lowerMovement) {
		super(firingType, upperMovement, lowerMovement);
	}

	@Override
	public void setupPositions() {
		positions = new Boolean[][]{{true,		true,		true,		true,		true},
									{false,		false,		false,		false,		false},
									{false,		false,		false,		false,		false},
									{false,		false,		false,		false,		false},
									{false, 	false,		false,		false,		false},
									{false,		false,		false,		false,		false},
									{false,		false,		false,		false,		false},
									{false,		false,		false,		false,		false},
									{false,		false,		false,		false,		false},
									{true,		true,		true,		true,		true}};
	}

	@Override
	public Wave getNewOfThisClass() {
		return new EdgeRowWave();
	}

}
