package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.SpriteType;

public class RotationRightTile extends Tile {

    public RotationRightTile(GameObject[] gameObjects, Direction direction) {
        super(gameObjects, direction);
        super.spriteType = SpriteType.ROTATION_RIGHT_TILE;
    }

    @Override
    public void checkTile(Board board, Player player){
        player.turnPlayer(1);
    }
}
