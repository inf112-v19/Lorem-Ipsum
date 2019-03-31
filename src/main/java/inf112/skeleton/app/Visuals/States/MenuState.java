package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Visuals.RoboRally;
import inf112.skeleton.app.Visuals.AssetHandler;
import inf112.skeleton.app.Visuals.SpriteType;

public class MenuState extends State {
    private AssetHandler assetHandler;
    private TextureRegion background;
    private Image startButton;

    private Stage stage;
    private final int buttonWidth;
    private final int buttonHeight;

    private boolean start;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        this.assetHandler = new AssetHandler();
        this.stage = new Stage(new ScreenViewport());
        this.stage.getBatch().setProjectionMatrix(camera.combined);

        this.buttonWidth = 192+64; //original size + 1/3 of the size
        this.buttonHeight = 49+16; //original size + 1/3 of the size
        this.start = false;

        this.background = this.assetHandler.getTexture(SpriteType.MENU_BACKGROUND);
        this.startButton = new Image(new TextureRegionDrawable(assetHandler.getTexture("StateImages/start.png")));

        setStartButton();
    }

    private void setStartButton() {
        this.startButton.setSize(buttonWidth, buttonHeight);
        this.startButton.setPosition((RoboRally.WIDTH / 2)-(this.buttonWidth/2), RoboRally.HEIGHT-(this.buttonHeight*2));
        this.stage.addActor(this.startButton);

        clickable(this.startButton);
    }

    private void clickable(Image button) {
        button.addListener(new InputListener() {
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
            gsm.set(new ChooseBoardState(gsm));
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
        this.assetHandler.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        //this.stage.getBatch().setProjectionMatrix(camera.combined);
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }
}
