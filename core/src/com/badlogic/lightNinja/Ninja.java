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
	private int score = 12;
	private int strenghtPoint;
	private int shurikenJauge;
	
	private int detectionZone;
	private boolean lighted;
	
	private Texture ninjaImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private long lifespanSh = 1000000000L;
	private Array<Shuriken> shurikens;
	
	
	public Ninja(){}
	
	public int getLife()
	{
		return this.life;
	}
	public int getShuriken()
	{
		return this.shurikenJauge;
	}
	public Integer getScore()
	{
		Integer score = this.score;
		return score;
	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera, Room room){
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
		
		//super.walkStep = 0.1f;
		//super.gravity = 0.1f;
		
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
			super.moveLeft();
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			super.moveRight();
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)){
			super.jump();
		}
		
//		if (!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)){
//			super.stay();
//		}
		
		Iterator<Shuriken> iter = shurikens.iterator();
		while(iter.hasNext()){
			Shuriken shrkn = iter.next();
			shrkn.moveTo();
			if((TimeUtils.nanoTime() - lifespanSh) >= shrkn.startTime){
				iter.remove();
				shrkn.dispose();
			}
		}
		
		super.computePosition();
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

//	private void jump(){
//		
//		super.position.y += isJumping;
//		isJumping -= fallStep;
//		if (isJumping <= 0)
//			isJumping = 0;
//		
//	}
//	
	public Vector2 getPosition(){
		return this.position;
	}
}
