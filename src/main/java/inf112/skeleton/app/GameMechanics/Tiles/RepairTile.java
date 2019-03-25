package inf112.skeleton.app.GameMechanics.Tiles;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.SpriteSheet;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;

public class RepairTile extends Tile {

	public RepairTile(GameObject[] gameObjects, Direction direction) {
		super(gameObjects, direction);
		super.spriteType = SpriteType.REPAIR_TILE;
		this.setDrawable(new TextureRegionDrawable(new SpriteSheet().getTexture(this).getTexture()));

	}

	@Override
	public void checkTile(Board board, Player player){
		player.increaseHealth();
		player.increaseHealth();
		player.setBackup(board.getPlayerPos(player));
	}
}
