package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GUI.SpriteSheet;
import inf112.skeleton.app.GUI.SpriteType;
import inf112.skeleton.app.Interfaces.IGameObject;
import inf112.skeleton.app.Position;

public abstract class GameObject implements IGameObject {

	protected Direction dir;
	protected SpriteType spriteType;


    public GameObject(Direction dir){
        this.dir = dir;
    }

    @Override
    public Position getPosition() {
        //return position;
        return null;
    }

    @Override
    public void place(Position position) {
        //board.setGameObject();  //has to be changed when board is finished
    }

    @Override
    public void remove() {
        //board.removeObject();    //has to be changed when board is finished
    }

	@Override
	public SpriteType getSpriteType() {
		return spriteType;
	}

	@Override
	public Direction getDirection() {
		return this.dir;
	}


}
