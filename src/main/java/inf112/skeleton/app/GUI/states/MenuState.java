package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GUI.DemoGame;

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
        sb.draw(background, 0, 0, DemoGame.WIDTH, DemoGame.HEIGHT);
        sb.draw(playButton, (DemoGame.WIDTH / 2) - (playButton.getWidth() / 2), (DemoGame.HEIGHT)-((playButton.getHeight())*2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}

