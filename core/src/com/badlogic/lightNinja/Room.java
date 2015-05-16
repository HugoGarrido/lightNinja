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
	//private int[][] lightMatrix;
	protected int[][] elmtMatrix;
	protected int[][] idMatrix;
	
	protected Array<Platform> platforms;
	protected Array<MobilePlatform> mobilePlatforms;
	protected Array<Artefact> artefacts;
	protected Array<Ennemi> ennemis;
	
	public Room(){
		platforms = new Array<Platform>();
		mobilePlatforms = new Array<MobilePlatform>();
		artefacts = new Array<Artefact>();
		ennemis = new Array<Ennemi>();
		
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
		fond = new Texture(Gdx.files.internal("bg/bg_level.png"));
		
		Platform sol = new Platform(Constante.ROOM_WIDTH, 1, 0, 0);
		addPlatform(sol);
		
		Platform murG = new Platform (2, Constante.ROOM_HEIGHT, 0, 0);
		addPlatform(murG);
		
		Platform murD = new Platform (1, Constante.ROOM_HEIGHT, Constante.ROOM_WIDTH -1, 0);
		addPlatform(murD);
		
		Platform plafond = new Platform(Constante.ROOM_WIDTH, 1, 0, Constante.ROOM_HEIGHT - 1);
		addPlatform(plafond);
		
		//zone 1
		
		Platform p1 = new Platform(4 + 2, 1, 0, 4);
		addPlatform(p1);
		
		Platform p2 = new Platform(2, 1, 10, 4);
		addPlatform(p2);
		
		Platform p3 = new Platform(2, 1, 10, 4);
		addPlatform(p3);
		
		Platform p4 = new Platform(2, 1, 12, 6);
		addPlatform(p4);
		
		Platform p5 = new Platform(1, 1, 14, 6);
		addPlatform(p5);
		
		//arene
		Platform p6 = new Platform(1, 6, 16, 1);
		addPlatform(p6);
		
		Platform p7 = new Platform(1, 6, 26, 1);
		addPlatform(p7);
		
		//sortie arene 1
		Platform p8 = new Platform(1, 1, 17, 4);
		addPlatform(p8);
		
		//sortie arene 2
		Platform p10 = new Platform(1, 1, 25, 4);
		addPlatform(p10);
		
		//arriere arene
		Platform p9 = new Platform(1, 2, 28, 1);
		addPlatform(p9);
		
		//fin zone 1
		Platform p11 = new Platform(1, 16, 34, 1);
		addPlatform(p11);
		
		
		//zone 2
		//longue plateforme
		Platform p12 = new Platform(16, 1, 1, 10);
		addPlatform(p12);
		
		Platform p13 = new Platform(4, 1, 30, 8);
		addPlatform(p13);
		
		Platform p14 = new Platform(4, 1, 20, 10);
		addPlatform(p14);
		
		Platform p15 = new Platform(5, 1, 1, 13);
		addPlatform(p15);
		
		Platform p16 = new Platform(2, 1, 1, 15);
		addPlatform(p16);
		
		Platform p17 = new Platform(1, 1, 6, 17);
		addPlatform(p17);
		
		Platform p18 = new Platform(14, 1, 6, 20);
		addPlatform(p18);
		
		Platform p19 = new Platform(1, 1, 2, 19);
		addPlatform(p19);
		
		Platform p20 = new Platform(14, 1, 24, 21);
		addPlatform(p20);
		
		// fin zone 2
		
		//départ zone 3
		Platform p21= new Platform(4, 1, 34, 6);
		addPlatform(p21);
		
		Platform p22= new Platform(14, 1, 60, 6);
		addPlatform(p22);
		
		Platform p23= new Platform(1, 1, 75, 4);
		addPlatform(p23);
		
		Platform p24= new Platform(8, 1, 52, 10);
		addPlatform(p24);
		
		Platform p25= new Platform(4, 1, 60, 14);
		addPlatform(p25);
		
		Platform p26= new Platform(14, 1, 64, 16);
		addPlatform(p26);
		
		Platform p27= new Platform(1, 5, 65, 17);
		addPlatform(p27);
		
		Platform p28= new Platform(1, 5, 76, 17);
		addPlatform(p28);
		
		Platform p29= new Platform(8, 1, 65, 21);
		addPlatform(p29);

		
//		MobilePlatform p5 = new MobilePlatform(8, 1, 10, 10, 10, 14, false);
//		addMobilePlatform(p5);
		
//		MobilePlatform p6 = new MobilePlatform(3, 1, 2, 5, 5, 9, true);
//		addMobilePlatform(p6);
		
		addArtefacts();
		addEnnemis();
	}
	
	public void addEnnemis(){
		
		Ennemi e1 = new Ennemi(20,1);
		e1.create(batch, this);
		ennemis.add(e1);
		
		Ennemi e2 = new Ennemi(8,12);
		e2.create(batch, this);
		ennemis.add(e2);
		
		Ennemi e3 = new Ennemi(35,30);
		e3.create(batch, this);
		ennemis.add(e3);
		
		Ennemi e4 = new Ennemi(40,12);
		e4.create(batch, this);
		ennemis.add(e4);
		
		Ennemi e5 = new Ennemi(50,6);
		e5.create(batch, this);
		ennemis.add(e5);
	}
	
	public void addArtefacts(){
		
		Artefact a1 = new Artefact(32, 1, TypeArtefact.LIFE);
		elmtMatrix[32][1] = 3;
		idMatrix[32][1] = a1.getId();
		a1.create(batch, camera, this);
		artefacts.add(a1);
		
		Artefact a2 = new Artefact(5, 11, TypeArtefact.POINT);
		elmtMatrix[5][11] = 3;
		idMatrix[5][11] = a2.getId();
		a2.create(batch, camera, this);
		artefacts.add(a2);
	}
	
	public void addMobilePlatform(MobilePlatform pMob){
		pMob.create(batch);
		
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
		p.create(batch);
		
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
		for (Ennemi e : ennemis){ 
			if (e.getLife() > 0)
				e.draw();
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
		for (Ennemi e : ennemis){ 
			if (e.getLife() > 0)
				e.render();
		}
	}
	
	public void dispose(){
		fond.dispose();
	}
}