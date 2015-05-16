package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class FinishedLevel implements Screen{
	final LightNinjaGame game;

    public OrthographicCamera camera;
    private Texture bg;
    
    private Sound soundYata;
    private Sound soundClick;
   
    public FinishedLevel(final LightNinjaGame gam) {
        game = gam;

        this.bg = new Texture(Gdx.files.internal("bg/level_clear.png"));
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        
        soundYata = Gdx.audio.newSound(Gdx.files.internal("sound/yata.wav"));
        soundClick = Gdx.audio.newSound(Gdx.files.internal("sound/shuriken.mp3"));
        soundYata.play(1.0f);
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
        game.font.draw(game.batch, "Level Clear - Congratulation", 1024/2 - 150, 768/2);
        game.batch.end();

        if (Gdx.input.isTouched()) {
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
    	soundYata.dispose();
    	soundClick.dispose();
    }
}
