package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.Visuals.RoboRally;

public class MenuState extends State {
    private TextureRegion background;
    private Image startButton;

    private int buttonWidth;
    private int buttonHeight;

    private boolean start;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        this.buttonWidth = 192+64; //original size + 1/3 of the size
        this.buttonHeight = 49+16; //original size + 1/3 of the size
        this.start = false;

        this.background = super.assetHandler.getTextureRegion("StateImages/startBackground.jpg");
        this.startButton = new Image(assetHandler.getTextureRegion("StateImages/start.png"));

        setStartButton();
    }

    private void setStartButton() {
        this.startButton.setSize(buttonWidth, buttonHeight);
        this.startButton.setPosition((RoboRally.WIDTH / 2)-(this.buttonWidth/2), (RoboRally.HEIGHT/2)-(this.buttonHeight/2));
        stage.addActor(this.startButton);

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
    }

    @Override
    public void handleInput() {
        if (this.start) {
            gsm.set(new ChooseBoardState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render() {
        this.stage.getBatch().begin();
        this.stage.getBatch().draw(this.background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        this.stage.getBatch().end();
		super.render();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize() {
        super.resize();
    }
}
