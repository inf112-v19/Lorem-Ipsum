package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Player;

import java.util.HashMap;
import java.util.List;

public class NewCardHandGUI {
    private CardManager cardManager;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage stage;

    private SpriteSheet spriteSheet;
    //private ImageButton clear;
    //private ImageButton submit;

    private BitmapFont font;
    private String playerTurn;

    private ImageButton[] displayedCardsArr;
    private Card[] tempCardSeq;
    private int tempCardPtr;
    private int cardXPos;
    private int labelXPos;
    private Player currentPlayer;
    private HashMap<Integer, ImageButton> buttonByXPos;


    public NewCardHandGUI(CardManager cardManager, OrthographicCamera camera, SpriteBatch batch, Stage stage) {
        this.cardManager = cardManager;
        this.camera = camera;
        this.batch = batch;
        this.stage = stage;

        spriteSheet = new SpriteSheet();
        font = new BitmapFont(true);
        buttonByXPos = new HashMap<>();
        displayedCardsArr = new ImageButton[9];

        cardManager.newRound();
        selectCards();

    }

    private void selectCards() {
        if (cardManager.hasNotReadyPlayers()) {
            currentPlayer = cardManager.getPlayer();
            playerTurn = currentPlayer.getPlayerID() + "'s turn";
            List<Card> currentCards = currentPlayer.getCardHand();
            draw(currentCards);
        }
    }

    private void draw(List<Card> c) {
        //clearOldCards();
        tempCardSeq = new Card[5];
        cardXPos = 0;
        final List<Card> cards = c;

        for (int i = 0; i < displayedCardsArr.length; i++) {
            displayedCardsArr[i] = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(cards.get(i))));
            displayedCardsArr[i].setSize(97, 135);
            displayedCardsArr[i].setPosition(cardXPos, 0);
            buttonByXPos.put(cardXPos, displayedCardsArr[i]);
            cardXPos += 97;
            stage.addActor(displayedCardsArr[i]);
            labelXPos = 0;
        }

        for (int i = 0; i < displayedCardsArr.length; i++) {
            final int finalI = i;
            displayedCardsArr[i].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (tempCardPtr < 5) {
                        if (!cardSeqContains(cards.get(finalI), tempCardSeq)) {
                            tempCardSeq[tempCardPtr] = cards.get(finalI);
                            addLabel(tempCardPtr);
                            swapCardPlacement(displayedCardsArr[finalI], getCardByX(getDrawPos(tempCardPtr)));
                            tempCardPtr++;
                        }
                    }
                    System.out.println("card priority: " + cards.get(finalI).getPriority());
                    return true;
                }
            });
        }

    }

    private void clearOldCards() {
        for (ImageButton button : displayedCardsArr) {
            if (button != null) {
                button.clearListeners();
                button.clear();
            }
        }
    }

    /**
     * returns x coordinate for cards draw position given its new place in the sequence
     */
    private int getDrawPos(int cardPtr) {
        return cardPtr * 97;
    }

    private ImageButton getCardByX(int xPos) {
        ImageButton b = buttonByXPos.get(xPos);
        return b;
    }

    private void swapCardPlacement(ImageButton a, ImageButton b) {
        int aXPos = (int) a.getX();
        int bXPos = (int) b.getX();

        a.setPosition(bXPos, 0);
        b.setPosition(aXPos, 0);

        buttonByXPos.put(bXPos, a);
        buttonByXPos.put(aXPos, b);
    }

    /**
     * adds label over selected card to indicate its place in the ready sequence
     */
    private void addLabel(int cardPtr) {
        if (cardPtr == 0) {
            //infoBar.remove();
        }

        String filename = "button" + (cardPtr + 1);
        Image label = new Image(new TextureRegionDrawable(new Texture("CardImages/" + filename + ".png")));
        label.setSize(97, 30);
        label.setPosition(labelXPos, 135);
        stage.addActor(label);
        labelXPos += 97;
    }

    private boolean cardSeqContains(Card card, Card[] cardSeq) {
        for (int i = 0; i < tempCardPtr; i++) {
            if (cardSeq[i] == card) {
                return true;
            }
        }
        return false;
    }

    public void render() {
        batch.begin();
        font.draw(batch, playerTurn, 10, 10);
        batch.end();
    }

}
