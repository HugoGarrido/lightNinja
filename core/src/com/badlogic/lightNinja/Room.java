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
	protected int[][] idMatrix;
	
	protected Array<Platform> platforms;
	protected Array<MobilePlatform> mobilePlatforms;
	protected Array<Artefact> artefacts;
	
	public Room(){
		platforms = new Array<Platform>();
		mobilePlatforms = new Array<MobilePlatform>();
		artefacts = new Array<Artefact>();
		
		elmtMatrix = new int[Constante.ROOM_WIDTH][Constante.ROOM_HEIGHT];
		for(int i=0; i< Constante.ROOM_WIDTH; i++){
			for(int j=0; j< Constante.ROOM_HEIGHT; j++){
				elmtMatrix[i][j] = 0;
			}
		}
		idMatrix = new int[Constante.ROOM_WIDTH][Constante.ROOM_HEIGHT];
		for(int i=0; i< Constante.ROOM_WIDTH; i++){
			for(int j=0; j< Constante.ROOM_HEIGHT; j++){
				idMatrix[i][j] = -1;
			}
		}

	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera){
		
		this.batch = batch;
		this.camera = camera;
		fond = new Texture(Gdx.files.internal("background_night.png"));
		
		Platform p1 = new Platform(Constante.ROOM_HEIGHT, 1, 0, 0);
		addPlatform(p1);
		
		Platform p2 = new Platform(1, 3, 8, 4);
		addPlatform(p2);
		
		Platform p3 = new Platform(7, 1, 10, 4);
		addPlatform(p3);
		
		Platform p4 = new Platform(9, 1, 19, 2);
		addPlatform(p4);
		
		MobilePlatform p5 = new MobilePlatform(8, 1, 10, 10, 10, 14, false);
		addMobilePlatform(p5);
		
		MobilePlatform p6 = new MobilePlatform(3, 1, 2, 5, 5, 9, true);
		addMobilePlatform(p6);
		
		Platform p7 = new Platform(1, 5, 5, 1);
		addPlatform(p7);
		
		Platform p8 = new Platform(5, 1, 25, 3);
		addPlatform(p8);
		
		Platform p9 = new Platform(5, 1, 30, 5);
		addPlatform(p9);
		
		Platform p10 = new Platform(5, 1, 36, 7);
		addPlatform(p10);
		
		Platform p11 = new Platform(5, 1, 40, 9);
		addPlatform(p11);
		
		Platform p12 = new Platform(5, 1, 46, 11);
		addPlatform(p12);
		
		addArtefacts();
	}
	
	public void addArtefacts(){
		for (int i = 0; i < Constante.ROOM_WIDTH; ++i){
			Artefact arte;
			if (i % 10 == 0){
				arte = new Artefact(i, 2, TypeArtefact.LIFE);
				elmtMatrix[i][2] = 3;
				idMatrix[i][2] = arte.getId();
			}
			else {
				arte = new Artefact(i, 2, TypeArtefact.POINT);
				elmtMatrix[i][2] = 4;
				idMatrix[i][2] = arte.getId();
			}
			arte.create(batch, camera, this);
			artefacts.add(arte);

		}
	}
	
	public void addMobilePlatform(MobilePlatform pMob){
		pMob.create(batch, camera);
		
		mobilePlatforms.add(pMob);
		if (pMob.getVertical()){
			for (int i = (int) pMob.posX; i < (int) pMob.posX + pMob.width; ++i){
				for (int j = (int) pMob.posA; j < (int) pMob.posB; ++j){
					idMatrix[i][j] = pMob.getId();
				}
			}
		}
		else {
			for (int i = (int)pMob.posA; i < (int) pMob.posB; ++i){
				for (int j =  (int) pMob.posX; j < (int) pMob.posX + pMob.height; ++j){
					idMatrix[i][j] = pMob.getId();
				}
			}
		}
		
	}
	
	
	public void addPlatform(Platform p){
		p.create(batch, camera);
		
		platforms.add(p);
		
		for(int i = 0; i < p.width; i++ ){
			for(int j = 0; j < p.height; j++){
				elmtMatrix[(int)p.posX + i][(int)p.posY + j] = 1;
			}
		}
	}
	
	
	public void updateNinja(Ninja n){
		Vector2 position = n.getPosition();
		elmtMatrix[Math.round(position.x)][Math.round(position.y)] = 13;
	}
	
	public void updatePlatform(){
		for (MobilePlatform p : mobilePlatforms){ 
			for (Box b : p.boxes){
				elmtMatrix[(int)b.getPosX()][(int)b.getPosY()] = 2;
			}
		}
	}
	
	
	public void draw(){
		batch.draw(fond, 0, 0);
		for (Platform p : platforms){ 
			p.draw();
		}
		for (MobilePlatform p : mobilePlatforms){ 
			p.draw();
		}
		for (Artefact a : artefacts){ 
			a.draw();
		}
	}
	
	public void render(){
		updatePlatform();
		for (Platform p : platforms){
			p.render();
		}
		for (MobilePlatform p : mobilePlatforms){ 
			p.render();
		}
		for (Artefact a : artefacts){ 
			a.render();
		}
	}
	
	public void dispose(){
		fond.dispose();
	}
}