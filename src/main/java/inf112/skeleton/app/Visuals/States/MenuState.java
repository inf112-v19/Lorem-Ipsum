package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Visuals.RoboRally;
import inf112.skeleton.app.Visuals.SpriteSheet;
import inf112.skeleton.app.Visuals.SpriteType;

public class MenuState extends State {

    private SpriteSheet spriteSheet;
    private TextureRegion background;
    private TextureRegion playButton;

    private Stage stage;
    private ImageButton startButton;

    private boolean start;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        this.spriteSheet = new SpriteSheet();
        this.stage = new Stage(new ScreenViewport());

        this.stage.getBatch().setProjectionMatrix(camera.combined);
        this.background = this.spriteSheet.getTexture(SpriteType.MENU_BACKGROUND);
        this.playButton = this.spriteSheet.getTexture(SpriteType.MENU_PLAY_BUTTON);

        this.start = false;
        clickable();
    }

    private void clickable() {
        this.startButton = new ImageButton(new TextureRegionDrawable(this.playButton));
        this.startButton.setSize(358, 83);;
        this.startButton.setPosition((RoboRally.WIDTH / 2) - (this.playButton.getRegionWidth()/2), RoboRally.HEIGHT-(this.playButton.getRegionHeight()*2));

        this.stage.addActor(this.startButton);
        this.startButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Start game!");
                start = true;
                return true;
            }
        });
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void handleInput() {
        if (this.start) {
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
        this.stage.act();
        this.stage.getBatch().begin();
        this.stage.getBatch().draw(this.background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        this.stage.getBatch().end();
        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.spriteSheet.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        this.stage.getBatch().setProjectionMatrix(camera.combined);
    }
}

