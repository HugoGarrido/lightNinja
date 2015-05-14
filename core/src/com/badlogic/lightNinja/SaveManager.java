package com.badlogic.lightNinja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

public class SaveManager {
	
	
	public String generateJson(Object o){
		
		Json json = new Json();
		json.setOutputType(OutputType.json);
		
		String data = json.toJson(o, Object.class);
		return data;
	}
	
	public void writeFile(String fileName, String data) {
		FileHandle file = Gdx.files.local(fileName+".json");
		
		//Si le fichier n'existe pas on le créer et on l'écrase
		if(file != null && file.exists()){
			file.writeString(data, false);
		}
		//S'il existe, on écrit à la suite
		//tester s'il met à jouer les champs tout seul ou s'il ajoute à la suite
		else{
			file.writeString(data, true);
		}
	}
	
	public void saveGame(Object toSave, String fileName){
		//for(Object object : toSave){
			writeFile(fileName, generateJson(toSave));
		//}
	}
	
	public void getLocalPath(){
		System.out.println(Gdx.files.getLocalStoragePath());
	}
}
