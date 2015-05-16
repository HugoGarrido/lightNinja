package com.badlogic.lightNinja;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Ennemi extends People{

	private int life = 3;
	
	private int detectionZone;
	private boolean lighted;
	private Ninja ninja;
	
	private Texture ninjaImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private long lifespanSh = 150000000L;
	private Array<Shuriken> shurikens;
	
	private int cptFrame = 0;
	
	
	private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 3;
    
    private Animation walkAnimation;
    private Animation stillAnimation;
    private Animation jumpAnimation;
    private Animation fallingAnimation;
    
    private Texture texEnnemi;
   
    private float orientation = -1;
    private boolean detected = false;
    
    float stateTime;

	
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
	
	public void create(SpriteBatch batch, OrthographicCamera camera, Room room){
		this.batch = batch;
		this.camera = camera;
		
		texEnnemi = new Texture(Gdx.files.internal("deamon.png"));       
        
        stateTime = 0f;
		
		setShurikens(new Array<Shuriken>());
		
		super.currentRoom = room;
		
	}
	
	public void setNinja(Ninja ninja){
		this.ninja = ninja;
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

		//Deplacement
//		if (orientation == 1){
//			super.moveLeft();
//			currentFrame = walkAnimation.getKeyFrame(stateTime, true);
//		}
//		
//		if (orientation == -1){
//			super.moveRight();
//			currentFrame = walkAnimation.getKeyFrame(stateTime, true);
//		}
		
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
		shuriken.create(batch, camera, super.rectangle.x + super.rectangle.getWidth()/2, 
				super.rectangle.y + super.rectangle.getHeight()/2, destX, destY, TimeUtils.nanoTime(), false, orientation);		
		getShurikens().add(shuriken);
		
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

}
