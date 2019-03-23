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

public class ChooseBoardState1 extends State {
    private SpriteSheet spriteSheet;
    private TextureRegion background;

    private Stage stage;

    private Image boardtype;
    private String boardName;

    private boolean start;

    public ChooseBoardState1(GameStateManager gsm, Board board) {
        super(gsm, board);

        this.spriteSheet = new SpriteSheet();
        this.stage = new Stage(new ScreenViewport());

        this.stage.getBatch().setProjectionMatrix(camera.combined);
        this.background = this.spriteSheet.getTexture(SpriteType.CHOOSE_BACKGROUND);

        this.start = false;
        setBoardTypes();
        clickable("Boards/BigBoard.txt");
    }

    /**
     * set "board types" (we have currently only one)
     */
    private void setBoardTypes() {
        this.boardtype = new Image(new TextureRegionDrawable(new Texture("StateImages/board.png")));
        this.boardtype.setSize(1160/6, 928/6);
        this.boardtype.setPosition((RoboRally.WIDTH/2) - ((1160/6)/2), RoboRally.HEIGHT/2 - ((928/6)/2));
        this.stage.addActor(this.boardtype);
    }

    private void clickable(String boardName) {
        this.boardtype.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Board " + getBoardName() + " was chosen!");
                start = true;
                return true;
            }
        });
        Gdx.input.setInputProcessor(this.stage);
    }

    public String getBoardName() {
        return this.boardName;
    }

    @Override
    public void handleInput() {
        if (this.start) {
            //System.out.println(getBoardName());
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
        this.stage.getBatch().draw(this.background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
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
