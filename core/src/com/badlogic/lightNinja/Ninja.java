package com.badlogic.lightNinja;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Ninja extends People {
	
	private int life;
	private float speed;
	private int score;
	private int strenghtPoint;
	private int shurikenJauge;
	
	private float isJumping;
	
	
	private int dectectionZone;
	private boolean lighted;
	
	private Texture ninjaImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private long lifespanSh = 1000000000L;
	private Array<Shuriken> shurikens;
	
	
	
	public Ninja(){
		
	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera, Room room){
		this.life = 15;
		this.score = 20;
		ninjaImage = new Texture(Gdx.files.internal("bucket.png"));
		this.batch = batch;
		this.camera = camera;
		
		super.rectangle = new Rectangle();
		super.rectangle.x = 9;
		super.rectangle.y = 10;
		super.rectangle.width = 2;
		super.rectangle.height = 2;
		super.position = new Vector2(super.rectangle.x, super.rectangle.y);
		
		shurikens = new Array<Shuriken>();
		
		super.currentRoom = room;
		
		super.walkStep = 0.1f;
		super.fallStep = 0.1f;
		
	}
	
	public void draw(){
		
		batch.draw(ninjaImage, super.rectangle.x*Constante.LENGHT_BOX, super.rectangle.y*Constante.LENGHT_BOX);
		
		if(shurikens.size != 0){
			for(Shuriken shrk : shurikens){
				shrk.draw();
			}
		}
		
	}
	
	public Rectangle getRectangle(){
		return super.rectangle;
	}
	
	
	public void render(){
		float currentY = 0;
		
		//Attack
		if(Gdx.input.justTouched()){	
			attack(Gdx.input.getX(), Gdx.input.getY());
		}
		
		//Deplacement
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			move(-1);
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			move(1);
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)){
			if(!isFalling){
				this.isJumping = 1;
			}
		}
		
		Iterator<Shuriken> iter = shurikens.iterator();
		while(iter.hasNext()){
			Shuriken shrkn = iter.next();
			shrkn.moveTo();
			if((TimeUtils.nanoTime() - lifespanSh) >= shrkn.startTime){
				iter.remove();
				shrkn.dispose();
			}
		}
		
		gravity();
		jump();
		
		
		updateRectangle();
		
	}
	
	public void dispose(){
		ninjaImage.dispose();
	}

	private void attack(float destX, float destY) {
		
		Shuriken shuriken = new Shuriken();
		shuriken.create(batch, camera, super.rectangle.x + super.rectangle.getWidth()/2, super.rectangle.y + super.rectangle.getHeight()/2, destX / Constante.LENGHT_BOX, destY / Constante.LENGHT_BOX, TimeUtils.nanoTime());		
		shurikens.add(shuriken);
		
	}
	
//	@Override
//	public void move(int sens){
//		
//	}
	
	private void jump(){
//		int step = 15;
//		int oldPosition = (int)ninja.y;
//		this.ninja.y += step * 2;
//		if(ninja.y > oldPosition){
//			
//		}
//		while(ninja.y != oldPosition){
//			ninja.y -= step;
//		}
		
		super.position.y += isJumping;
		isJumping -= fallStep;
		if (isJumping <= 0)
			isJumping = 0;
	}
	
	public Vector2 getPosition(){
		return this.position;
	}
	
	public int getLife(){
		return this.life;
	}

	public int getScore() {
		return this.score;
	}
	
	public void setLife(int damage){
		this.life -= damage;
	}
	
	
	public void setScore(int bonus){
		this.score += bonus;
	}
}
