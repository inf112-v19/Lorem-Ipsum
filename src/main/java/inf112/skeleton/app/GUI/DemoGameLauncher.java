package inf112.skeleton.app.GUI;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DemoGameLauncher {

    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        //em
        config.width = DemoGame.WIDTH;
        config.height = DemoGame.HEIGHT;
        config.title = DemoGame.TITLE;

        config.title = "Robo Rally";
        config.width = 576;
        config.height = 576;
        new LwjglApplication(new DemoGame(), config);
    }
}
