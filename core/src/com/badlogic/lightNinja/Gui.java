package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Gui {
	
	private Texture shurikenImage;
	private Texture vieImage;
	private Texture carreVert;
	private BitmapFont font;
	private SpriteBatch batch;
	
	public Gui(){}
	
	public void create(SpriteBatch batch, Room room){
		vieImage = new Texture(Gdx.files.internal("sprites/vie.png"));
		shurikenImage = new Texture(Gdx.files.internal("sprites/shuriken.png"));
		carreVert = new Texture(Gdx.files.internal("sprites/carreVert.png"));
		this.batch = batch;
	}
	
	public void showLife(Ninja ninja)
	{
		for (int i=0; i<ninja.getLife(); i++)
		{
			batch.draw(carreVert, 72+(i*32), 685, 32, 22); 
		}	
	}
	public void showShuriken(Ninja ninja)
	{
		for (int j=0; j<ninja.getShuriken(); j++)
		{
			batch.draw(carreVert, 72+(j*32), 645, 32, 22); 
		}
	}
	public void showScore(Ninja ninja)
	{
		
		font = new BitmapFont(Gdx.files.internal("ninjaFont.fnt"));
		font.draw(batch, ninja.getScore().toString(), 20, 620);
	}
	
	public void draw(Ninja ninja){
		//vie
		batch.draw(vieImage, 20, 680, 32, 32);
		showLife(ninja);
		//shuriken
		batch.draw(shurikenImage, 20, 640, 32, 32);
		showShuriken(ninja);
		showScore(ninja);
	}	
	
	public void render(){
		
	}
	
	public void dispose(){
		vieImage.dispose();
		shurikenImage.dispose();
		carreVert.dispose();
	}
	
	
}
