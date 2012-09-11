package com.louisfellows.ld24;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.louisfellows.ld24.actors.PlayerShip;
import com.louisfellows.ld24.gameplay.Simulation;

public class Ld24 implements ApplicationListener {
	private Stage stage;
	private Label score_label;
	private Image health_image;
	private Label wave_label;
	private Label generation_label;
	private PlayerShip playerShip;
	
	@Override
	public void create() {		
		stage = new Stage(600,400,true);
		Gdx.input.setInputProcessor(stage);
		playerShip = new PlayerShip();
		stage.addActor(playerShip);
		stage.setKeyboardFocus(playerShip);
		LabelStyle style = new LabelStyle(new BitmapFont(), Color.WHITE);
		
		score_label = new Label("SCORE: " + ScoringSingleton.getInstance().getScore(), style);
		score_label.setX(500);
		score_label.setY(375);
		stage.addActor(score_label);
		
		health_image = new Image(new Texture(Gdx.files.internal("data/bar.png")));
		health_image.setX(5);
		health_image.setY(385);
		health_image.setScaleX(playerShip.getHealth() / 20);
		health_image.setColor(Color.WHITE);
		stage.addActor(health_image);
		
		wave_label = new Label("WAVE: " + ScoringSingleton.getInstance().getGeneration(), style);
		wave_label.setX(5);
		wave_label.setY(0);
		stage.addActor(wave_label);
		
		generation_label = new Label("GEN: " + ScoringSingleton.getInstance().getGeneration(), style);
		generation_label.setX(500);
		generation_label.setY(0);
		stage.addActor(generation_label);
		
		Simulation.generate10RandomWaves();
		
		Music music = Gdx.audio.newMusic(Gdx.files.internal("data/LD24BGM.wav"));
		music.setLooping(true);
		music.setVolume(1f);
		music.play();
	}

	@Override
	public void dispose() {
        stage.dispose();
	}

	@Override
	public void render() {		
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		health_image.setScaleX(playerShip.getHealth() / 20);
		score_label.setText("SCORE: " + ScoringSingleton.getInstance().getScore());
		wave_label.setText("WAVE: " + ScoringSingleton.getInstance().getWave());
		generation_label.setText("GEN: " + ScoringSingleton.getInstance().getGeneration());
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        Simulation.checkHits(stage, playerShip);
        if (Simulation.getEnemyShips(stage).size == 0) {
        	boolean newGen = ScoringSingleton.getInstance().increaseWave();
        	if (newGen) {
        		Simulation.generate10RandomWaves();
        	}
        	Simulation.nextWave(stage);
        }
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(600, 400, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
