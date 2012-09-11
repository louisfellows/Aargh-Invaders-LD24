package com.louisfellows.ld24.actors.movement;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ChargeMovement extends EnemyMovement {
	
	public Action getActions() {
		return Actions.sequence(Actions.fadeOut(0.4f),
								Actions.fadeIn(0.4f),
								Actions.moveBy(0, -560, 1, 	Interpolation.pow2In), 
								Actions.moveBy(0, 560, 5, 	Interpolation.linear));
	}

}

//Actions.moveBy(0, -5, 0.8f),
//Actions.moveBy(0, 5	, 0.8f),
//Actions.moveBy(0, -5, 0.8f),
//Actions.moveBy(0, 5	, 0.8f),
