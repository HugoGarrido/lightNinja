package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Room {
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	final int ROOM_WIDTH = 4000;
	final int ROOM_HEIGHT = 4000;
	
	private Texture fond;
	private int[][] lightMatrix;
	private int[][] elmtMatrix;
	
	private Array<Platform> platforms;
	
	public Room(){
		platforms = new Array<Platform>();

	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera){
		
		this.batch = batch;
		this.camera = camera;
		fond = new Texture(Gdx.files.internal("expl_fond.jpg"));
		
		Platform p1 = new Platform(1, 6, 0, 0);
		p1.create(batch, camera);
		platforms.add(p1);
		
		Platform p2 = new Platform(3, 1, 8, 2);
		p2.create(batch, camera);
		platforms.add(p2);
		
		Platform p3 = new Platform(1, 7, 10, 2);
		p3.create(batch, camera);
		platforms.add(p3);
		
		Platform p4 = new Platform(1, 9, 19, 2);
		p4.create(batch, camera);
		platforms.add(p4);
		
		MobilePlatform p5 = new MobilePlatform(1, 3, 10, 10, 10, 17, false);
		p5.create(batch, camera);
		platforms.add(p5);
		
		MobilePlatform p6 = new MobilePlatform(3, 1, 3, 5, 5, 11, true);
		p6.create(batch, camera);
		platforms.add(p6);
		
	}
	
	public void draw(){
		batch.draw(fond, 0, 0);
		for (Platform p : platforms){
			p.draw();
		}
	}
	
	public void render(){
		for (Platform p : platforms){
			p.render();
		}
	}
	
	public void dispose(){
		fond.dispose();
	}
}