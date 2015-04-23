package com.badlogic.lightNinja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LightNinja extends ApplicationAdapter {
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Ninja ninja;
	
	@Override
	public void create () {
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		batch = new SpriteBatch();
		
		ninja = new Ninja();
		ninja.create(batch, camera);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2F, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
			ninja.draw();
		batch.end();
		
		ninja.render();
	}
	
	public void dispose(){
		ninja.dispose();
	}
}
