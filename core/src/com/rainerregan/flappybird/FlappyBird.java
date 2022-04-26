package com.rainerregan.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background; // Seperti image | Background untuk game
	Texture[] birds;

	int flapState = 0;
	float birdY = 0;
	float velocity = 0;
	float gravity = 2;

	int gameState = 0;

	Texture topTube;
	Texture bottomTube;
	float gap = 400;

	float maxTubeOffset;
	Random rand;
	float tubeVelocity = 4;
	int numberOfTubes = 4;
	float[] tubeX =  new float[numberOfTubes];
	float[] tubeOffset = new float[numberOfTubes];
	float distanceBetweenTubes;

	@Override
	public void create () {
		batch = new SpriteBatch(); // Managing Sprites
		background = new Texture("bg.png"); // Mengambil sumber dari bg.png
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");

		birdY = Gdx.graphics.getHeight()/2 - birds[0].getHeight()/2;

		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");

		maxTubeOffset = Gdx.graphics.getHeight()/2- gap/2 -100;

		rand = new Random();

		distanceBetweenTubes = Gdx.graphics.getWidth() * 3/4;

		for (int i = 0; i < numberOfTubes; i++) {
			tubeOffset[i] = (rand.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);

			tubeX[i] = Gdx.graphics.getWidth() /2 -topTube.getWidth()/2 + i * distanceBetweenTubes;
		}
	}

	@Override
	public void render () {

		batch.begin(); // Begin Batch: Memberi tahu render untuk memulai
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Ketika game sudah dimulai
		if(gameState != 0) {
			// Jika disentuh, maka burung akan terbang karena velocitynya minus, dan akan terbang keatas
			if(Gdx.input.justTouched()){
				velocity = -30;

			}

			for (int i = 0; i < numberOfTubes; i++) {

				if (tubeX[i] < - topTube.getWidth()){
					tubeX[i] += numberOfTubes * distanceBetweenTubes;
				} else {
					tubeX[i] = tubeX[i] - tubeVelocity;
				}

				batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight()/2 + gap/2 + tubeOffset[i]);
				batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight()/2 -gap/2 - bottomTube.getHeight() + tubeOffset[i]);
			}



			if(birdY > 0 || velocity < 0) {
				// Kalau sudah tidak disentuh, burung akan jatuh kebawah sesuai dengan gravitasi
				velocity = velocity + gravity;
				birdY -= velocity;
			}
		} else {

			// Jika disentuh, permainan akan mulai
			if(Gdx.input.justTouched()){
				gameState = 1;
			}
		}

		// Memberi efek kepakan sayap
		if(flapState == 0){
			flapState = 1;
		} else {
			flapState = 0;
		}

		batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, birdY);
		batch.end(); // End render
	}
}
