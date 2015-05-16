package com.badlogic.lightNinja;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LightNinja implements Screen {	
	
	final LightNinjaGame game;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private SpriteBatch batch_fixed;
	private Ninja ninja;
	private EndGame endGame;
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
		endGame = new EndGame();
		ninja.create(batch, camera, room, endGame);
		
		
		endGame.create(batch, camera, room, 25, 3);
		
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
			endGame.draw();
		batch.end();
		
		batch_fixed.begin();
			gui.draw(ninja);
		batch_fixed.end();
		
		
		ninja.render();
		room.render();
		gui.render();
		
		if(endGame.reachEndLevel(ninja)){
			game.setScreen(new FinishedLevel(game));
			dispose();
		};
		
		if(ninja.getLife() <= 0){
			game.setScreen(new GameOver(game));
			dispose();
		}
	}
	
	@Override
	public void dispose(){
		ninja.dispose();
		room.dispose();
		gui.dispose();
		endGame.dispose();
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
