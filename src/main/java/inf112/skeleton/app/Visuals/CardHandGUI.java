package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Player;
import org.lwjgl.Sys;

import java.util.HashMap;
import java.util.List;

public class CardHandGUI {
    private CardManager cardManager;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage stage;

    private SpriteSheet spriteSheet;

    private BitmapFont font;
    private String playerTurn;

    private ImageButton[] displayedCardsArr;
    private Card[] tempCardSeq;
    private int tempCardPtr;
    private int cardXPos;
    private int labelXPos;
    private Player currentPlayer;
    private HashMap<Integer, ImageButton> buttonByXPos;

    private ImageButton clear;
    private ImageButton submit;
    private Image infoBar;
    private Image[] numberlabels;

    public CardHandGUI(CardManager cardManager, OrthographicCamera camera, SpriteBatch batch, Stage stage) {
        this.cardManager = cardManager;
        this.camera = camera;
        this.batch = batch;
        this.stage = stage;

        spriteSheet = new SpriteSheet();
        font = new BitmapFont(true);
        buttonByXPos = new HashMap<>();
        displayedCardsArr = new ImageButton[9];
        numberlabels = new Image[5];

        clear = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(SpriteType.CARD_CLEAR)));
        submit = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(SpriteType.CARD_SUBMIT)));
        createSubmitButton();
        createClearButton();
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
        clearOldCards();
        clearLabels();
        tempCardSeq = new Card[5];
        cardXPos = 0;
        labelXPos = 0;
        tempCardPtr = 0;
        final List<Card> cards = c;

        for (int i = 0; i < displayedCardsArr.length; i++) {
            displayedCardsArr[i] = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(cards.get(i))));
            displayedCardsArr[i].setSize(97, 135);
            displayedCardsArr[i].setPosition(cardXPos, Gdx.graphics.getHeight() - 135);
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
                    System.out.println("Card priority: " + cards.get(finalI).getPriority() + ", card type: " + cards.get(finalI).getCardType());
                    return true;
                }
            });
        }

        infoBar = new Image(new TextureRegionDrawable(spriteSheet.getTexture(SpriteType.CARD_BAR)));
        infoBar.setSize(485, 30);
        infoBar.setPosition(0, Gdx.graphics.getHeight()-165);
        infoBar.addAction(Actions.sequence(Actions.fadeOut(0.15f), Actions.fadeIn(0.15f)));
        stage.addActor(infoBar);
    }

    private void clearLabels() {
        for (Image label : numberlabels) {
            if (label != null) {
                label.remove();
            }
        }
    }

    private void clearOldCards() {
        for (ImageButton button : displayedCardsArr) {
            if (button != null) {
                button.clearListeners();
                button.remove();
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
        int drawYPos = Gdx.graphics.getHeight() - 135;

        int aXPos = (int) a.getX();
        int bXPos = (int) b.getX();

        a.setPosition(bXPos, drawYPos);
        b.setPosition(aXPos, drawYPos);

        buttonByXPos.put(bXPos, a);
        buttonByXPos.put(aXPos, b);
    }

    /**
     * adds label over selected card to indicate its place in the ready sequence
     */
    private void addLabel(int cardPtr) {
        infoBar.remove();

        String filename = "button" + (cardPtr + 1);
        TextureRegion numberTexture = new TextureRegion(new Texture("CardImages/" + filename + ".png"));
        numberTexture.flip(false, true);
        numberlabels[cardPtr] = new Image(numberTexture);
        numberlabels[cardPtr].setSize(97, 30);
        numberlabels[cardPtr].setPosition(labelXPos, Gdx.graphics.getHeight() - 165);
        stage.addActor(numberlabels[cardPtr]);
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
        Gdx.input.setInputProcessor(stage);
        stage.act(Gdx.graphics.getDeltaTime());
        batch.begin();
        font.draw(batch, playerTurn, 10, 10);
        batch.end();
    }

    private void createSubmitButton() {
        submit.setSize(95, 32);
        submit.setPosition(873, Gdx.graphics.getHeight() - 100);
        TextureRegion pressed = new TextureRegion(new Texture("submit_press.png"));
        pressed.flip(false, true);
        submit.getStyle().imageDown = new TextureRegionDrawable(pressed);
        stage.addActor(submit);

        submit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if (cardManager.setCardSeq(currentPlayer, tempCardSeq)) {
                    System.out.print("Cards submitted: ");
                    for (int i = 0; i < tempCardPtr; i++) {
                        System.out.print(tempCardSeq[i].toString() + ", ");
                    }
                    System.out.println();
                    selectCards();
                } else {
                    System.out.println("Select 5 cards!");
                }
                return true;
            }
        });
    }

    private void createClearButton() {
        clear.setSize(76, 32);
        clear.setPosition(873, Gdx.graphics.getHeight()-60);
        TextureRegion pressed = new TextureRegion(new Texture("clear_press.png"));
        pressed.flip(false, true);
        clear.getStyle().imageDown = new TextureRegionDrawable(pressed);
        stage.addActor(clear);

        clear.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selectCards();
                return true;
            }
        });
    }

    public void dispose() {
        submit.clearListeners();
        clear.clearListeners();
        clearOldCards();
    }

}
