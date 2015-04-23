package com.badlogic.lightNinja.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.lightNinja.LightNinja;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Light Ninja";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new LightNinja(), config);
	}
}
