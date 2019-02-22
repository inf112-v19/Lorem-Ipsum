package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.skeleton.app.GUI.DemoGame;

public class Main {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Robo Rally";
		config.width = 576;
		config.height = 576;

		new LwjglApplication(new RoboRally(), config);

	}

}
