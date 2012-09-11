package com.louisfellows.ld24.actors.movement;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MoveOntoScreen extends EnemyMovement {

	@Override
	public Action getActions() {
		return Actions.moveBy(0, -250, 1);
	}

}
