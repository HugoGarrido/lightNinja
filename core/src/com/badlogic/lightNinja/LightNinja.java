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
	private SpriteBatch batch_fixed;
	private Ninja ninja;
	private Ennemi ennemi;
	private Room room;
	private Gui gui;
	
	private float posCamX = 0;
	private float posCamY = 0;
	
	@Override
	public void create () {
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
		
		batch = new SpriteBatch();
		batch_fixed = new SpriteBatch();

		room = new Room();
		room.create(batch, camera);
		
		ennemi = new Ennemi();
		ennemi.create(batch, camera, room);
		
		ninja = new Ninja();
		ninja.create(batch, camera, room, ennemi);
		
		ennemi.setNinja(ninja);
		
		gui = new Gui();
		gui.create(batch_fixed, camera, room);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2F, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float stepCam = 0.05f;
	
		posCamX += (ninja.getRectangle().x * Constante.LENGHT_BOX - camera.position.x) * stepCam;
		posCamY += (ninja.getRectangle().y * Constante.LENGHT_BOX - camera.position.y) * stepCam;

		if (posCamX <= 512){
			posCamX = 512;
		}
		else if (posCamX >= Constante.ROOM_WIDTH * Constante.LENGHT_BOX - 512){
			posCamX = Constante.ROOM_WIDTH * Constante.LENGHT_BOX - 512;
		}
		if (posCamY  <= 388){
			posCamY = 388;
		}
		else if (posCamY >= Constante.ROOM_HEIGHT * Constante.LENGHT_BOX - 388){
			posCamY = Constante.ROOM_WIDTH * Constante.LENGHT_BOX - 388;
		}
		
		camera.position.set(posCamX, posCamY, 0);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		    room.draw();
			ninja.draw();
			if (ennemi.getLife() > 0)
				ennemi.draw();
		batch.end();
		
		batch_fixed.begin();
			gui.draw(ninja);
		batch_fixed.end();
		
		
		ninja.render();
		room.render();
		gui.render();
		
		if (ennemi.getLife() > 0)
			ennemi.render();
	}
	
	public void dispose(){
		ninja.dispose();
		room.dispose();
		batch.dispose();
	}
}
