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
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.Visuals.RoboRally;
import inf112.skeleton.app.Visuals.SpriteSheet;
import inf112.skeleton.app.Visuals.SpriteType;
import java.util.ArrayList;
import java.util.Scanner;

public class ChoosePlayerState extends State {
    private SpriteSheet spriteSheet;
    private TextureRegion background;
    private Stage stage;
    private CardManager cardManager;

    private boolean start;
    private boolean bar2;

    //text bar
    private Image textBar;
    private Image textBar2;

    //player
    private int amountPlayers;
    private int spaceOverButtons;
    private int halfButtonWidth;
    private int halfButtonWidth1;
    private int bigButtonWidth;
    private int playerAmount;

    //input
    private ArrayList<String> playerNames;

    public ChoosePlayerState(GameStateManager gsm, Board board, CardManager cardManager) {
        super(gsm, board);

        this.spriteSheet = new SpriteSheet();
        this.stage = new Stage(new ScreenViewport());

        this.stage.getBatch().setProjectionMatrix(camera.combined);
        this.background = this.spriteSheet.getTexture(SpriteType.CHOOSE_BACKGROUND);

        this.cardManager = cardManager;

        this.start = false;
        this.bar2 = false;

        //text bar
        this.textBar = new Image(new TextureRegionDrawable(new Texture("StateImages/choosePlayerAmount.png")));
        this.textBar2 = new Image(new TextureRegionDrawable(new Texture("StateImages/namePlayers.png")));


        //player
        this.amountPlayers = 4;
        this.spaceOverButtons = 49*2;
        this.halfButtonWidth = 193/2;
        this.halfButtonWidth1 = 193/2;
        this.bigButtonWidth = this.halfButtonWidth+193;

        setTextbar();
        setSixPlayers();
        //setAmountPlayers(amountPlayers);

        //input
        playerNames = new ArrayList<String>();


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
     * set the textbar2 "Name players"
     */
    private void setTextbar2() {
        this.textBar2.setSize(821/3, 121/3);
        this.textBar2.setPosition((RoboRally.WIDTH/2)-((821/3)/2), 102-(102/3));
        this.stage.addActor(this.textBar2);
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
     * adds the amount of players that can be chosen (max 4)
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

    private void clickable(Image button, final int playerName) {
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (playerName == 1) {
                    System.out.println("1 player was chosen!");
                    savePlayerAmount(playerName);
                } else {
                    System.out.println(playerName + " players was chosen!");
                    savePlayerAmount(playerName);
                }
                start = true;
                bar2 = true;
                return true;
            }
        });

        Gdx.input.setInputProcessor(this.stage);
    }

    private int savePlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
        return this.playerAmount;
    }

    public int getPlayerAmount() {
        return this.playerAmount;
    }

    /**
     * input name and it displays in output window
     */
    private void inputName() {
        String name;
        try (Scanner sc = new Scanner(System.in)){
            if (getPlayerAmount() == 1) {
                System.out.print("Enter your players name:\nplayer 1: ");
                name = sc.next();
                this.playerNames.add(name);
                System.out.println("Player name saved!");
            } else if (getPlayerAmount() >= 2 && getPlayerAmount() <= 6) {
                System.out.println("Enter your players' name:");
                for (int i = 1; i < getPlayerAmount() + 1; i++) {
                    System.out.print("player " + i + ": ");
                    name = sc.next();
                    this.playerNames.add(name);
                }
                System.out.println("Player names saved!");
            }
        }
    }

    public ArrayList<String> getPlayerNames() {
        return this.playerNames;
    }

    @Override
    public void handleInput() {
        if (this.start) {
            do {
                inputName();
            } while (this.playerNames.size() != this.playerAmount);
            gsm.set(new CardState(gsm, board, cardManager));
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