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
	
	protected Rectangle rect = new Rectangle();
	private boolean lighted;
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	public Box (float posX, float posY, boolean lighted){
		this.rect.x = posX;
		this.rect.y = posY;
		this.lighted = lighted;
	}
	
	public float getPosX(){
		return this.rect.x;
	}
	public float getPosY(){
		return this.rect.y;
	}
	
	public void moveX(float move){
		this.rect.x += move;
	}
	public void moveY(float move){
		this.rect.y += move;
	}
	
	
	public boolean getLighted(){
		return lighted;
	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera){
		this.batch = batch;
		this.camera = camera;
	}
	
	public void draw(Texture tex) {
		batch.draw(tex, this.rect.x * Constante.LENGHT_BOX, this.rect.y * Constante.LENGHT_BOX);
	}
	
	public void render() {}
	
	public void dispose() {}

}