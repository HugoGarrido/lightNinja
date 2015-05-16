package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Artefact {
	

	private SpriteBatch batch;
	OrthographicCamera camera;
	private Texture tex;
	
	private Rectangle rectangle;
	private boolean picked = false;
	private TypeArtefact type;
	
	private int id;
	private static int nextId = 0;
	
	public Artefact(float posX, float posY, TypeArtefact type){
		this.type = type;
		this.setRectangle(new Rectangle(posX, posY, 1, 1));
		this.setId(nextId);
		this.nextId++;
	}
	
	
	public void pickedUp(Ninja ninja){
		if (picked == false)
			action(ninja);
		picked = true;
	}
	
	public void action(Ninja ninja){
		if (type == TypeArtefact.POINT){
			ninja.setScore(ninja.getScore() + 1);
		}
		else if (type == TypeArtefact.LIFE){
			if (ninja.getLife() < 10)
				ninja.setLife(ninja.getLife() + 1);
		}
	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera, Room currentRoom){
		this.batch = batch;
		this.camera = camera;
		if (type == TypeArtefact.POINT){
			tex = new Texture(Gdx.files.internal("shuriken.png"));
		}
		else if (type == TypeArtefact.LIFE){
			tex = new Texture(Gdx.files.internal("vie.png"));
		}
	}
	
	public void draw(){
		if (picked == false)
			batch.draw(tex, this.getRectangle().x * Constante.LENGHT_BOX, this.getRectangle().y * Constante.LENGHT_BOX);
	}
	
	public void render(){

	}
	
	public void dispose(){
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Rectangle getRectangle() {
		return rectangle;
	}


	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
}
