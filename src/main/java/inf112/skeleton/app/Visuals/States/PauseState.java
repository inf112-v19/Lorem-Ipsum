package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Visuals.RoboRally;


public class PauseState extends State{
    GameStateManager gsm;
    //Stage stage;

    //boolean

    private boolean mainMenu;
    private boolean resume;
    private boolean exit;
    private boolean start;

    //image
    private Image resumeButton;
    private Image mainMenuButton;
    private Image exitButton;

    private String buttonName;


    public PauseState(GameStateManager gsm) {
        super(gsm);
        //this.assetHandler = new AssetHandler();
        this.stage = new Stage(new ScreenViewport());
        this.stage.getBatch().setProjectionMatrix(camera.combined);

        //boolean
        this.mainMenu = false;
        this.resume = false;
        this.exit = false;
        this.start = false;

        this.resumeButton = new Image(new TextureRegionDrawable(new Texture("StateImages/resume.png")));
        this.mainMenuButton = new Image(new TextureRegionDrawable(new Texture("StateImages/mainMenu.png")));
        this.exitButton = new Image(new TextureRegionDrawable(new Texture("StateImages/exit.png")));
        setResume();
        setMainMenu();
        setExit();
    }

    private void setResume() {
        this.resumeButton.setSize(191, 49);
        this.resumeButton.setPosition((RoboRally.WIDTH/2)-(191/2), RoboRally.HEIGHT-(49*3));
        this.stage.addActor(this.resumeButton);
        clickable(this.resumeButton, "resume");
    }

    private void setMainMenu() {
        this.mainMenuButton.setSize(191, 49);
        this.mainMenuButton.setPosition((RoboRally.WIDTH/2)-(191/2), RoboRally.HEIGHT-(49*5));
        this.stage.addActor(this.mainMenuButton);
        clickable(this.mainMenuButton, "mainMenu");
    }

    private void setExit() {
        this.exitButton.setSize(191, 49);
        this.exitButton.setPosition((RoboRally.WIDTH/2)-(191/2), RoboRally.HEIGHT-(49*7));
        this.stage.addActor(this.exitButton);
        clickable(this.exitButton, "exit");
    }

    private void clickable(Image buttonType, final String buttonName) {
        buttonType.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (buttonName.equals("resume")) {
                    resume = true;
                } else if (buttonName.equals("mainMenu")) {
                    mainMenu = true;
                } else if (buttonName.equals("exit")) {
                    exit = true;
                }
                start = true;
                return true;
            }
        });
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    protected void handleInput() {
        if (this.start) {
            if (this.resume) {
                System.out.println("Resume Game");
                this.gsm.pop();
            } else if (this.mainMenu) {
                System.out.println("New Game!");
                this.gsm.pop();
                this.gsm.set(new MenuState(this.gsm));
                dispose();
            } else if (this.exit) {
                System.out.println("Exit Game!");
                Gdx.app.exit();
            }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize() {
        super.resize();
        this.stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

}
