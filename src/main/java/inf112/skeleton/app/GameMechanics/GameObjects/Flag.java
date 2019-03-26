package inf112.skeleton.app.GameMechanics.GameObjects;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.Visuals.SpriteType;

public class Flag extends GameObject {
	//index is the number corresponding to in which order the flag should be collected
	private final int index;

    public Flag(Direction dir, int index){
        super(dir);
        spriteType = SpriteType.FLAG;
        this.index = index;
    }

    public int getIndex() {
    	return index;
	}
}
