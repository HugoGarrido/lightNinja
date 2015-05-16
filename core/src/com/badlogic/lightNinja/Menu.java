package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class Menu implements Screen{
    
	
	final LightNinjaGame game;

    public OrthographicCamera camera;
    private Texture bg;
    
    Sound soundTitle;
    Sound soundClick;

    public Menu(final LightNinjaGame gam) {
        game = gam;
        
        bg = new Texture(Gdx.files.internal("bg/main_menu.png"));
        
        soundTitle = Gdx.audio.newSound(Gdx.files.internal("sound/light_ninja_intro.wav"));
        soundClick = Gdx.audio.newSound(Gdx.files.internal("sound/shuriken.mp3"));
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        soundTitle.play();
    }
	
	@Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(bg, 0,0);
        //To do -> scale
        game.font.draw(game.batch, "Light Ninja", 1024/2 - 75, 768/2);
        game.font.draw(game.batch, "Tap anywhere to begin", 1024/2 -140, 768/2 - 50);
        game.batch.end();

        
        if (Gdx.input.justTouched()) {
        	soundClick.play();
            game.setScreen(new LightNinja(game));
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
    	bg.dispose();
    	soundTitle.dispose();
    	soundClick.dispose();
    }
}
