package com.badlogic.lightNinja;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Ennemi extends People{

	private int life = 3;
	
	private SpriteBatch batch;
	
	private long lifespanSh = 150000000L;
	private Array<Shuriken> shurikens;
	
	private int cptFrame = 0;
    
    
    private Texture texEnnemi;
   
    private float orientation = -1;
    private boolean detected = false;
    
    float stateTime;

    private Sound soundAttack; 
	
	public Ennemi(int posX, int posY){
		super.rectangle = new Rectangle();
		super.rectangle.x = posX;
		super.rectangle.y = posY;
		super.rectangle.width = 2;
		super.rectangle.height = 2;
		super.position = new Vector2(super.rectangle.x, super.rectangle.y);
		
	}
	
	public int getLife()
	{
		return this.life;
	}
	
	public void create(SpriteBatch batch, Room room){
		this.batch = batch;
		
		texEnnemi = new Texture(Gdx.files.internal("sprites/deamon.png"));       
        
        stateTime = 0f;
		
		setShurikens(new Array<Shuriken>());
		
		super.currentRoom = room;
		
		soundAttack = Gdx.audio.newSound(Gdx.files.internal("sound/fireball.wav"));
	}
	
	
	public void draw(){
		
		batch.draw(texEnnemi, super.rectangle.x*Constante.LENGHT_BOX, super.rectangle.y*Constante.LENGHT_BOX);
		
		if(getShurikens().size != 0){
			for(Shuriken shrk : getShurikens()){
				shrk.draw();
			}
		}	
	}
	
	public Rectangle getRectangle(){
		return super.rectangle;
	}
	
	
	public void render(){
	
		stateTime += Gdx.graphics.getDeltaTime();
		
		//Attack
		if(cptFrame % 40 == 0 && isDetected()){	
			attack(super.rectangle.x + super.rectangle.getWidth() + 20 * getOrientation(), super.rectangle.y + super.rectangle.getHeight());
		}
		cptFrame++;
		
		Iterator<Shuriken> iter = getShurikens().iterator();
		while(iter.hasNext()){
			Shuriken shrkn = iter.next();
			shrkn.moveTo();
			if((TimeUtils.nanoTime() - lifespanSh) >= shrkn.startTime){
				iter.remove();
				shrkn.dispose();
			}
		}
		
		super.computePosition();
		super.updateRectangle();
		
	}

	private void attack(float destX, float destY) {
		Shuriken shuriken = new Shuriken();
		shuriken.create(batch, super.rectangle.x + super.rectangle.getWidth()/2, 
				super.rectangle.y + super.rectangle.getHeight()/2, destX, destY, TimeUtils.nanoTime(), false, orientation);		
		getShurikens().add(shuriken);
		soundAttack.play(0.6f);
		
	}

	public Vector2 getPosition(){
		return this.position;
	}
	
	public void setLife(int life) {
		this.life = life;
	}

	public Array<Shuriken> getShurikens() {
		return shurikens;
	}

	public void setShurikens(Array<Shuriken> shurikens) {
		this.shurikens = shurikens;
	}

	public float getOrientation() {
		return orientation;
	}

	public void setOrientation(float orientation) {
		this.orientation = orientation;
	}

	public boolean isDetected() {
		return detected;
	}

	public void setDetected(boolean detected) {
		this.detected = detected;
	}
	
	public void dispose(){
		soundAttack.dispose();
	}

}
