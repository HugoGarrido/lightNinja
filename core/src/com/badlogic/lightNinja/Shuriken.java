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
	private Texture shurikenImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	public Vector2 destination;
	public Vector2 unit  = new Vector2();
	public long startTime;
	
	public void create(SpriteBatch batch, OrthographicCamera camera, float posX, float posY, float destX, float destY, long startTime){
		this.shurikenImage = new Texture(Gdx.files.internal("droplet.png"));
		this.batch = batch;
		this.camera = camera;
		
		this.shuriken = new Rectangle();
		this.shuriken.x = posX;
		this.shuriken.y = posY;
		this.shuriken.width = 64;
		this.shuriken.height = 64;
		
		this.destination = new Vector2();
		
		this.destination.x = destX;
		this.destination.y = destY;
		
		this.startTime = startTime;
		
		this.unit = new Vector2();
		Vector2 orientation = new Vector2();
		//Recuperer le vecteur allant de l'origine au center du shuriken
		Vector2 center = new Vector2(0,0);
		shuriken.getCenter(center);
		
		//System.out.println("center " + center);
		
		//Récupérer le vecteur de l'origine à la destination;
		Vector2 dest = new Vector2(0,0);
		dest = this.destination.cpy();
		
		//System.out.println("destination " + dest);
		
		//Calculer le vecteur qui va du ninja à la destination.
		//orientation = dest - center
		orientation = dest.add(center.scl(-1));
		orientation.scl(1, -1);
		//System.out.println("orientation " + orientation);
		
		//Normaliser orientation
		//Vector2 unit = orientation.scl(1/(dest.dst(center)));
		unit = orientation.nor(); 
		//System.out.println("unit " + unit);
	}
	
	public void draw(){
		this.batch.draw(shurikenImage, shuriken.x, shuriken.y); 
	}
	
	public Rectangle getRectangle(){
		return this.shuriken;
	}
	
	public void moveTo(){
		//float step = 30 * Gdx.graphics.getDeltaTime();
		
		int step = 3;
		Vector2 center = new Vector2(0,0);
		shuriken.getCenter(center);
		/*
		System.out.println("step " +step);
		System.out.println("center qui bouge " + center );
		*/
		Vector2 stepVect = unit.cpy().scl(step, step);
				
		//Bouger le shuriken
		
		shuriken.setCenter(center.cpy().add(stepVect));

	}
	
	public void dispose(){
		shurikenImage.dispose();
	}
}
