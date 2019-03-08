package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.skeleton.app.Visuals.RoboRally;

public class Main {

    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = RoboRally.WIDTH;
        config.height = RoboRally.HEIGHT;
        config.title = RoboRally.TITLE;

        new LwjglApplication(new RoboRally(), config);
    }
}
