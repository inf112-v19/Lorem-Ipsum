package inf112.skeleton.app.GUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    protected Vector2 pos;
    protected  EntityType type;
    protected float velocityY;
    protected GameMap map;
    protected boolean grounded = false;

    public Entity(float x, float y, EntityType type, GameMap map) {
        this.pos = new Vector2(x, y);
        this.type = type;
        this.map = map;
    }

    public void update (float deltatime, float gravity) {

    }

    public abstract void render(SpriteBatch batch);

    protected void moveX (float amount) {
        float newX = this.pos.x + amount;
        //kollisjonstest
        this.pos.x = newX;

    }

    protected void moveY (float amount) {
        float newY = this.pos.y + amount;
        //kollisjonstest
        this.pos.y = newY;

    }





    public Vector2 getPos() {
        return pos;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public EntityType getType() {
        return type;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public int getHeight() {
        return type.getHeight();
    }

    public int getWidth() {
        return type.getWidth();
    }

    public int getWeight() {
        return type.getWeight();
    }


}
