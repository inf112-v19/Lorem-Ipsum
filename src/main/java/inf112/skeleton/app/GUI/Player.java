package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity {

    Texture image;

    public Player(float x, float y, GameMap map) {
        super(x, y, EntityType.PLAYER, map);
        image = new Texture("player.png");
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y);
    }

    /**
     * Player can be moved with arrow keys as a proof of concept
     */
    @Override
    public void update() {
        super.update();

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
}
