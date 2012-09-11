package com.louisfellows.ld24.gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.louisfellows.ld24.ScoringSingleton;
import com.louisfellows.ld24.actors.AbstractShip;
import com.louisfellows.ld24.actors.EnemyShip;
import com.louisfellows.ld24.actors.PlayerShip;
import com.louisfellows.ld24.actors.Shot;
import com.louisfellows.ld24.actors.Shot.Direction;
import com.louisfellows.ld24.actors.firingtype.FiringType;
import com.louisfellows.ld24.actors.movement.EnemyMovement;
import com.louisfellows.ld24.wave.Wave;

public class Simulation {	

	private static Array<Wave> waves = new Array<Wave>();
	private static List<Wave> pastwaves = new ArrayList<Wave>();
		
	public static void nextWave(Stage stage) {		
		Wave wave =	waves.pop();
		pastwaves.add(wave);
		wave.createWave(stage);
	}
	
	public static void checkHits(Stage stage, PlayerShip playerShip) {
		for (Shot act : getShots(stage)) {
			for (Actor isHit : stage.getActors()) {
				if (isHit instanceof AbstractShip) {
					if (((AbstractShip) isHit).isAtPosition(act.getX(), act.getY())) {
						((AbstractShip) isHit).isHitBy((Shot)act);
						break;
					}
				}
			}
		}
		for (EnemyShip ship : getEnemyShips(stage)) {
			for (PlayerShip player : getPlayerShips(stage)) {
				if (player.isAtPosition(ship.getX(), ship.getY()) ||
					player.isAtPosition(ship.getX() + 32, ship.getY()) ||
					player.isAtPosition(ship.getX(), ship.getY() + 32) ||
					player.isAtPosition(ship.getX() + 32, ship.getY() + 32)) {
						player.isHitBy(ship);
						ship.isHitBy(player);
				}
			}
		}
	}
	
	public static Array<Shot> getShots(Stage stage) {
		Array<Shot> shots = new Array<Shot>();
		for (Actor actor : stage.getActors()) {
			if (actor instanceof Shot) {
				shots.add((Shot)actor);
			}
		}
		return shots;	
	}
	
	public static Array<EnemyShip> getEnemyShips(Stage stage) {
		Array<EnemyShip> array = new Array<EnemyShip>();
		for (Actor actor : stage.getActors()) {
			if (actor instanceof EnemyShip) {
				array.add((EnemyShip)actor);
			}
		}
		return array;	
	}
	
	public static Array<PlayerShip> getPlayerShips(Stage stage) {
		Array<PlayerShip> array = new Array<PlayerShip>();
		for (Actor actor : stage.getActors()) {
			if (actor instanceof PlayerShip) {
				array.add((PlayerShip)actor);
			}
		}
		return array;	
	}
	
	public static void generateGenerations() {
		Map<Integer,Wave> scoreToWaveMap = new HashMap<Integer,Wave>();
		for (int i = 1; i <= 10; i++) {
			scoreToWaveMap.put(ScoringSingleton.getInstance().getWaveScore(i), pastwaves.get(i-1));
		}
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			Wave pastWave = scoreToWaveMap.get(ScoringSingleton.getInstance().getSortedWaveScores().get(i));
			waves.add(pastWave);
			//Mutation
			Wave clone = pastWave.clone();
			int mutation = random.nextInt(7);
			
			switch (mutation) {
			case 0: //Wave
				Wave randomWave = Wave.getRandomWaveType();
				randomWave.setFiringType(pastWave.getFiringType());
				randomWave.setLowerMovement(pastWave.getLowerMovement());
				randomWave.setUpperMovement(pastWave.getUpperMovement());
				clone = randomWave;
				break;
			case 1: //Shot
				clone.setFiringType(FiringType.getRandomFiringType(Direction.LEFT, false));				
				break;
			case 2: //Movement
				List<EnemyMovement> randomMovement = EnemyMovement.getRandomEnemyMovement();
				clone.setUpperMovement(randomMovement.get(0));
				clone.setLowerMovement(randomMovement.get(1));
				break;
			case 3: //Wave + Shot
				randomWave = Wave.getRandomWaveType();
				randomWave.setFiringType(FiringType.getRandomFiringType(Direction.LEFT, false));
				randomWave.setLowerMovement(pastWave.getLowerMovement());
				randomWave.setUpperMovement(pastWave.getUpperMovement());
				clone = randomWave;
				break;
			case 4: //Wave + Movement
				randomMovement = EnemyMovement.getRandomEnemyMovement();
				randomWave = Wave.getRandomWaveType();
				randomWave.setFiringType(pastWave.getFiringType());
				randomWave.setLowerMovement(randomMovement.get(1));
				randomWave.setUpperMovement(randomMovement.get(0));
				clone = randomWave;
				break;
			case 5: //Shot + Movement
				clone.setFiringType(FiringType.getRandomFiringType(Direction.LEFT, false));
				randomMovement = EnemyMovement.getRandomEnemyMovement();
				clone.setUpperMovement(randomMovement.get(0));
				clone.setLowerMovement(randomMovement.get(1));
				break;
			case 6: //All
				randomMovement = EnemyMovement.getRandomEnemyMovement();
				randomWave = Wave.getRandomWaveType();
				randomWave.setFiringType(FiringType.getRandomFiringType(Direction.LEFT, false));
				randomWave.setLowerMovement(randomMovement.get(1));
				randomWave.setUpperMovement(randomMovement.get(0));
				clone = randomWave;
				break;
			}
			waves.add(clone);
			pastwaves.clear();
		}
		waves.shuffle();
	}
	
	public static void generate10RandomWaves() {
		for (int i = 0; i < 10; i++) {
			List<EnemyMovement> randomMovement = EnemyMovement.getRandomEnemyMovement();
			Wave randomWave = Wave.getRandomWaveType();
			randomWave.setFiringType(FiringType.getRandomFiringType(Direction.LEFT, false));
			randomWave.setLowerMovement(randomMovement.get(1));
			randomWave.setUpperMovement(randomMovement.get(0));
			waves.add(randomWave);
		}
	}
	
}
