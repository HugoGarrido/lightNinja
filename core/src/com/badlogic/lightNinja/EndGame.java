package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class EndGame {
	private Texture endGameImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	//private Intersector intersection;
	private Rectangle rectangle;
	
	public EndGame(){
		
	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera, Room room, float posX, float posY){
		this.batch = batch;
		this.camera = camera;
		
		endGameImage = new Texture(Gdx.files.internal("portail.png"));
		
		rectangle = new Rectangle();
		rectangle.width = 2;
		rectangle.height = 2;
		rectangle.x = posX;
		rectangle.y = posY;	
	}
	
	public void draw(){
		batch.draw(endGameImage, rectangle.x * Constante.LENGHT_BOX, rectangle.y * Constante.LENGHT_BOX);
	}
	
	public void render(){
	}
	
	public Rectangle getRectangle(){
		return this.rectangle;
	}
	
	public void dispose(){
		endGameImage.dispose();
	}
	
	public boolean reachEndLevel(Ninja n){
		
		if(n.getRectangle().overlaps(this.rectangle)){
			return true;
		}else{
			return false;
		}
		
	}
	
}
