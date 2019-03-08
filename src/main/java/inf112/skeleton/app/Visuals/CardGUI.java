package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Exceptions.PlayerEmptyCardHandException;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Interfaces.ICardDeck;
import org.lwjgl.Sys;

import java.util.HashMap;
import java.util.List;

public class CardGUI {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private SpriteSheet spriteSheet;
    private Player[] players;

    private ImageButton clear;
    private ImageButton submit;

    private Stage stage;

    private int cardPtr;
    private HashMap<Integer, ImageButton> buttonByXPos;
    private Image infoBar;
    private int labelXPos;
    private int cardXPos;

    private BitmapFont font;
    private String playerTurn;

    private Card[] cardSeq;
    private int currentPlayer;

    public CardGUI(OrthographicCamera camera, SpriteBatch batch, Player[] players) {
        this.camera = camera;
        this.batch = batch;
        this.players = players;

        stage = new Stage(new ScreenViewport());
        spriteSheet = new SpriteSheet();
        this.camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //temp

        cardPtr = 0;
        currentPlayer = 0;

        font = new BitmapFont(true);
        buttonByXPos = new HashMap<>();

        clear = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(SpriteType.CARD_CLEAR)));
        submit = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(SpriteType.CARD_SUBMIT)));

        try {
            selectCards();
        } catch (PlayerEmptyCardHandException e) {
            e.printStackTrace();
        }
    }

    private void selectCards() throws PlayerEmptyCardHandException {
        playerTurn = players[currentPlayer].getPlayerID() + "'s turn";
        List<Card> playerCards = players[currentPlayer].getCardHand();

        if (playerCards == null) {
            throw new PlayerEmptyCardHandException("Player does not have any cards in hand");
        }
        draw(playerCards);
    }

    private void setPlayerDone() {
        players[currentPlayer].setCardSequence(cardSeq);
        players[currentPlayer].setReady();

        currentPlayer++;
        if (currentPlayer < players.length) {
            try {
                selectCards();
            } catch (PlayerEmptyCardHandException e) {
                e.printStackTrace();
            }
        }
    }

    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.begin();
        font.draw(batch, playerTurn, 10, 10);
        batch.end();
    }

    /**
     * Draws list of cards on screen and add listeners on each card
     *
     * @param c list of cards
     */
    private void draw(List<Card> c) {

        final List<Card> cards = c;
        final ImageButton[] buttonArr = new ImageButton[cards.size()];
        cardSeq = new Card[5];
        cardXPos = 0;

        for (int i = 0; i < buttonArr.length; i++) {
            buttonArr[i] = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(cards.get(i))));
            buttonArr[i].setSize(97, 135);
            buttonArr[i].setPosition(cardXPos, 0);
            buttonByXPos.put(cardXPos, buttonArr[i]);
            cardXPos += 97;
            stage.addActor(buttonArr[i]);
            labelXPos = 0;
        }

        for (int i = 0; i < buttonArr.length; i++) {
            final int finalI = i;
            buttonArr[i].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (cardPtr < 5) {
                        if (!cardSeqContains(cards.get(finalI), cardSeq)) {
                            cardSeq[cardPtr] = cards.get(finalI);
                            addLabel(cardPtr);
                            swapCardPlacement(buttonArr[finalI], getCardByX(getDrawPos(cardPtr)));
                            cardPtr++;
                        }
                    }
                    System.out.println("card priority: " + cards.get(finalI).getPriority());
                    return true;
                }
            });
        }

        cardXPos += 10;
        infoBar = new Image(new TextureRegionDrawable(spriteSheet.getTexture(SpriteType.CARD_BAR)));

        infoBar.setSize(485, 30);
        infoBar.setPosition(0, 135);
        infoBar.addAction(Actions.sequence(Actions.fadeOut(0.15f), Actions.fadeIn(0.15f)));
        stage.addActor(infoBar);

        createClearButton();
        createSubmitButton();

        Gdx.input.setInputProcessor(stage);
    }

    private boolean cardSeqContains(Card card, Card[] cardSeq) {
        for (int i = 0; i < cardPtr; i++) {
            if (cardSeq[i] == card) {
                return true;
            }
        }
        return false;
    }

    /** adds label over selected card to indicate its place in the ready sequence*/
    private void addLabel(int cardPtr) {
        if (cardPtr == 0) {
            infoBar.remove();
        }

        String filename = "button" + (cardPtr + 1);
        Image label = new Image(new TextureRegionDrawable(new Texture("CardImages/" + filename + ".png")));
        label.setSize(97, 30);
        label.setPosition(labelXPos, 135);
        stage.addActor(label);
        labelXPos += 97;
    }

    private ImageButton getCardByX(int xPos) {
        ImageButton b = buttonByXPos.get(xPos);
        return b;
    }

    /** returns x coordinate for cards draw position given its new place in the sequence */
    private int getDrawPos(int cardPtr) {
        return cardPtr * 97;
    }

    private void swapCardPlacement(ImageButton a, ImageButton b) {
        int aXPos = (int) a.getX();
        int bXPos = (int) b.getX();

        a.setPosition(bXPos, 0);
        b.setPosition(aXPos, 0);

        buttonByXPos.put(bXPos, a);
        buttonByXPos.put(aXPos, b);
    }

    public void dispose() {
        submit.clearListeners();
        clear.clearListeners();

        stage.clear();
        stage.dispose();
        font.dispose();
    }

    private void createSubmitButton() {
        submit.setSize(80, 30);
        submit.setPosition(cardXPos, 70);
        stage.addActor(submit);

        submit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (cardPtr < 4) {
                    System.out.println("not enough cards");
                } else {
                    System.out.print("submit: ");
                    for (int i = 0; i < cardPtr; i++) {
                        System.out.print(cardSeq[i].toString() + ", ");
                    }

                    cardPtr = 0;
                    stage.clear();
                    setPlayerDone();
                }
                return true;
            }
        });
    }

    private void createClearButton() {
        clear.setSize(80, 30);
        clear.setPosition(cardXPos, 20);
        stage.addActor(clear);

        clear.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                cardPtr = 0;
                stage.clear();
                draw(players[currentPlayer].getCardHand());
                System.out.println("clear");
                return true;
            }
        });
    }

}
