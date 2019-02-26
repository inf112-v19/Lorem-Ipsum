package inf112.skeleton.app.Cards;

import com.badlogic.gdx.graphics.Texture;

public enum CardType {
    BACKWARD_1(0, -1, new Texture("CardImages/BackUp.png")),
    FORWARD_1(0, 1, new Texture("CardImages/Move1.png")),
    FORWARD_2(0, 2, new Texture("CardImages/Move2.png")),
    FORWARD_3(0, 3, new Texture("CardImages/Move3.png")),
    ROTATE_180(2, 0, new Texture("CardImages/U-Turn.png")),
    ROTATE_90_L(-1, 0, new Texture("CardImages/LeftTurn.png")),
    ROTATE_90_R(1, 0, new Texture("CardImages/RightTurn.png"));

    private final int rotation;
    private final int movement;
    private Texture image;

    CardType(int rotation, int movement, Texture image) {
        this.rotation = rotation;
        this.movement = movement;
        this.image = image;
    }

    public int getRotation() {
        return rotation;
    }

    public int getMovement() {
        return movement;
    }

    public Texture getImage() {
        return image;
    }
}