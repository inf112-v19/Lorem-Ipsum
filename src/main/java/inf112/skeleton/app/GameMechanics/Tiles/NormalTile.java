package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;

public class NormalTile extends Tile {

	public NormalTile(GameObject[] gameObjects, Direction direction) {
		super(gameObjects, direction);
		super.spriteType = SpriteType.NORMAL_TILE;
	}


	@Override
	public void checkTile(Board board, Player player){
		for(int i=0;i<gameObjects.length;i++){
			if(gameObjects[i] instanceof Flag){
				player.increaseHealth();
				player.setBackup(board.getPlayerPos(player));
				player.collectFlag((Flag)gameObjects[i]);
				break;
			}
		}
	}
}
