package inf112.skeleton.app.Interfaces;

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
}
