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
    private SpriteBatch batch;

    private Stage stage;
    private ImageButton startButton;
    private boolean start;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        this.start = false;

        this.spriteSheet = new SpriteSheet();
        this.batch = new SpriteBatch();
        this.batch.setProjectionMatrix(camera.combined);
        this.background = spriteSheet.getTexture(SpriteType.MENU_BACKGROUND);
        this.playButton = spriteSheet.getTexture(SpriteType.MENU_PLAY_BUTTON);

        this.stage = new Stage(new ScreenViewport());
        clickable();
    }

    private void clickable() {
        startButton = new ImageButton(new TextureRegionDrawable(playButton));
        startButton.setSize(358, 83);;
        startButton.setPosition((RoboRally.WIDTH / 2) - (playButton.getRegionWidth()/2), RoboRally.HEIGHT-(playButton.getRegionHeight()*2));

        stage.addActor(startButton);
        startButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Start game!");
                start = true;
                return true;
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput() {
        if (start) {
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
        /*
        batch.begin();
        batch.draw(background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        batch.draw(playButton, (RoboRally.WIDTH / 2) - (playButton.getRegionWidth()/2), (playButton.getRegionHeight()));
        batch.end();
        */
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void dispose() {
        spriteSheet.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        //batch.setProjectionMatrix(camera.combined); //ikke resizable
        stage.getBatch().setProjectionMatrix(camera.combined);  //reziable
    }
}

