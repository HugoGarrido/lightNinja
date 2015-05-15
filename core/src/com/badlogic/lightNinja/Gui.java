package com.badlogic.lightNinja;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


public class Gui {
	//private Ninja ninja;
	private Texture shurikenImage;
	private Texture vieImage;
	private Texture carreVert;
	private BitmapFont font;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	
	public Gui(){}
	
	public void create(SpriteBatch batch, OrthographicCamera camera, Room room){
		vieImage = new Texture(Gdx.files.internal("vie_32x32.png"));
		shurikenImage = new Texture(Gdx.files.internal("shuriken_32x32.png"));
		carreVert = new Texture(Gdx.files.internal("carreVert_32x32.png"));
		this.batch = batch;
		this.camera = camera;
		
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
		
		font = new BitmapFont();
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
