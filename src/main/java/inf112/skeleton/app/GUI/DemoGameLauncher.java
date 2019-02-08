package inf112.skeleton.app.GUI;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DemoGameLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Robo Rally";
        config.width = 576;
        config.height = 576;

        new LwjglApplication(new DemoGame(), config);
    }
}
