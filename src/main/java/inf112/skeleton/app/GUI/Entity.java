package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Entity {

    protected Vector2 pos;
    protected String EntityID;
    protected GameMap map;

    Texture image;

    public Entity(float x, float y, String id, GameMap map) {
        this.pos = new Vector2(x, y);
        this.EntityID = id;
        this.map = map;
        image = new Texture("player.png");
    }

    /**
     * Player can be moved with arrow keys as a proof of concept
     */
    public void update () {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            moveX(-48);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            moveX(48);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            moveY(48);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            moveY(-48);
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y);
    }

    protected void moveX (float amount) {
        this.pos.x = this.pos.x + amount;
    }

    protected void moveY (float amount) {
        this.pos.y = this.pos.y + amount;
    }
}
