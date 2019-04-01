package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.Visuals.SpriteType;

public class SpawnTile extends Tile{

    public SpawnTile(GameObject[] gameObjects, Direction direction) {
        super(gameObjects, direction);
        super.spriteType = SpriteType.SPAWN_TILE;
    }

    
}
