package com.louisfellows.ld24.actors.movement;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class LongCircleDownMovement extends EnemyMovement {
	
	public Action getActions() {
		return Actions.sequence(Actions.moveBy(0, 0, 1, 	Interpolation.swing),
								Actions.moveBy(0, -320, 1, 	Interpolation.swing), 
								Actions.moveBy(-160, 0, 3, 	Interpolation.swing), 
								Actions.moveBy(0, 320, 1, 	Interpolation.swing), 
								Actions.moveBy(160,0, 3, 	Interpolation.swing));
	}

}
