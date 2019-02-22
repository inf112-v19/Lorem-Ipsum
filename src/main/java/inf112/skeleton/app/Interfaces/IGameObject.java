package inf112.skeleton.app.Interfaces;

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
     * @param postion
     */
    void place(Position postion);

    /**
     * Removes the object from the board
     */
    void remove();
}
