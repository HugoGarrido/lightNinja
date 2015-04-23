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

public class Ninja {
	
	private int life;
	private float speed;
	private int score;
	private int strenghtPoint;
	private int shurikenJauge;
	
	private Vector2 position;
	private int step;
	
	
	private int dectectionZone;
	private boolean lighted;
	
	private Rectangle ninja;
	private Texture ninjaImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private long lifespanSh = 1000000000L;
	private Array<Shuriken> shurikens;
	
	
	
	public Ninja(){
		this.step = 8;
	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera){
		ninjaImage = new Texture(Gdx.files.internal("bucket.png"));
		this.batch = batch;
		this.camera = camera;
		
		ninja = new Rectangle();
		ninja.x = 800/2 - 64/2;
		ninja.y = 400/2 - 64/2;
		ninja.width = 64;
		ninja.height = 64;
		
		shurikens = new Array<Shuriken>();
		
	}
	
	public void draw(){
		batch.draw(ninjaImage, ninja.x, ninja.y);
		//fonction qui va afficher les bon shurikens
		
		if(shurikens.size != 0){
			for(Shuriken shrk : shurikens){
				shrk.draw();
			}
		}
		
	}
	
	public Rectangle getRectangle(){
		return this.ninja;
	}
	
	
	public void render(){
		//if(Gdx.input.isTouched()){
		if(Gdx.input.justTouched()){	
			//System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
			attack(Gdx.input.getX(), Gdx.input.getY());
			//attack(300, 300);
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			move(-1);
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			move(1);
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)){
			jump();
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
	}
	
	public void dispose(){
		ninjaImage.dispose();
	}

	private void attack(float destX, float destY) {
		System.out.println("attack");
		
		Shuriken shuriken = new Shuriken();
		shuriken.create(batch, camera, this.ninja.x + ninja.getWidth()/2, this.ninja.y + ninja.getHeight()/2, destX, destY, TimeUtils.nanoTime());		
		shurikens.add(shuriken);
		
	}
	
	private void move(int sens){
		System.out.println("move dans sens" + sens);
		int test = 0;
		if(this.ninja.x > 0){
			this.ninja.x +=sens * this.step;
		}
		
		if(this.ninja.x < 800 - ninjaImage.getWidth()){
			this.ninja.x +=sens * this.step;
			test = sens * this.step;
			System.out.println(ninja.x);
		}
		
	}
	
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
		
		System.out.println("jump");
	}
}
