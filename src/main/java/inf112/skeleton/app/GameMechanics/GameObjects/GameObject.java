package inf112.skeleton.app.GameMechanics.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.Interfaces.IGameObject;
import inf112.skeleton.app.GameMechanics.Position;

import java.util.Objects;

public abstract class GameObject extends Image implements IGameObject {

	protected Direction dir;
	protected SpriteType spriteType;

	public GameObject(Direction dir) {
		this.dir = dir;
	}

	@Override
	public Position getPosition() {
		//return position;
		return null;
	}

    @Override
    public void place(Position position) {
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
		if (obj == null) return false;

		if (this.hashCode() == obj.hashCode()) return true;

		if (!obj.getClass().isInstance(this)) {
			return false;
		}

		GameObject gameObject = (GameObject) obj;
		return gameObject.getDirection().equals(this.dir);
	}


}
