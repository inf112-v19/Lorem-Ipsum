package inf112.skeleton.app.GUI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public abstract class GameMap {

    protected ArrayList<Entity> entities;

    public GameMap() {
        entities = new ArrayList<>();
        entities.add(new Entity(0, 0, "Player", this));
    }

    public void render (OrthographicCamera camera, SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.render(batch);
        }
    }

    public void update () {
        for (Entity entity : entities) {
            entity.update();
        }
    }

    public abstract void dispose ();
}
