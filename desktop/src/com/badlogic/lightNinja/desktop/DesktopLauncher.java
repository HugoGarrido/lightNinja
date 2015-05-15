package com.badlogic.lightNinja.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.lightNinja.LightNinja;
import com.badlogic.lightNinja.LightNinjaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Light Ninja";
		config.width = 1024;
		config.height = 768;
		new LwjglApplication(new LightNinjaGame(), config);
	}
}
