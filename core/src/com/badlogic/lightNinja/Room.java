package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Room extends Constante{
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	
	private Texture fond;
	private int[][] lightMatrix;
	protected int[][] elmtMatrix;
	
	private Array<Platform> platforms;
	
	public Room(){
		platforms = new Array<Platform>();
		
		elmtMatrix = new int[Constante.ROOM_WIDTH][Constante.ROOM_HEIGHT];
		for(int i=0; i< Constante.ROOM_WIDTH; i++){
			for(int j=0; j< Constante.ROOM_HEIGHT; j++){
				elmtMatrix[i][j] = 0;
			}
		}

	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera){
		
		this.batch = batch;
		this.camera = camera;
		fond = new Texture(Gdx.files.internal("expl_fond.jpg"));
		
		Platform p1 = new Platform(Constante.ROOM_HEIGHT, 1, 0, 0);
		addPlatform(p1);
		
		Platform p2 = new Platform(1, 3, 8, 4);
		addPlatform(p2);
		
		Platform p3 = new Platform(7, 1, 10, 4);
		addPlatform(p3);
		
		Platform p4 = new Platform(9, 1, 19, 2);
		addPlatform(p4);
		
		MobilePlatform p5 = new MobilePlatform(3, 1, 10, 10, 10, 17, false);
		addPlatform(p5);
		
		MobilePlatform p6 = new MobilePlatform(1, 3, 3, 5, 5, 11, true);
		addPlatform(p6);
		
		Platform p7 = new Platform(1, 5, 5, 1);
		addPlatform(p7);
		
	}
	
	
	public void addPlatform(Platform p){
		p.create(batch, camera);
		
		//Voir si c'est encore utile, pour l'instant on utilise pas le tableau de platform
		platforms.add(p);
		
		if(p instanceof Platform){
			for(int i = 0; i < p.width; i++ ){
				for(int j = 0; j < p.height; j++){
					elmtMatrix[p.posX + i][p.posY + j] = 1;
				}
			}
		}
		
		else if(p instanceof MobilePlatform){
			//To do
		}
	}
	
	
	public void updateNinja(Ninja n){
		Vector2 position = n.getPosition();
		elmtMatrix[Math.round(position.x)][Math.round(position.y)] = 13;
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