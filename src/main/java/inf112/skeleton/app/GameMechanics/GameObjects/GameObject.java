package inf112.skeleton.app.GameMechanics.GameObjects;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.Interfaces.IGameObject;
import inf112.skeleton.app.GameMechanics.Position;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Objects;

public abstract class GameObject extends Image implements IGameObject {

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
	public SpriteType getSpriteType() {
		return spriteType;
	}

	@Override
	public Direction getDirection() {
		return this.dir;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dir, spriteType);
	}

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        if(this.hashCode() == obj.hashCode()) return true;

    	if (!obj.getClass().isInstance(this)) {
    		return false;
		}

		GameObject gameObject = (GameObject) obj;
    	return gameObject.getDirection().equals(this.dir);
    }

	@Override
	public int getWidth(ImageObserver imageObserver) {
		return 0;
	}

	@Override
	public int getHeight(ImageObserver imageObserver) {
		return 0;
	}

	@Override
	public ImageProducer getSource() {
		return null;
	}

	@Override
	public Graphics getGraphics() {
		return null;
	}

	@Override
	public Object getProperty(String s, ImageObserver imageObserver) {
		return null;
	}
}
