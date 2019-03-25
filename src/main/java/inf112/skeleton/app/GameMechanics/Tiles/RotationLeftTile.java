package inf112.skeleton.app.GameMechanics.Tiles;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.SpriteSheet;
import inf112.skeleton.app.Visuals.SpriteType;

public class RotationLeftTile extends Tile{

    public RotationLeftTile(GameObject[] gameObjects, Direction direction) {
        super(gameObjects,direction);
        super.spriteType = SpriteType.ROTATION_LEFT_TILE;
    }

    @Override
    public void checkTile(Board board, Player player){
        player.turnPlayer(-1);
    }
}
