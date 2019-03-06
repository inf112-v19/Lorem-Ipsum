package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.Visuals.SpriteType;

public class RotationRightTile extends Tile {

    public RotationRightTile(GameObject[] gameObjects, Direction direction) {
        this.gameObjects = gameObjects;
        this.direction = direction;
        super.spriteType = SpriteType.ROTATION_RIGHT_TILE;

    }
}
