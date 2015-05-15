package com.badlogic.lightNinja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LightNinja implements Screen {	
	
	final LightNinjaGame game;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private SpriteBatch batch_fixed;
	private Ninja ninja;
	private Room room;
	private Gui gui;
	
	private float posCamX = 0;
	private float posCamY = 0;
	
	LightNinja(LightNinjaGame gam){
		this.game = gam;

		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
		
		batch = new SpriteBatch();
		batch_fixed = new SpriteBatch();

		room = new Room();
		room.create(batch, camera);
		
		ninja = new Ninja();
		ninja.create(batch, camera, room);
		

		gui = new Gui();
		gui.create(batch_fixed, camera, room);
		
	}

	@Override
	public void render(float delta) {
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
		batch.end();
		
		batch_fixed.begin();
			gui.draw(ninja);
		batch_fixed.end();
		
		
		ninja.render();
		room.render();
		gui.render();
	}
	
	@Override
	public void dispose(){
		ninja.dispose();
		room.dispose();
		batch.dispose();
	}
	
	@Override
	public void show() {
    }
 
	@Override
    public void hide() {
    }
 
	@Override
    public void pause() {
    }
 
	@Override
    public void resume() {
    }
    
	@Override
	public void resize(int width, int height) {
    }

}
