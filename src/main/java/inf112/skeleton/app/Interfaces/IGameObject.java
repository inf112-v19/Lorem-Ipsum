package inf112.skeleton.app.Interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Position;

/**
 * Interface for game objects
 */
public interface IGameObject {

    /**
     * @return The position of the object
     */
    Position getPosition();

    /**
     * Place game object on the board
     * @param position the object should be placed at
     */
    void place(Position position);

    /**
     * Removes the object from the board
     */
    void remove();


    /**
     *function that returns the visuals of a GameObject
     * @return TextureRegion that shuold be drawn to screen
     */
    TextureRegion getTexture();
}
