package com.louisfellows.ld24.actors.movement;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class CircleUpMovement extends EnemyMovement {
	
	public Action getActions() {
		return Actions.sequence(Actions.moveBy(80, 0, 1, 	Interpolation.swing), 
								Actions.moveBy(0, -160, 1, 	Interpolation.swing), 
								Actions.moveBy(-80, 0, 1, 	Interpolation.swing), 
								Actions.moveBy(0, 160, 1, 	Interpolation.swing));
	}

}
