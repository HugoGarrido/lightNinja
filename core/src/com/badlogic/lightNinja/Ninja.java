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

public class Ninja extends People {
	
	private int life;
	private float speed;
	private int score = 12;
	private int strenghtPoint;
	private int shurikenJauge;
	
	private int detectionZone;
	private boolean lighted;
	
	private Texture ninjaImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private long lifespanSh = 1000000000L;
	private Array<Shuriken> shurikens;
	
	
	private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 3;
    
    private Animation walkAnimation;
    private Animation stillAnimation;
    private Animation jumpAnimation;
    private Animation fallingAnimation;
    
    private Texture spriteSheet;
    
    private TextureRegion[] walkFramesRight;
    private TextureRegion[] walkFramesLeft;
    private TextureRegion[] jumpFrames;
    private TextureRegion[] stillFrames;
    private TextureRegion[] fallingFrame;
    private TextureRegion currentFrame;

    private float orientation = 1;
    
    float stateTime;

	
	public Ninja(){}
	
	public int getLife()
	{
		return this.life;
	}
	public int getShuriken()
	{
		return this.shurikenJauge;
	}
	public Integer getScore()
	{
		Integer score = this.score;
		return score;
	}
	
	public void create(SpriteBatch batch, OrthographicCamera camera, Room room){
		////ninjaImage = new Texture(Gdx.files.internal("dark_ninja_still.png"));
		this.batch = batch;
		this.camera = camera;
		
		spriteSheet = new Texture(Gdx.files.internal("dark_ninja_spritesheet.png"));
		
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth()/FRAME_COLS, spriteSheet.getHeight()/FRAME_ROWS);
        
        TextureRegion[][] tmpFlip = tmp.clone();
       
        
        walkFramesRight = new TextureRegion[6 * 1];
        walkFramesLeft = new TextureRegion[6 * 1];
        jumpFrames = new TextureRegion[5 * 1];
        stillFrames = new TextureRegion[1 * 1];
        fallingFrame = new TextureRegion[1];
        
        int indexW = 0;
        int indexJ = 0;
        int indexS = 0;
        
        //still
        for(int i = 1 ; i < 2 ; i++){
        	for(int j = 0; j < 6; j++){
        		walkFramesRight[indexW++] = tmp[i][j];
        	}
        }
        
       
        
        for(int i = 2 ; i < 3 ; i++){
        	for(int j = 0; j < 5; j++){
        		jumpFrames[indexJ++] = tmp[i][j];
        	}
        }
        
        stillFrames[0] = tmp[0][0];
        fallingFrame[0] = tmp[2][2];
        
        walkAnimation = new Animation(0.080f, walkFramesRight);
        fallingAnimation = new Animation(0.080f, fallingFrame);
       
        jumpAnimation = new Animation(2.00f, jumpFrames);
        stillAnimation = new Animation(0.080f, stillFrames);
        stateTime = 0f;
		
		
		super.rectangle = new Rectangle();
		super.rectangle.x = 9;
		super.rectangle.y = 10;
		super.rectangle.width = 2;
		super.rectangle.height = 2;
		super.position = new Vector2(super.rectangle.x, super.rectangle.y);
		
		shurikens = new Array<Shuriken>();
		
		super.currentRoom = room;
		
		currentFrame = stillAnimation.getKeyFrame(stateTime, true);
		
		
		//super.walkStep = 0.1f;
		//super.gravity = 0.1f;
		
	}
	
	public void draw(){
		
		//batch.draw(walkSheet, super.rectangle.x*Constante.LENGHT_BOX, super.rectangle.y*Constante.LENGHT_BOX);
		

		
		batch.draw(currentFrame, super.rectangle.x*Constante.LENGHT_BOX, super.rectangle.y*Constante.LENGHT_BOX, 
				32, 32, 64 ,64 , 
				1, orientation, -90, false);
		
		if(shurikens.size != 0){
			for(Shuriken shrk : shurikens){
				shrk.draw();
			}
		}	
	}
	
	public Rectangle getRectangle(){
		return super.rectangle;
	}
	
	
	public void render(){
		float currentY = 0;
	
		stateTime += Gdx.graphics.getDeltaTime();
		
		//Attack
		if(Gdx.input.justTouched()){	
			attack(Gdx.input.getX(), Gdx.input.getY());
		}
		
		//Deplacement
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			this.orientation = -1;
			super.moveLeft();
			currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			this.orientation = 1;
			super.moveRight();
			currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)){
			super.jump();
			//currentFrame = jumpAnimation.getKeyFrame(stateTime, true);
		}
		
		if (!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)){
			currentFrame = stillAnimation.getKeyFrame(stateTime, true);
		}
		
		if(super.getJumpStatus() == true){
			currentFrame = jumpAnimation.getKeyFrame(stateTime, true);
		}
		if(super.getLandedStatus() == false){
			currentFrame = fallingAnimation.getKeyFrame(stateTime, true);
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
		
		super.computePosition();
		super.updateRectangle();
		
	}
	
	public void dispose(){
		spriteSheet.dispose();
	}

	private void attack(float destX, float destY) {
		
		Shuriken shuriken = new Shuriken();
		shuriken.create(batch, camera, super.rectangle.x + super.rectangle.getWidth()/2, super.rectangle.y + super.rectangle.getHeight()/2, destX / Constante.LENGHT_BOX, destY / Constante.LENGHT_BOX, TimeUtils.nanoTime());		
		shurikens.add(shuriken);
		
	}
	
	public Vector2 getPosition(){
		return this.position;
	}
}
