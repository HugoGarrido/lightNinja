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
		
		endGameImage = new Texture(Gdx.files.internal("door.jpg"));
		
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
		/*
		Rectangle intersection = new Rectangle();
		
		Intersector.intersectRectangles(n.getRectangle(), this.rectangle, intersection);
		
		if((intersection.x > n.getRectangle().x) && 
				(intersection.y > n.getRectangle().y) && 
				(intersection.x + intersection.width < n.getRectangle().x + n.getRectangle().width) &&
				(intersection.y + intersection.height < n.getRectangle().y + n.getRectangle().height)
			){
			return true;
		}else{
			return false;
		}
    	*/
		
		if(n.getRectangle().overlaps(this.rectangle)){
			return true;
		}else{
			return false;
		}
		
	}
	
}
