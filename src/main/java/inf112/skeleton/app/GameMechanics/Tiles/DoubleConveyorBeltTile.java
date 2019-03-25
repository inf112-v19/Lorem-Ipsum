package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.SpriteType;

public class DoubleConveyorBeltTile extends Tile {

    public DoubleConveyorBeltTile(GameObject[] gameObjects, Direction direction) {
        super(gameObjects, direction);
        this.setDirection();
    }

    private void setDirection(){
        switch (direction){
            case NORTH:
                super.spriteType = SpriteType.DOUBLE_CONVEYOR_BELT_TILE_NORTH;
                break;
            case SOUTH:
                spriteType = SpriteType.DOUBLE_CONVEYOR_BELT_TILE_SOUTH;
                break;
            case EAST:
                spriteType = SpriteType.DOUBLE_CONVEYOR_BELT_TILE_EAST;
                break;
            case WEST:
                spriteType = SpriteType.DOUBLE_CONVEYOR_BELT_TILE_WEST;
                break;
            default:
                System.err.println("No valid Direction in DoubleConveyorBelt!");
                break;
        }
    }

    /**
     * Tells the player to do the action specified by the tile type
     * @param board current GameBoard
     * @param player the Player to preform the action
     */
    @Override
    public void checkTile(Board board, Player player){
        board.movePlayer(player, direction, 2);
    }
}
