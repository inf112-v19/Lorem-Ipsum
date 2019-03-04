package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.GUI.RoboRally;
import inf112.skeleton.app.GUI.SpriteSheet;
import inf112.skeleton.app.GUI.SpriteType;

public class MenuState extends State {

    private SpriteSheet spriteSheet;
    private TextureRegion background;
    private TextureRegion playButton;
    private SpriteBatch batch;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        spriteSheet = new SpriteSheet();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        background = spriteSheet.getTexture(SpriteType.MENU_BACKGROUND);
        playButton = spriteSheet.getTexture(SpriteType.MENU_PLAY_BUTTON);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new CardState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        batch.draw(playButton, (RoboRally.WIDTH / 2) - (playButton.getRegionWidth()/2),
                (playButton.getRegionHeight()));
        batch.end();
    }

    @Override
    public void dispose() {
        spriteSheet.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        batch.setProjectionMatrix(camera.combined);
    }
}

