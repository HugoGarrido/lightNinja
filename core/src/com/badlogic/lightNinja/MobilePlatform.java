package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class MobilePlatform extends Platform{
	
	protected float posA;
	protected float posB;
	private boolean vertical;
	private boolean right = true;
	final int stepMoving = 3;

	private int id;
	private static int nextId = 0;
	
	

	public MobilePlatform(int width, int height, int posX, int posY, int posA, int posB, boolean vertical) {
		super(width, height, posX, posY);
		this.vertical = vertical;
		this.posA = posA;
		this.posB = posB;
		this.id = nextId;
		this.nextId++;
	}
	
	public int getId(){
		return id;
	}
	
	public boolean getVertical(){
		return vertical;
	}
	
	public boolean getRight(){
		return right;
	}
	
	public void moveY(int sens){
		for (Box box : super.boxes){
			box.moveY(sens * stepMoving * Gdx.graphics.getDeltaTime());
		}
		super.posY += sens * stepMoving * Gdx.graphics.getDeltaTime();
	}
	public void moveX(int sens){
		for (Box box : super.boxes){
			box.moveX(sens * stepMoving * Gdx.graphics.getDeltaTime());
		}
		super.posX += sens * stepMoving * Gdx.graphics.getDeltaTime();
	}
	
	public void render(){
		
		int cpt = 0;
		
		if (vertical){
			if((int)super.posY <= posA) right = true;
			else if ((int)super.posY >= posB) right = false;
			
			if (right) moveY(1);
			else moveY(-1);
		}
		else {
			if (super.posX <= posA) right = true;
			else if ((int)super.posX >= posB) right = false;
			
			if (right) moveX(1);
			else moveX(-1);
		}
		
//		for (Box box : super.boxes) {
//			
//			if (vertical){
//				if (cpt == 0){
//					if (box.getPosY() <= posA * LENGHT_BOX) right = true;
//					else if (box.getPosY() >= posB * LENGHT_BOX) right = false;
//				}
//				if (right) box.moveY(200 * Gdx.graphics.getDeltaTime());
//				else box.moveY( - 200 * Gdx.graphics.getDeltaTime());
//			}
//			else {
//				if (cpt == 0){
//					if (box.getPosX() <= posA * LENGHT_BOX) right = true;
//					else if (box.getPosX() >= posB * LENGHT_BOX) right = false;
//				}
//				if (right) box.moveX(200 * Gdx.graphics.getDeltaTime());
//				else box.moveX( - 200 * Gdx.graphics.getDeltaTime());
//			}
//			++cpt;
//		}		
		
		
	}

	
}