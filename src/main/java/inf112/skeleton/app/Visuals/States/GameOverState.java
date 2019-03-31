package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Visuals.RoboRally;

public class GameOverState extends State {

    //image Game Over
    private Image gameOverImage;

    //text
    Table table;
    Label.LabelStyle font;
    Label playAgainLabel;

    public GameOverState(GameStateManager gsm) {
        super(gsm);

        //image
        this.gameOverImage = new Image(assetHandler.getTextureRegion("StateImages/gameOver.png"));

        //label
        this.table = new Table();
        this.font = new Label.LabelStyle(new BitmapFont(true), Color.WHITE);
        this.playAgainLabel = new Label("Click To Play Again", this.font);

        gameOverImage();
        playAgainLabel();
    }

    private void gameOverImage() {
        this.table.add(gameOverImage);
        this.table.row();
        System.out.println("Game Over!");
    }

    private void playAgainLabel() {
        this.table.center();
        this.table.setFillParent(true);
        this.table.add(this.playAgainLabel).expandX().padTop(10f);
        super.stage.addActor(this.table);

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            System.out.println("New game!");
            this.gsm.set(new MenuState(this.gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render() {
        super.render();
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //this.stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resize() {
        super.resize();
    }
}
