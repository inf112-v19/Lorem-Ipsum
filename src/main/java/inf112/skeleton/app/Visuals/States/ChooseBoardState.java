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
import inf112.skeleton.app.Visuals.AssetHandler;
import inf112.skeleton.app.Visuals.SpriteType;


public class ChooseBoardState extends State {
    private AssetHandler assetHandler;
    private TextureRegion background1;
    private Stage stage;

    //text bar
    private Image textBar;

    private Board board;

    //board types
    private int halfButtonWidth;
    private int bigButtonWidth;
    private Image boardType1;
    private Image boardType2;
    private Image boardType3;
    private String boardName;
    //private Image boardType;
    //private int boardTypes;

    private boolean start;


    public ChooseBoardState(GameStateManager gsm) {
        super(gsm);

        this.assetHandler = new AssetHandler();
        this.stage = new Stage(new ScreenViewport());

        this.stage.getBatch().setProjectionMatrix(camera.combined);
        this.background1 = this.assetHandler.getTexture(SpriteType.CHOOSE_BACKGROUND);

        this.start = false;
        this.halfButtonWidth = 193/2; //193 og ikke 191 fordi det passer bildet bedre
        this.bigButtonWidth = this.halfButtonWidth+193;
        //this.boardTypes = 3;

        this.textBar = new Image(new TextureRegionDrawable(new Texture("StateImages/chooseBoardType.png")));

        this.boardType1 = new Image(new TextureRegionDrawable(new Texture("StateImages/board1.png")));
        this.boardType2 = new Image(new TextureRegionDrawable(new Texture("StateImages/board2.png")));
        this.boardType3 = new Image(new TextureRegionDrawable(new Texture("StateImages/board3.png")));

        setTextBar();
        setBoardTypes();

        /*
        for (int i = 1; i < boardTypes+1; i++) {
            setBoardTypes(i, boardName);
            clickable(i, boardName);
        } */
    }

    /**
     * set the textbar "Choose board type"
     */
    private void setTextBar() {
        this.textBar.setSize(1070/3, 102/3);
        this.textBar.setPosition((RoboRally.WIDTH/2)-((1070/3)/2), RoboRally.HEIGHT-(102));
        this.stage.addActor(this.textBar);
    }

    private void setBoardTypes() {
        //first board
        this.boardType1.setSize(191, 49);
        this.boardType1.setPosition(this.halfButtonWidth, RoboRally.HEIGHT/2);
        this.stage.addActor(this.boardType1);
        clickable(this.boardType1, "Boards/BigBoard.txt");

        //second board
        this.boardType2.setSize(191, 49);
        this.boardType2.setPosition((this.halfButtonWidth + this.bigButtonWidth), RoboRally.HEIGHT/2);
        this.stage.addActor(this.boardType2);
        clickable(this.boardType2, "Boards/ExampleBoard.txt");

        //third board
        this.boardType3.setSize(191, 49);
        this.boardType3.setPosition((this.halfButtonWidth + ((this.bigButtonWidth)*2)), RoboRally.HEIGHT/2);
        this.stage.addActor(this.boardType3);
        clickable(this.boardType3, "Boards/BoardBuilderTest2.txt");
    }

    /**
     * set "board types"
     */
    /*private void setBoardTypes(int nBoard, String boardName) {
        String filename = "board" + (nBoard);
        this.boardType = new Image(new TextureRegionDrawable(new Texture("StateImages/" + filename + ".png")));
        this.boardType.setSize(191, 49);
        this.boardType.setPosition(this.modifiedWidth, RoboRally.HEIGHT/2);
        this.stage.addActor(this.boardType);

        this.modifiedWidth += 191 + (191/2);
    }*/

    private void clickable(Image boardType, final String boardName) {
    //private void clickable(Image boardType, final String boardName) {
        boardType.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Board " + boardName + " was chosen!");
                saveBoardName(boardName);
                start = true;
                return true;
            }
        });
        Gdx.input.setInputProcessor(this.stage);
    }

    private String saveBoardName(String boardName) {
        this.boardName = boardName;
        return this.boardName;
    }

    public String getBoardName() {
        return this.boardName;
    }

    @Override
    public void handleInput() {
        if (this.start) {
            this.board = new Board(this.boardName);
            //System.out.println(getBoardName());
            gsm.set(new ChoosePlayerState(this.gsm, this.board));
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
        super.dispose();
        this.assetHandler.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        this.stage.getBatch().setProjectionMatrix(camera.combined);
    }
}