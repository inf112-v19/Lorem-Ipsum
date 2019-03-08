package inf112.skeleton.app.GameMechanics.GameObjects;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.Visuals.SpriteType;

public class Flag extends GameObject {

    public Flag(Direction dir){
        super(dir);
        spriteType = SpriteType.FLAG;
    }

}
