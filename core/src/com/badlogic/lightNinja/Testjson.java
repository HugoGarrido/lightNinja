package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

public class Testjson {
	private int saveLife;
	private Vector2 savePosition;
	private int saveScore;
	
	
	public void print(){
		Json json = new Json();
		System.out.println(json.prettyPrint(this));
	}
	
	public void saveJson(String jsonName){
		Json json = new Json();
		json.setOutputType(OutputType.json);
		
		FileHandle file = Gdx.files.local(jsonName+".json");
		String lol = json.toJson(this, Object.class);
		file.writeString(lol, true);
//		//json.writeType(this.getClass());
//		//json.setElementType(Testjson.class, "ninja", Ninja.class);
//		
//		json.toJson(this, file);
//		//json.prettyPrint(this, file);
//		
//		PlayerScore score = new PlayerScore("Player1", 1537443);      // The Highscore of the Player1 
//		Json json = new Json();
//		String score = json.prettyPrint(score);
	}
	
	public void readJson(){
		
	}
	
	
	
//	public void saveNinja(Ninja n){
//		setLife(n);
//		setPosition(n);
//		setScore(n);
//	}
	
	
	public static void writeFile(String s, Json json) {
		FileHandle file = Gdx.files.local("level.json");
		System.out.println( Gdx.files.getLocalStoragePath());
		//file.writeString(com.badlogic.gdx.utils.toJson(), false);
		//file.toJson(json);
	}

}

