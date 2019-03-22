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
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.RoboRally;
import inf112.skeleton.app.Visuals.SpriteSheet;
import inf112.skeleton.app.Visuals.SpriteType;

public class ChooseBoardState extends State {
    private SpriteSheet spriteSheet;
    private TextureRegion background1;

    private Stage stage;

    private int modifiedWidth;
    private Image boardtype;
    private int boardTypes;

    private boolean start;

    public ChooseBoardState(GameStateManager gsm, Board board) {
        super(gsm, board);

        this.spriteSheet = new SpriteSheet();
        this.stage = new Stage(new ScreenViewport());

        this.stage.getBatch().setProjectionMatrix(camera.combined);
        this.background1 = this.spriteSheet.getTexture(SpriteType.TEST_A);

        this.start = false;

        this.modifiedWidth = 191/2;
        this.boardTypes = 3;

        for (int i = 1; i < boardTypes+1; i++) {
            setBoardTypes(i);
            clickable();
        }
    }

    /**
     * set "board types"
     */
    private void setBoardTypes(int nBoard) {
        String filename = "board" + (nBoard);
        this.boardtype = new Image(new TextureRegionDrawable(new Texture("StateImages/" + filename + ".png")));
        this.boardtype.setSize(191, 49);
        this.boardtype.setPosition(this.modifiedWidth, RoboRally.HEIGHT/2);
        this.stage.addActor(this.boardtype);

        this.modifiedWidth += 191 + (191/2);
    }

    private void clickable() {
        this.boardtype.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Board chosen!");
                start = true;
                return true;
            }
        });

        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void handleInput() {
        if (this.start) {
            gsm.set(new ChoosePlayerState(gsm, board));
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
        this.stage.getBatch().draw(this.background1, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
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