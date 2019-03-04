package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();

    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop();
    }

    public void set(State state) {
        states.pop();
        states.push(state);

    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render() {
        states.peek().render();
    }

    public void resize() {
        //super.resize(width, height);
        states.peek().resize();
    }
}
