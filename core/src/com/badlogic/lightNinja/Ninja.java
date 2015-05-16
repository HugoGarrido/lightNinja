package com.badlogic.lightNinja;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Ninja extends People {
	
	private int life = 10;
	private int score = 0;
	private int shurikenJauge;
	

	//private boolean lighted;
	
	private SpriteBatch batch;
	
	private long lifespanSh = 150000000L;
	private Array<Shuriken> shurikens;
	
	
	private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 3;
    
    private Animation walkAnimation;
    private Animation stillAnimation;
    private Animation jumpAnimation;
    private Animation fallingAnimation;
    
    private Texture spriteSheet;
    
    private TextureRegion[] walkFramesRight;
    private TextureRegion[] jumpFrames;
    private TextureRegion[] stillFrames;
    private TextureRegion[] fallingFrame;
    private TextureRegion currentFrame;

    private float orientation = 1;
    
    float stateTime;
    
    Sound soundShuriken;
    Sound soundArtefact; 
    Sound soundJump;
    Sound soundHit;
    
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
	
	public void create(SpriteBatch batch, Room room, EndGame end){
		this.batch = batch;
		
		
		spriteSheet = new Texture(Gdx.files.internal("spriteSheet/dark_ninja_spritesheet.png"));
		
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth()/FRAME_COLS, spriteSheet.getHeight()/FRAME_ROWS);
       
        
        walkFramesRight = new TextureRegion[6 * 1];
        jumpFrames = new TextureRegion[5 * 1];
        stillFrames = new TextureRegion[1 * 1];
        fallingFrame = new TextureRegion[1];
        
        int indexW = 0;
        int indexJ = 0;
        
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
		super.rectangle.x = 2;
		super.rectangle.y = 1;
		super.rectangle.width = 2;
		super.rectangle.height = 2;
		super.position = new Vector2(super.rectangle.x, super.rectangle.y);
		
		shurikens = new Array<Shuriken>();
		
		super.currentRoom = room;
		
		currentFrame = stillAnimation.getKeyFrame(stateTime, true);
		
		soundShuriken = Gdx.audio.newSound(Gdx.files.internal("sound/shuriken.mp3"));
		soundArtefact = Gdx.audio.newSound(Gdx.files.internal("sound/artefact.wav"));
		soundJump = Gdx.audio.newSound(Gdx.files.internal("sound/jump1.wav"));
		soundHit = Gdx.audio.newSound(Gdx.files.internal("sound/hit.wav"));
		
	}
	
	public void draw(){
		
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
	
		stateTime += Gdx.graphics.getDeltaTime();
		
		//Attack
		//if(Gdx.input.justTouched()){
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			attack(position.x + super.rectangle.width/2 + orientation, position.y + 1.5f);
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
			soundJump.play(0.7f);
			super.jump();
		}
		
		if (!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)){
			currentFrame = stillAnimation.getKeyFrame(stateTime, true);
		}
		
		checkArtefact();
		checkEnnemis();
		
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
		soundShuriken.dispose();
		soundArtefact.dispose();
		soundHit.dispose();
		soundJump.dispose();
	}

	private void attack(float destX, float destY) {
		
		Shuriken shuriken = new Shuriken();

		
		shuriken.create(batch, super.rectangle.x + super.rectangle.getWidth()/2, 
				super.rectangle.y + super.rectangle.getHeight()/2,
				destX,
				destY,
				TimeUtils.nanoTime(), true, orientation);		
		shurikens.add(shuriken);
		soundShuriken.play(1.0f);
		
	}
	
	public void checkArtefact(){
		Iterator<Artefact> iter = currentRoom.artefacts.iterator();
		while(iter.hasNext()){
			Artefact arte = iter.next();
			if(arte.getRectangle().overlaps(this.rectangle)){
				soundArtefact.play(1.0f);
				arte.action(this);
				iter.remove();
				arte.dispose();
			}
		}
	}
	
	public void checkShurikens(Ennemi ennemi){
		Iterator<Shuriken> iter = ennemi.getShurikens().iterator();
		while(iter.hasNext()){
			Shuriken shrk = iter.next();
			if(shrk.getRectangle().overlaps(this.rectangle)){
				this.life--;
				soundHit.play(1.0f);
				iter.remove();
				shrk.dispose();
			}
		}
	}
	
	public void checkEnnemis(){
		Iterator<Ennemi> iterEn = currentRoom.ennemis.iterator();
		while(iterEn.hasNext()){
			Ennemi en = iterEn.next();
			//check if the ninja is on the right or on the left to the ennemi
			if (en.rectangle.x - this.rectangle.x > 0){
				en.setOrientation(-1);
			}
			else en.setOrientation(1);
			//detection
			if (Math.abs(en.rectangle.x - this.rectangle.x) < 5 ){
				if(Math.abs(en.rectangle.y - this.rectangle.y) < 1){
					en.setDetected(true);
				}
				
			} else en.setDetected(false);
			
			checkShurikens(en);
			Iterator<Shuriken> iterSh = shurikens.iterator();
			while(iterSh.hasNext()){
				Shuriken shrk = iterSh.next();
				if(shrk.getRectangle().overlaps(en.rectangle)){
					en.setLife(en.getLife() - 1);
					iterSh.remove();
					shrk.dispose();
				}
			}
			if (en.getLife() == 0){
				score += 60;
				iterEn.remove();
			}
		}
	}

	public Vector2 getPosition(){
		return this.position;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Array<Shuriken> getShurikens() {
		return shurikens;
	}
}
