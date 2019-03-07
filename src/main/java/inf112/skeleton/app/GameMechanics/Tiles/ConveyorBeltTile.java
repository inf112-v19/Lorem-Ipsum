package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.SpriteType;

public class ConveyorBeltTile extends Tile {

    public ConveyorBeltTile(GameObject[] gameObjects, Direction direction) {
        this.gameObjects = gameObjects;
        this.direction = direction;
        setDirection();
    }

    private void setDirection(){
        switch (direction){
            case NORTH:
                super.spriteType = SpriteType.CONVEYOR_BELT_TILE_NORTH;
                break;
            case SOUTH:
                spriteType = SpriteType.CONVEYOR_BELT_TILE_SOUTH;
                break;
            case EAST:
                spriteType = SpriteType.CONVEYOR_BELT_TILE_EAST;
                break;
            case WEST:
                spriteType = SpriteType.CONVEYOR_BELT_TILE_WEST;
                break;
            default:
                System.err.println("No valid Direction in ConveyorBelt!");
                break;
        }
    }

    /**
     * Tells the player to do the action specified by the tile type
     * @param board current GameBoard
     * @param player the Player to preform the action
     */
    public void checkTile(Board board, Player player) throws PlayerNotFoundException {
        board.movePlayer(player, direction);
    }

}
