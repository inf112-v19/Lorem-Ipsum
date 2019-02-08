package inf112.skeleton.app.GUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    protected Vector2 pos;
    protected  EntityType type;
    protected GameMap map;

    public Entity(float x, float y, EntityType type, GameMap map) {
        this.pos = new Vector2(x, y);
        this.type = type;
        this.map = map;
    }

    public void update () {
    }

    public abstract void render(SpriteBatch batch);

    protected void moveX (float amount) {
        this.pos.x = this.pos.x + amount;
    }

    protected void moveY (float amount) {
        this.pos.y = this.pos.y + amount;
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
