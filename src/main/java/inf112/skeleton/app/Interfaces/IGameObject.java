package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.GameMechanics.Position;

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
     * function that gets the visuals of this GameObject
     * @return SpriteType of this GameObject
     */
    SpriteType getSpriteType();


    /**
     * function that returns a GameObject Direction on the Board
     * @return Direction of the GameObject
     */
    Direction getDirection();

}
