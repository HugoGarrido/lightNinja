package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Shuriken{
	private long reach = 1500;
	
	private Rectangle shuriken;
	private Texture projectileImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private float orientationNinja = -1;
	
	public Vector2 destination;
	public Vector2 unit  = new Vector2();
	public long startTime;
	
	public void create(SpriteBatch batch, OrthographicCamera camera, float posX, float posY, float destX, float destY, long startTime, boolean shurikenOrFire, float orientationNinja){
		if (shurikenOrFire == true){
			this.projectileImage = new Texture(Gdx.files.internal("shuriken.png"));
		}
		else this.projectileImage = new Texture(Gdx.files.internal("feu.png"));
		
		this.batch = batch;
		this.camera = camera;
		this.orientationNinja = orientationNinja;
		
		this.shuriken = new Rectangle();
		this.shuriken.x = posX;
		this.shuriken.y = posY;
		this.shuriken.width = 1;
		this.shuriken.height = 1;
		
		this.destination = new Vector2();
		
		this.destination.x = destX;
		this.destination.y = destY;
		
		this.startTime = startTime;
		
		this.unit = new Vector2();
		Vector2 orientation = new Vector2();
		//Recuperer le vecteur allant de l'origine au center du shuriken
		Vector2 center = new Vector2(0,0);
		shuriken.getCenter(center);
		
		
		//Récupérer le vecteur de l'origine à la destination;
		Vector2 dest = new Vector2(0,0);
		dest = this.destination.cpy();

		
		//Calculer le vecteur qui va du ninja à la destination.
		//orientation = dest - center
		orientation = dest.add(center.scl(-1));
		
		//Normaliser orientation
		//Vector2 unit = orientation.scl(1/(dest.dst(center)));
		unit = orientation.nor(); 
	}
	
	public void draw(){
		float angle = 0;
		if (orientationNinja == 1)
			angle = 180;
		
		batch.draw(projectileImage,  shuriken.x * Constante.LENGHT_BOX, shuriken.y * Constante.LENGHT_BOX, 16, 16,
				32, 32, 1, 1, angle,  0 , 0 , 32, 32, false, false);
	}
	
	public Rectangle getRectangle(){
		return this.shuriken;
	}
	
	public void moveTo(){
		
		float step = 0.4f;
		Vector2 center = new Vector2(0,0);
		shuriken.getCenter(center);
		
		Vector2 stepVect = unit.cpy().scl(step, step);
				
		//Bouger le shuriken
		
		shuriken.setCenter(center.cpy().add(stepVect));

	}
	
	public void dispose(){
		projectileImage.dispose();
	}
}
