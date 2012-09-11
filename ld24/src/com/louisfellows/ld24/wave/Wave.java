package com.louisfellows.ld24.wave;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.louisfellows.ld24.actors.EnemyShip;
import com.louisfellows.ld24.actors.Shot.Direction;
import com.louisfellows.ld24.actors.firingtype.FiringType;
import com.louisfellows.ld24.actors.firingtype.StraightShot;
import com.louisfellows.ld24.actors.movement.EnemyMovement;
import com.louisfellows.ld24.actors.movement.NoMovement;

public abstract class Wave {
	FiringType firingType;
	EnemyMovement upperMovement;
	EnemyMovement lowerMovement;
	Boolean[][] positions;
	
	public Wave() {
		firingType = new StraightShot(Direction.LEFT, false);
		upperMovement = new NoMovement();
		lowerMovement = new NoMovement();
		setupPositions();
	}
	
	public Wave(FiringType firingType, EnemyMovement upperMovement,
			EnemyMovement lowerMovement) {
		super();
		this.firingType = firingType;
		this.upperMovement = upperMovement;
		this.lowerMovement = lowerMovement;
		setupPositions();
	}
	
	public void setFiringType(FiringType firingType) {
		this.firingType = firingType;
	}
	
	public void setUpperMovement(EnemyMovement upperMovement) {
		this.upperMovement = upperMovement;
	}
	
	public void setLowerMovement(EnemyMovement lowerMovement) {
		this.lowerMovement = lowerMovement;
	}
	
	public FiringType getFiringType() {
		return firingType;
	}
	
	public EnemyMovement getLowerMovement() {
		return lowerMovement;
	}
	
	public EnemyMovement getUpperMovement() {
		return upperMovement;
	}
	
	public void createWave(Stage stage) {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 5; y++) {
				if (positions[x][y]) {
					EnemyMovement movement;
					if (x < 5) {
						movement = lowerMovement;
					} else {
						movement = upperMovement;
					}
					stage.addActor(EnemyShip.NewEnemyShip((x * 40), 
														  (y * 40) + 600, 
														  movement, 
														  firingType));
				}
			}
		}
	}
	
	public abstract void setupPositions();
	
	public Wave clone() {
		Wave cloneWave = getNewOfThisClass();
		cloneWave.setFiringType(firingType);
		cloneWave.setLowerMovement(lowerMovement);
		cloneWave.setUpperMovement(upperMovement);
		return cloneWave;
	}
	
	public abstract Wave getNewOfThisClass();
	
	public static Wave getRandomWaveType() {
		int random = new Random().nextInt(5);
		switch (random) {
		case 0:
			return new EdgeRowWave();
		case 1:
			return new AlternateRowWave();
		case 2:
			return new FullRowWave();
		case 3:
			return new FlyingVWave();
		case 4:
			return new LFWave();
		}
		return null;
	}
}
