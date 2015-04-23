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

public class Box {
	
	private int posX;
	private int posY;
	private boolean lighted;
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	public Box (int posX, int posY, boolean lighted){
		this.posX = posX;
		this.posY = posY;
		this.lighted = lighted;
	}
	
	public int getPosX(){
		return posX;
	}
	public int getPosY(){
		return posY;
	}
	
	public void moveX(float move){
		posX += move;
	}
	public void moveY(float move){
		posY += move;
	}
	
	
	public boolean getLighted(){
		return lighted;
	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera){
		this.batch = batch;
		this.camera = camera;
	}
	
	public void draw(Texture tex) {
		batch.draw(tex, posX, posY);
	}
	
	public void render() {
		
	}
	
	public void dispose() {
	}

}