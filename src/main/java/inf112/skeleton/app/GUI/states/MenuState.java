package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.GUI.RoboRally;
import inf112.skeleton.app.GUI.SpriteSheet;
import inf112.skeleton.app.GUI.SpriteType;

public class MenuState extends State {

    private SpriteSheet spriteSheet;
    private TextureRegion background;
    private TextureRegion playButton;
    //private Texture background;
    //private Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        spriteSheet = new SpriteSheet();
        //background = new Texture("tempStart.jpg");
        background = spriteSheet.getTexture(SpriteType.MENU_BACKGROUND);
        playButton = spriteSheet.getTexture(SpriteType.MENU_PLAY_BUTTON);
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
        sb.draw(playButton, (RoboRally.WIDTH / 2) - (playButton.getRegionWidth()/2), (playButton.getRegionHeight()));
        sb.end();
    }

    @Override
    public void dispose() {
        spriteSheet.dispose();
        //background.dispose();
        //playButton.dispose();
    }
}

