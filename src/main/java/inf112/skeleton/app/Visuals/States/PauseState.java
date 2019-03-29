package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.BoardGUI;
import inf112.skeleton.app.Visuals.RoboRally;
import inf112.skeleton.app.Visuals.SpriteSheet;


public class PauseState extends State{
    GameStateManager gsm;
    SpriteSheet spriteSheet;
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


    public PauseState(GameStateManager gsm) {
        super(gsm);
        //this.spriteSheet = new SpriteSheet();
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
        this.resumeButton.setPosition((RoboRally.WIDTH/2)-(191/2), RoboRally.HEIGHT/2);
        this.stage.addActor(this.resumeButton);
        clickable(resumeButton, "resume");
        this.resume = true;
    }

    private void setMainMenu() {
        this.mainMenuButton.setSize(191, 49);
        this.mainMenuButton.setPosition((RoboRally.WIDTH/2)-(191/2), RoboRally.HEIGHT/3);
        this.stage.addActor(this.mainMenuButton);
        clickable(mainMenuButton, "mainMenu");
    }

    private void setExit() {
        this.exitButton.setSize(191, 49);
        this.exitButton.setPosition((RoboRally.WIDTH/2)-(191/2), RoboRally.HEIGHT/4);
        this.stage.addActor(this.exitButton);
        clickable(exitButton, "exit");
    }

    private void clickable(Image boardType, final String boardName) {
        //private void clickable(Image boardType, final String boardName) {
        boardType.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("Board " + boardName + " was chosen!");
                System.out.println("clickeddddd");
                //saveBoardName(boardName);
                if (boardName.equals("resume")) {
                    resume = true;
                } if (boardName.equals("mainMenu")) {
                    mainMenu = true;
                } if (boardName.equals("exit")) {
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
            if (this.mainMenu) {
                System.out.println("New Game!");
                //gsm.set(new MenuState(gsm));
                //dispose();
            }
        /*if (Gdx.input.isKeyPressed(Input.Keys.P) || this.resume) {
            System.out.println("Resume Game");
            //tilbake til spillet
        }*/
            if (Gdx.input.justTouched()) {
                System.out.println("Exit Game!");
                //terminere spillet
                Gdx.app.exit();

            }
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }
/*
    private void test() {
        Table table = new Table();
        Window.WindowStyle style = new Window.WindowStyle(new BitmapFont(), Color.WHITE);
        Window pause = new Window("Paused", style);
        pause.setMoveable(false); //So the user can't move the window
        pause.add(new TextButton("Unpause", skin)); //Add a new text button that unpauses the game.
        pause.pack(); //Important! Correctly scales the window after adding new elements.
        float newWidth = 400, newHeight = 200;
        pause.setBounds((Gdx.graphics.getWidth() - newWidth ) / 2,
                (Gdx.graphics.getHeight() - newHeight ) / 2, newWidth , newHeight ); //Center on screen.
        stage.addActor(pause);
    }*/

    @Override
    public void render() {
        super.render();
        /*TextureRegion textBar = new TextureRegion(new Texture("StateImages/.png"));
        //TextureRegion background = this.spriteSheet.getTexture(SpriteType.MENU_BACKGROUND);
        this.stage.act();
        this.stage.getBatch().begin();
        this.stage.getBatch().draw(textBar, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        this.stage.getBatch().end();
        */
        /*Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        */
        //this.stage.draw();

    }

    @Override
    public void resize() {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
