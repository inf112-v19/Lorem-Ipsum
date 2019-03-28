package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Visuals.SpriteSheet;

public class GameOverState extends State {
    private SpriteSheet spriteSheet;
    private TextureRegion background;

    private Stage stage;

    //text
    Label.LabelStyle font;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        this.spriteSheet = new SpriteSheet();
        this.stage = new Stage(new ScreenViewport());
        this.stage.getBatch().setProjectionMatrix(camera.combined);

        font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        gameOverText();
    }

    private void gameOverText() {
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", font);
        Label playAgainLabel = new Label("Click to Play Again", font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);

        System.out.println("Game Over!");

        this.stage.addActor(table);
    }

    /*private void setStartButton() {
        this.startButton.setSize(buttonWidth, buttonHeight);
        this.startButton.setPosition((RoboRally.WIDTH / 2) - (this.buttonWidth / 2), RoboRally.HEIGHT - (this.buttonHeight * 2));
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
    */
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            System.out.println("New game!");
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
        /*
        this.stage.act();
        this.stage.getBatch().begin();
        this.stage.getBatch().draw(this.background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        this.stage.getBatch().end();
        this.stage.draw();
        */

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.spriteSheet.dispose();
        this.stage.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        //this.stage.getBatch().setProjectionMatrix(camera.combined);
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }
}
