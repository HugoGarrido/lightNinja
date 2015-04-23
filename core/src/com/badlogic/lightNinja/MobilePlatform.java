package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class MobilePlatform extends Platform{
	
	private int posA;
	private int posB;
	private boolean vertical;
	private boolean right = true;
	

	public MobilePlatform(int width, int height, int posX, int posY, int posA, int posB, boolean vertical) {
		super(width, height, posX, posY);
		this.vertical = vertical;
		this.posA = posA;
		this.posB = posB;
		
	}
	
	public void render(){
		
		int cpt = 0;
		
		for (Box box : super.boxes) {
			
			if (vertical){
				if (cpt == 0){
					if (box.getPosY() <= posA * LENGHT_BOX) right = true;
					else if (box.getPosY() >= posB * LENGHT_BOX) right = false;
				}
				if (right) box.moveY(200 * Gdx.graphics.getDeltaTime());
				else box.moveY( - 200 * Gdx.graphics.getDeltaTime());
			}
			else {
				if (cpt == 0){
					if (box.getPosX() <= posA * LENGHT_BOX) right = true;
					else if (box.getPosX() >= posB * LENGHT_BOX) right = false;
				}
				if (right) box.moveX(200 * Gdx.graphics.getDeltaTime());
				else box.moveX( - 200 * Gdx.graphics.getDeltaTime());
			}
			++cpt;
		}		
		
		
	}

	
}