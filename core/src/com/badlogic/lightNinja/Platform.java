package com.badlogic.lightNinja;



import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


public class Platform {
	
	final int LENGHT_BOX = 32;
	
	protected int width;
	protected int height;
	protected float posX;
	protected float posY;
	private Texture LightedTex;
	private Texture DarkTex;
	protected Array<Box> boxes;
	 
	private SpriteBatch batch;

	
	public Platform(int width, int height, int posX, int posY){
		this.width = width;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
	}
	
	private void ConstructBoxes() {
		for (int i = 0; i < width; ++i){
			for (int j = 0; j < height; ++j){
				Box box = new Box((i + (int) this.posX ),( j + (int) this.posY), true);
				box.create(batch);
				boxes.add(box);
			}
		}
		
	}
	
	public void create(SpriteBatch batch){
		
		this.batch = batch;
		LightedTex = new Texture(Gdx.files.internal("sol_3.png"));
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