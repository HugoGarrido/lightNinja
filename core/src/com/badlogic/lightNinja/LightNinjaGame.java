package com.badlogic.lightNinja;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LightNinjaGame extends Game{

	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("ninjaFont.fnt"));
		this.setScreen(new Menu(this));
	}

	public void render() {
        super.render(); //important!
    }

}
