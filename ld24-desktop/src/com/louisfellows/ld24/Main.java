package com.louisfellows.ld24;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Aargh, Invaders!";
		cfg.useGL20 = false;
		cfg.width = 600;
		cfg.height = 400;
		
		new LwjglApplication(new Ld24(), cfg);
	}
}
