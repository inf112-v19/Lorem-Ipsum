package inf112.skeleton.app.GameMechanics.GameObjects;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.Visuals.SpriteType;

public class Flag extends GameObject {
    //index is the number corresponding to in which order the flag should be collected
    private final int index;

    public Flag(Direction dir, int index) {
        super(dir);
        this.index = index;
        assignSpriteType();
    }

    public int getIndex() {
        return index;
    }

    private void assignSpriteType() {
        switch (index) {
            case 0:
                spriteType = SpriteType.FLAG1;
                break;
            case 1:
                spriteType = SpriteType.FLAG2;
                break;
            case 2:
                spriteType = SpriteType.FLAG3;
                break;
            case 3:
                spriteType = SpriteType.FLAG4;
                break;
            case 4:
                spriteType = SpriteType.FLAG5;
                break;
            case 5:
                spriteType = SpriteType.FLAG6;
                break;
            default:
                spriteType = SpriteType.FLAG1;
        }
    }
}
