package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GUI.BoardGUI;

public class PlayState extends State{

    private BoardGUI boardGUI;
    private SpriteBatch batch;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        boardGUI = new BoardGUI(camera, batch);
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render() {
        boardGUI.render();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize() {
        super.resize();
        batch.setProjectionMatrix(camera.combined);
        boardGUI.resize();
    }
}
