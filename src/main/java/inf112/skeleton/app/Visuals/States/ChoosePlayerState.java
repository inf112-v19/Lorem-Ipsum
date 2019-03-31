package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.AssetHandler;
import inf112.skeleton.app.Visuals.RoboRally;
import inf112.skeleton.app.Visuals.SpriteType;
import java.util.ArrayList;
import java.util.Stack;

public class ChoosePlayerState extends State {
    private Board board;

    private AssetHandler assetHandler;
    private TextureRegion background;
    private Stage stage;

    //booleans
    private boolean start;

    //text bar
    private Image textBar;

    //player
    private int amountPlayers;
    private int spaceOverButtons;
    private int halfButtonWidth;
    private int halfButtonWidth1;
    private int bigButtonWidth;
    private int playerAmount;

    //input
    public ArrayList<String> playerNames;
    public Stack<Player> players;

    public ChoosePlayerState(GameStateManager gsm, Board board) {
        super(gsm);
        this.board = board;

        this.assetHandler = new AssetHandler();
        this.stage = new Stage(new ScreenViewport());
        this.stage.getBatch().setProjectionMatrix(camera.combined);

        this.background = new TextureRegion(new Texture("StateImages/secondBackground.png"));
        this.background = this.assetHandler.getTexture(SpriteType.CHOOSE_BACKGROUND);

        this.start = false;

        //text bar
        this.textBar = new Image(new TextureRegionDrawable(new Texture("StateImages/choosePlayerAmount.png")));

        //player buttons
        this.amountPlayers = 4;
        this.spaceOverButtons = 49*2;
        this.halfButtonWidth = 193/2;
        this.halfButtonWidth1 = 193/2;
        this.bigButtonWidth = this.halfButtonWidth+193;

        setTextbar();
        setSixPlayers();
        //setAmountPlayers(amountPlayers);

        //players
        players = new Stack<>();
        playerNames = new ArrayList<>();
        playerNames.add("RANDOM_NAME_1");
        playerNames.add("RANDOM_NAME_2");
        playerNames.add("RANDOM_NAME_3");
        playerNames.add("RANDOM_NAME_4");
        playerNames.add("RANDOM_NAME_5");
        playerNames.add("RANDOM_NAME_6");
    }

    /**
     * set the textbar "Choose player amount"
     */
    private void setTextbar() {
        this.textBar.setSize(1273/3, 102/3);
        this.textBar.setPosition((RoboRally.WIDTH/2)-((1273/3)/2), RoboRally.HEIGHT-(102));
        this.stage.addActor(this.textBar);
    }

    /**
     * set up six players that you can choose
     */
    private void setSixPlayers() {
        int nPlayers = 6;
        Image nplayers;
        for (int i = 1; i < nPlayers+1; i++) {
            String filename = "no" + i;
            nplayers = new Image(new TextureRegionDrawable(new Texture("StateImages/" + filename + ".png")));
            nplayers.setSize(191, 49);
            this.stage.addActor(nplayers);
            if (i >= 1 && i <= 3) {
                nplayers.setPosition(this.halfButtonWidth, (RoboRally.HEIGHT)/2);
                this.halfButtonWidth += bigButtonWidth;
            }
            else {
                nplayers.setPosition(this.halfButtonWidth1, (RoboRally.HEIGHT/3));
                this.halfButtonWidth1 += bigButtonWidth;
            }
            this.playerAmount = i;
            clickable(nplayers, this.playerAmount);
        }
    }

    /**
     * adds the amount of players that can be chosen (max four) (is not currently being used)
     */
    private void setAmountPlayers(int amountPlayers) {
        for (int i = 1; i < amountPlayers+1; i++) {
            String filename = "no" + i;
            Image nplayers = new Image(new TextureRegionDrawable(new Texture("StateImages/" + filename + ".png")));
            nplayers.setSize(191, 49);
            nplayers.setPosition(((RoboRally.WIDTH / 2) - this.halfButtonWidth-6), RoboRally.HEIGHT - (spaceOverButtons*2));
            this.stage.addActor(nplayers);

            this.spaceOverButtons += 49;
            this.playerAmount = i;
            clickable(nplayers, this.playerAmount);
        }
    }

    private void clickable(Image button, final int playerNumber) {
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (playerNumber == 1) {
                    System.out.println(playerNumber + " " + "(" + getPlayerNameAt(0) + ")" + " player was chosen!");
                    savePlayerAmount(playerNumber);
                } else {
                    System.out.print(playerNumber + " (" + getPlayerNameAt(0));
                    for (int i = 1; i < playerNumber; i++) {
                        System.out.print( ", "+ getPlayerNameAt(i));
                    }
                    System.out.println(") players was chosen!");
                    savePlayerAmount(playerNumber);
                }
                start = true;
                return true;
            }
        });
        Gdx.input.setInputProcessor(this.stage);
    }

    /**
     * helper-method to save the amount of players that is being chosen
     * @param playerAmount
     * @return the amount of players
     */
    private int savePlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
        return this.playerAmount;
    }

    /**
     * @return how many players is chosen
     */
    public int getPlayerAmount() {
        return this.playerAmount;
    }

    /**
     * creates players according to how many players was chosen to play
     * @return
     */
    public Queue<Player> createPlayers() {
        Queue<Player> players = new Queue<>();
        for (int i = 0; i < this.playerAmount; i++) {
            Player player = new Player(i , getPlayerNameAt(i), Direction.EAST);
            players.addLast(player);
        }
        return players;
    }

    /**
     * gets player name at index
     * @param playerIndex
     * @return
     */
    public String getPlayerNameAt(int playerIndex) {
        return playerNames.get(playerIndex);
    }

    @Override
    public void handleInput() {
        if (this.start) {
            //gsm.set(new CardState(gsm, board, cardManager));
            //gsm.set(new SpawnPointState(gsm, board, createPlayers()));
            gsm.set(new PlayerNameState(gsm, board, playerAmount));
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
        this.stage.act();
        this.stage.getBatch().begin();
        this.stage.getBatch().draw(this.background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        this.stage.getBatch().end();
        this.stage.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            System.out.println("PAUSE!");
            this.gsm.push(new PauseState(this.gsm));
        }
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