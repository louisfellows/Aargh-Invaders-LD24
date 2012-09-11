package com.louisfellows.ld24.actors.movement;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Action;

public abstract class EnemyMovement {

	public abstract Action getActions();
	
	/**
	 * Returns list of uppermovement, lowermovement
	 * @return
	 */
	public static List<EnemyMovement> getRandomEnemyMovement() {
		int random = new Random().nextInt(6);
		switch (random) {
		case 0:
			return Arrays.asList(new CircleDownMovement(), new CircleUpMovement());
		case 1:
			//Third Entry forces 'EnemyMovement' type
			return Arrays.asList(new NoMovement(), new NoMovement(), new CircleDownMovement());
		case 2:
			return Arrays.asList(new RapidDownMovement(), new RapidUpMovement());
		case 3:
			return Arrays.asList(new LongCircleDownMovement(), new LongCircleUpMovement());
		case 4:
			//Third Entry forces 'EnemyMovement' type
			return Arrays.asList(new ChargeMovement(), new ChargeMovement(), new CircleDownMovement());
		case 5:
			//Third Entry forces 'EnemyMovement' type
			return Arrays.asList(new InvisibleMovement(), new InvisibleMovement(), new CircleDownMovement());
		}
		
		return null;
	}
}