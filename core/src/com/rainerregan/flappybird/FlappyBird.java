package com.rainerregan.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background; // Seperti image
	
	@Override
	public void create () {
		batch = new SpriteBatch(); // Managing Sprites
		background = new Texture("bg.png"); // Mengambil sumber dari bg.png
	}

	@Override
	public void render () {
		batch.begin(); // Begin Batch: Memberi tahu render untuk memulai
		batch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end(); // End render
	}
}
