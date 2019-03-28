package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Visuals.SpriteSheet;

public class GameOverState extends State {
    private SpriteSheet spriteSheet;

    private Stage stage;

    //text
    Table table;
    Label.LabelStyle font;
    Label gameOverLabel;
    Label playAgainLabel;


    public GameOverState(GameStateManager gsm) {
        super(gsm);
        this.spriteSheet = new SpriteSheet();
        this.stage = new Stage(new ScreenViewport());
        this.stage.getBatch().setProjectionMatrix(camera.combined);

        this.table = new Table();
        this.font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        this.gameOverLabel = new Label("GAME OVER", font);
        this.playAgainLabel = new Label("Click To Play Again", font);

        gameOverText();
    }

    private void gameOverText() {
        table.center();
        table.setFillParent(true);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);

        System.out.println("Game Over!");
        this.stage.addActor(table);
    }

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
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }
}
