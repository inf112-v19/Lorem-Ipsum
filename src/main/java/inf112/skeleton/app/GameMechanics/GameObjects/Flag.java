package inf112.skeleton.app.GameMechanics.GameObjects;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.Visuals.SpriteType;

public class Flag extends GameObject {

    private SpriteType spriteType;
    public Flag(Direction dir){
        super(dir);
    }

    public SpriteType getSpriteType(){
        return spriteType;
    }
}
