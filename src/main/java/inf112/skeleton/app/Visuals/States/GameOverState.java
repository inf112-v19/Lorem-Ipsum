package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.RoboRally;
import inf112.skeleton.app.Visuals.SpriteSheet;
import inf112.skeleton.app.Visuals.SpriteType;

public class GameOverState extends State {
    private SpriteSheet spriteSheet;

    private Stage stage;

    //image Game Over
    private Image gameOverImage;

    //text
    Table table;
    Label.LabelStyle font;
    Label playAgainLabel;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        this.spriteSheet = new SpriteSheet();
        this.stage = new Stage(new ScreenViewport());
        this.stage.getBatch().setProjectionMatrix(camera.combined);

        //image
        this.gameOverImage = new Image(new TextureRegionDrawable(new Texture("StateImages/gameOverSmall.png")));

        //label
        this.table = new Table();
        this.font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        this.playAgainLabel = new Label("Click To Play Again", this.font);

        gameOverImage();
        playAgainLabel();
    }

    private void gameOverImage() {
        this.gameOverImage.setSize(529, 102);
        this.gameOverImage.setPosition((RoboRally.WIDTH / 2)-(520/2), RoboRally.HEIGHT-(102*2)-51);
        this.stage.addActor(this.gameOverImage);
        System.out.println("Game Over!");
    }

    private void playAgainLabel() {
        this.table.center();
        this.table.setFillParent(true);
        this.table.add(this.playAgainLabel).expandX().padTop(10f);
        this.stage.addActor(this.table);

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
        /*TextureRegion textBar = new TextureRegion(new Texture("StateImages/.png"));
        //TextureRegion background = this.spriteSheet.getTexture(SpriteType.MENU_BACKGROUND);
        this.stage.act();
        this.stage.getBatch().begin();
        this.stage.getBatch().draw(textBar, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        this.stage.getBatch().end();
*/
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.spriteSheet.dispose();
        //this.stage.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        this.stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }
}
