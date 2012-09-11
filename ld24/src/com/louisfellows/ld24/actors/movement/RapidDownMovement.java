package com.louisfellows.ld24.actors.movement;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class RapidDownMovement extends EnemyMovement {
	
	public Action getActions() {
		return Actions.sequence(Actions.moveBy(-10, -10, 1, 	Interpolation.circle),
								Actions.moveBy(-10, 0,	 1, 	Interpolation.circle),
								Actions.moveBy(0, 	-10, 1, 	Interpolation.circle),
								Actions.moveBy(20, 0, 	 1, 	Interpolation.circle),
								Actions.moveBy(-0, 20,  1, 	Interpolation.circle));
	}

}
