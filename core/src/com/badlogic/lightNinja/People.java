package com.badlogic.lightNinja;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class People {
	protected Vector2 position;
	protected Room currentRoom;
	protected Rectangle rectangle;
	
	protected boolean isFalling;
	
	protected float walkStep;
	protected float fallStep;
	
	public void gravity(){
		if(this.position.x > 1 && this.position.x <= Constante.ROOM_WIDTH 
				&& this.position.y > 1 && this.position.y <= Constante.ROOM_HEIGHT ){
			
			if(currentRoom.elmtMatrix[(int)(this.position.x)][(int)(this.position.y - this.fallStep)] == 0
					&& currentRoom.elmtMatrix[(int)(this.position.x + 1)][(int)(this.position.y - this.fallStep)] == 0){
				this.position.y -= this.fallStep;
				this.isFalling = true;
			}
			else{
				this.isFalling = false;
			}
			
		}
	}
	
	public void move(int sens){
		
		if(position.x > 0 && position.x <= Constante.ROOM_WIDTH && position.y > 0 && position.y <= Constante.ROOM_HEIGHT ){
			if(sens > 0){
				if(currentRoom.elmtMatrix[(int)(position.x + sens * walkStep + rectangle.getWidth())][Math.round(position.y)] == 0
						&& currentRoom.elmtMatrix[(int)(position.x + sens * walkStep + rectangle.getWidth())][Math.round(position.y + rectangle.getHeight() / 2) ] == 0){
					position.x += sens * walkStep;
				}
				else{
					System.out.println("droite " + position.x);
				}
			}
			else{
				if(currentRoom.elmtMatrix[(int)(position.x + sens * walkStep)][Math.round(position.y)] == 0
						&& currentRoom.elmtMatrix[(int)(position.x + sens * walkStep)][Math.round(position.y + rectangle.getHeight() / 2) ] == 0){
					position.x += sens * walkStep;
				}
				else{
					System.out.println("gauche "+ position.x);
				}
			}
		}
	}
	
	public void updatePosition(){
		this.position.x = rectangle.x;
		position.y = this.rectangle.y;
	}
	
	public void updateRectangle(){
		rectangle.x = this.position.x;
		rectangle.y = this.position.y;
	}
}