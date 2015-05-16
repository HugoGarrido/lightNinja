package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class GameOver implements Screen{
    
	
	final LightNinjaGame game;

    public OrthographicCamera camera;
    
    private Sound soundGameOver;
    private Sound soundClick;
    
    public GameOver(final LightNinjaGame gam) {
        game = gam;
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        
        soundGameOver = Gdx.audio.newSound(Gdx.files.internal("sound/game_over.wav"));
        soundClick = Gdx.audio.newSound(Gdx.files.internal("sound/shuriken.mp3"));

        soundGameOver.play(1.0f);
    }

	
	@Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Game Over", 1024/2 - 50, 768/2);
        game.font.draw(game.batch, "Tap anywhere to continue", 1024/2 -50, 768/2 - 50);
        game.batch.end();

        if (Gdx.input.isTouched()) {
        	soundClick.play();
            game.setScreen(new Menu(game));
            dispose();
        }
    }
 
    public void resize(int width, int height) {
    }
 
   
    public void show() {
    }
 
    
    public void hide() {
    }
 
    
    public void pause() {
    }
 
    
    public void resume() {
    }
 
    
    public void dispose() {
    }
}
