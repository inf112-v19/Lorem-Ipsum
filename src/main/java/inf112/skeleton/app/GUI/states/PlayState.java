package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GUI.BoardGUI;
import inf112.skeleton.app.GUI.RoboRally;
import inf112.skeleton.app.GUI.SpriteSheet;

public class PlayState extends State{

    private Texture car;
    private BoardGUI boardGUI;
    private SpriteBatch batch;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        boardGUI = new BoardGUI(camera, batch);

        //camera.setToOrtho(false, RoboRally.WIDTH/2, RoboRally.WIDTH / 2); // zoomer inn
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
