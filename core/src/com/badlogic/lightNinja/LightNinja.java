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
	private Room room;
	
	@Override
	public void create () {
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constante.ROOM_HEIGHT, Constante.ROOM_HEIGHT);
		
		batch = new SpriteBatch();

		room = new Room();
		room.create(batch, camera);
		
		ninja = new Ninja();
		ninja.create(batch, camera, room);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2F, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		    room.draw();
			ninja.draw();
		batch.end();
		
		ninja.render();
		room.render();
	}
	
	public void dispose(){
		ninja.dispose();
		room.dispose();
		batch.dispose();
	}
}
