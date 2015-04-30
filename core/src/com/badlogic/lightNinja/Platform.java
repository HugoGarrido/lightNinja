package com.badlogic.lightNinja;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Platform {
	
	final int LENGHT_BOX = 32;
	
	protected int width;
	protected int height;
	protected int posX;
	protected int posY;
	private Texture LightedTex;
	private Texture DarkTex;
	protected Array<Box> boxes;
	 
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	public Platform(int width, int height, int posX, int posY){
		this.width = width;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
	}
	
	private void ConstructBoxes() {
		for (int i = 0; i < width; ++i){
			for (int j = 0; j < height; ++j){
				Box box = new Box((i + this.posX )* LENGHT_BOX,( j + this.posY) * LENGHT_BOX, true);
				box.create(batch, camera);
				boxes.add(box);
			}
		}
		
	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera){
		
		this.batch = batch;
		this.camera = camera;
		LightedTex = new Texture(Gdx.files.internal("box_light.png"));
		DarkTex = new Texture(Gdx.files.internal("box_dark.png"));
		
		boxes = new Array<Box>();
		ConstructBoxes();
	}
	
	public void draw() {
		for (Box box : boxes) {
			if (box.getLighted())
				box.draw(LightedTex);
			else box.draw(DarkTex);
		}
	}
	
	public void render() {
		
	}
	
	public void dispose() {
		LightedTex.dispose();
		DarkTex.dispose();
		
	}

}