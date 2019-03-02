package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GUI.RoboRally;

public class MenuState extends State {

    Texture background;
    Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("tempStart.jpg");
        playButton = new Texture("tapToStart.gif");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        sb.draw(background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        sb.draw(playButton, (RoboRally.WIDTH / 2) - (playButton.getWidth() / 2), (RoboRally.HEIGHT)-((playButton.getHeight())*2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}

