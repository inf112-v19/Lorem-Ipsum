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
        background = new Texture("tempBG.png");
        playButton = new Texture("player.png");
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
        handleInput();M
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, DemoGame.WIDTH, DemoGame.HEIHGT);
        sb.draw(playButton, (DemoGame.WIDTH / 2) - (playButton.getWidth() / 2), (DemoGame.HEIHGT / 4));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}

