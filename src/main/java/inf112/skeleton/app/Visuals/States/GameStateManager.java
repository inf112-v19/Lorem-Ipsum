package inf112.skeleton.app.Visuals.States;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public State pop() {
        return states.pop();
    }

    public State peek() {
        return states.peek();
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
        states.peek().resize();
    }
}
