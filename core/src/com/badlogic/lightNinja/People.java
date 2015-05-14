package com.badlogic.lightNinja;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class People {
	protected Vector2 position;
	protected Vector2 direction;
	protected Vector2 lastDirection;
	protected Room currentRoom;
	protected Rectangle rectangle = new Rectangle();
	
	protected boolean isFalling;
	
	protected float walkStep;
	protected float jumpStep;
	
	protected float gravity;
	protected Vector2 jump;
	protected float jumping;
	protected boolean isLanded;
	protected boolean isJumping;
	protected int nbJump;

	public People(){
		this.direction = new Vector2(0,0);
		this.lastDirection = new Vector2(0,0);
		this.jump = new Vector2(0,0);
		this.gravity = 0.1f;
		this.walkStep = 0.1f;
		this.jumpStep = 1.0f;
		this.isLanded = false;
		this.isJumping = false;
	}

	public void gravity(){
		if(this.position.x > 1 && this.position.x <= Constante.ROOM_WIDTH 
			&& this.position.y > 1 && this.position.y <= Constante.ROOM_HEIGHT 
			&& currentRoom.elmtMatrix[(int)(this.position.x)][(int)(this.position.y - this.gravity)] == 0
			&& currentRoom.elmtMatrix[(int)(this.position.x + rectangle.getWidth()/2)][(int)(this.position.y - this.gravity)] == 0){
				this.direction.y -= this.gravity;
				this.isLanded = false;
			}
		else{
			this.isLanded = true;
			this.nbJump = 1;
		}	
	}
	
	public void moveLeft(){
		if(position.x > 0 && position.x <= Constante.ROOM_WIDTH 
			&& position.y > 0 && position.y <= Constante.ROOM_HEIGHT
			&& currentRoom.elmtMatrix[(int)(position.x - walkStep)][Math.round(position.y)] == 0
			&& currentRoom.elmtMatrix[(int)(position.x - walkStep)][Math.round(position.y + rectangle.getHeight() / 2) ] == 0){
				this.direction.x -= this.walkStep;
		}
	}
	
	public void moveRight(){
		if(position.x > 0 && position.x <= Constante.ROOM_WIDTH 
			&& position.y > 0 && position.y <= Constante.ROOM_HEIGHT
			&&currentRoom.elmtMatrix[(int)(position.x + walkStep + rectangle.getWidth())][Math.round(position.y)] == 0
			&& currentRoom.elmtMatrix[(int)(position.x + walkStep + rectangle.getWidth())][Math.round(position.y + rectangle.getHeight() / 2) ] == 0){
				this.direction.x += this.walkStep;
		}
	}
	
	public void moveUp(){
		if(currentRoom.elmtMatrix[(int)(position.x)][(int)(position.y + walkStep + rectangle.getHeight())] == 0
			&& currentRoom.elmtMatrix[(int)(position.x + rectangle.getWidth())][(int)(position.y + walkStep + rectangle.getHeight())] == 0) {
				this.direction.y += this.walkStep;
		}
	}
	
	public void moveDown(){
		if(currentRoom.elmtMatrix[(int)(position.x)][(int)(position.y - walkStep)] == 0
			&& currentRoom.elmtMatrix[(int)(position.x + rectangle.getWidth())][(int)(position.y - walkStep)] == 0) {
				this.direction.y -= this.walkStep;
		}
	}
	
	public void jump(){
		if(this.nbJump > 0){
				this.jumping = jumpStep;
				isJumping = true;
				this.nbJump--;
		}	
	}
	
	public void jumpUp(){
		if(currentRoom.elmtMatrix[(int)(position.x)][(int)(position.y + walkStep + rectangle.getHeight())] == 0
			&& currentRoom.elmtMatrix[(int)(position.x + rectangle.getWidth()/2)][(int)(position.y + walkStep + rectangle.getHeight())] == 0
			&& currentRoom.elmtMatrix[(int)(position.x + rectangle.getWidth())][(int)(position.y + walkStep + rectangle.getHeight())] == 0) {
				this.direction.y += this.jumping;
		}
		else{
			jumping = 0;
			isJumping = false;
		}
	}
	
	public void decrementJump(){
		if (this.jumping > 0){
			this.jumping -= this.walkStep;
			jumpUp();
		}
		else if (this.jumping == 0){
			jumping = 0;
			isJumping = false;
		}
		else isJumping = false;
	}
	
	public void movePlatform(){
		if(this.position.x > 1 && this.position.x <= Constante.ROOM_WIDTH 
			&& this.position.y > 1 && this.position.y <= Constante.ROOM_HEIGHT 
			&& currentRoom.elmtMatrix[(int) position.x][(int) (position.y - 0.5)] == 2){
				int idPlat = currentRoom.elmtMatrix[(int) position.x][(int) (position.y - 0.5)]- 1;
//				System.out.println("vertical ? " + currentRoom.mobilePlatforms.get(0).getVertical());
//				System.out.println("right ? " + currentRoom.mobilePlatforms.get(0).getRight());
//				System.out.println("idPlat : " + idPlat);
				
				//bug ici : id donné en dur pour le bien de tous
				if (currentRoom.mobilePlatforms.get(0).getVertical()){
					if (currentRoom.mobilePlatforms.get(0).getRight()){
						moveDown();
					}
					else moveUp();
				}
				else if(currentRoom.mobilePlatforms.get(0).getRight()){
					moveRight();
					//System.out.println("lol");
				}
				else moveLeft();
		}
	}

	public void computePosition(){
		
		//move avec platform
		movePlatform();
		
		//gravity
		if (!isLanded)
			this.direction.y -= this.gravity;
		else this.direction.y = 0;
		gravity();
		
		//jump
		//this.direction.add(jump);
		if (isJumping){
			decrementJump();
		}
		
		//compute
		this.position.add(direction);
		lastDirection = direction.cpy();
		direction.set(0, 0);
	}
	
	public void updateRectangle(){
		this.rectangle.x = this.position.x;
		this.rectangle.y = this.position.y;
		
		
	}
}
