package com.louisfellows.ld24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoringSingleton {
	private int score = 0;
	private List<Integer> waveScores;
	private static ScoringSingleton instance;
	private int wave = 0; //Will be increased to 1 at start
	private int generation = 1;
	
	
	public static ScoringSingleton getInstance() {
		if (instance == null) {
			instance = new ScoringSingleton();
		}
		return instance;
	}
	
	private ScoringSingleton() {
		waveScores = new ArrayList<Integer>();
		for (int i = 0; i <= 10; i++) {
			waveScores.add(0);
		}
	}
	
	public void resetWaveScores() {
		for (int i = 0; i <= 10; i++) {
			waveScores.set(i, 0);
		}
	}
	
	public int getWaveScore(int wave) {
		return waveScores.get(wave);
	}
	
	public List<Integer> getSortedWaveScores() {
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		sorted.addAll(waveScores);
		Collections.sort(sorted);
		Collections.reverse(sorted);
		return sorted;
	}
	
	public void addToWaveScore(int wave, int addedScore) {
		waveScores.set(wave, waveScores.get(wave) + addedScore);
		score += addedScore;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean increaseWave() {
		wave++;
    	if (wave > 10) {
    		generation++;
    		wave = 1;
    		return true;
    	}
    	return false;
	}
	
	public void addScoreToCurrentWave(int score) {
		addToWaveScore(wave, score);
	}
	
	public int getWave() {
		return wave;
	}
	
	public int getGeneration() {
		return generation;
	}
}
