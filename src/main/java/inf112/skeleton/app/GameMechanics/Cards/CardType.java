package inf112.skeleton.app.GameMechanics.Cards;

import inf112.skeleton.app.Visuals.SpriteType;

public enum CardType {
    BACKWARD_1(0, -1, SpriteType.BACKWARD_1),
    FORWARD_1(0, 1, SpriteType.FORWARD_1),
    FORWARD_2(0, 2, SpriteType.FORWARD_2),
    FORWARD_3(0, 3, SpriteType.FORWARD_3),
    ROTATE_180(2, 0, SpriteType.ROTATE_180),
    ROTATE_90_L(-1, 0, SpriteType.ROTATE_90_L),
    ROTATE_90_R(1, 0, SpriteType.ROTATE_90_R);

    private final int rotation;
    private final int movement;
    private SpriteType sprite;

    CardType(int rotation, int movement, SpriteType sprite) {
        this.rotation = rotation;
        this.movement = movement;
        this.sprite = sprite;
    }

    public int getRotation() {
        return rotation;
    }

    public int getMovement() {
        return movement;
    }

    public SpriteType getSprite() {
        return sprite;
    }

    public CardType getCardTypeByString(String s) {
        switch (s) {
            case "BACKWARD_1":
                return BACKWARD_1;
            case "FORWARD_1":
                return FORWARD_1;
            case "FORWARD_2":
                return FORWARD_2;
            case "FORWARD_3":
                return FORWARD_3;
            case "ROTATE_180":
                return ROTATE_180;
            case "ROTATE_90_L":
                return ROTATE_90_L;
            case "ROTATE_90_R":
                return ROTATE_90_R;
            default:
                System.out.println("Invalid input CardType");
                return FORWARD_1;
        }
    }
}