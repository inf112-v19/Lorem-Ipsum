package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardHandGUI {
    private CardManager cardManager;
    private SpriteBatch batch;
    private Stage stage;

    private AssetHandler assetHandler;
    private Texture texture;

    private BitmapFont font;
    private String playerTurn;

    private BitmapFont[] cardPriorities;
    private int[] tempPriorities;

    private ImageButton[] displayedCardsArr;
    private Card[] tempCardSeq;
    private int tempCardPtr;
    private int cardXPos;
    private int labelXPos;
    private Player currentPlayer;
    private HashMap<Integer, ImageButton> buttonByXPos;
    private HashMap<Integer, Card> cardByXPos;

    private ImageButton clear;
    private ImageButton submit;
    private Image infoBar;
    private Image[] numberLabels;
    private List<Image> lockList;

    public CardHandGUI(CardManager cardManager, SpriteBatch batch, Stage stage, AssetHandler assetHandler) {
        this.cardManager = cardManager;
        this.batch = batch;
        this.stage = stage;

        this.assetHandler = assetHandler;
        font = new BitmapFont();
        buttonByXPos = new HashMap<>();
        cardByXPos = new HashMap<>();
        displayedCardsArr = new ImageButton[9];
        numberLabels = new Image[5];

        clear = new ImageButton(new TextureRegionDrawable(assetHandler.getTexture(SpriteType.CARD_CLEAR)));
        submit = new ImageButton(new TextureRegionDrawable(assetHandler.getTexture(SpriteType.CARD_SUBMIT)));
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
        if (lockList != null) {
            clearLockList();
        }
        lockList = new ArrayList<>();
        clearOldCards();
        clearLabels();
        tempCardSeq = new Card[5];
        cardXPos = 0;
        labelXPos = 0;
        tempCardPtr = 0;
        final List<Card> cards = c;
        tempPriorities = new int[cards.size()];
        cardPriorities = new BitmapFont[cards.size()];

        for (int i = 0; i < cardPriorities.length; i++) {
            cardPriorities[i] = new BitmapFont();
        }

        for (int i = 0; i < cards.size(); i++) {
            displayedCardsArr[i] = new ImageButton(new TextureRegionDrawable(assetHandler.getTexture(cards.get(i))));
            displayedCardsArr[i].setSize(97, 135);
            displayedCardsArr[i].setPosition(cardXPos, 0);
            buttonByXPos.put(cardXPos, displayedCardsArr[i]);
            cardByXPos.put(cardXPos, cards.get(i));

            if (cardManager.isLocked(cards.get(i))) {
                drawLockImage(cardXPos);
                tempCardSeq[i] = cards.get(i);
            }

            cardXPos += 97;
            stage.addActor(displayedCardsArr[i]);
            labelXPos = 0;
        }

        for (int i = 0; i < cards.size(); i++) {
            final int finalI = i;
            displayedCardsArr[i].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (tempCardPtr < 5 && !cardManager.isLocked(cards.get(finalI))) {
                        if (!cardSeqContains(cards.get(finalI), tempCardSeq)) {
                            tempCardSeq[tempCardPtr] = cards.get(finalI);
                            addLabel(tempCardPtr);
                            swapCardPlacement(displayedCardsArr[finalI], getCardImageByX(getDrawPos(tempCardPtr)));
                            tempCardPtr++;
                        }
                    }
                    if (cardManager.isLocked(cards.get(finalI))) {
                        System.out.println("CARD IS LOCKED IN HAND");
                    }
                    System.out.println("Card priority: " + cards.get(finalI).getPriority() + ", card type: " + cards.get(finalI).getCardType());
                    return true;
                }
            });
        }

        infoBar = new Image(new TextureRegionDrawable(assetHandler.getTexture(SpriteType.CARD_BAR)));
        infoBar.setSize(485, 30);
        infoBar.setPosition(0, 135);
        infoBar.addAction(Actions.sequence(Actions.fadeOut(0.15f), Actions.fadeIn(0.15f)));
        stage.addActor(infoBar);
        renderPriorities();
        render();
    }

    private void clearLabels() {
        for (Image label : numberLabels) {
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

    private ImageButton getCardImageByX(int xPos) {
        ImageButton b = buttonByXPos.get(xPos);
        return b;
    }

    private void swapCardPlacement(ImageButton a, ImageButton b) {
        int drawYPos = 0;

        int aXPos = (int) a.getX();
        int bXPos = (int) b.getX();

        a.setPosition(bXPos, drawYPos);
        b.setPosition(aXPos, drawYPos);

        buttonByXPos.put(bXPos, a);
        buttonByXPos.put(aXPos, b);

        Card first = cardByXPos.get(aXPos);
        Card second = cardByXPos.get(bXPos);
        cardByXPos.put(aXPos, second);
        cardByXPos.put(bXPos, first);
    }

    /**
     * adds label over selected card to indicate its place in the ready sequence
     */
    private void addLabel(int cardPtr) {
        infoBar.remove();

        String filename = "button" + (cardPtr + 1);
        texture = assetHandler.getTexture("CardImages/" + filename + ".png");
        TextureRegion numberTexture = new TextureRegion(texture);
        numberLabels[cardPtr] = new Image(numberTexture);
        numberLabels[cardPtr].setSize(97, 30);
        numberLabels[cardPtr].setPosition(labelXPos, 135);
        stage.addActor(numberLabels[cardPtr]);
        labelXPos += 97;
    }

    private void drawLockImage(int xPos) {
        texture = assetHandler.getTexture("lock.png");
        TextureRegion lockTex = new TextureRegion(texture);
        lockTex.flip(false, true);
        Image lock = new Image(lockTex);
        lock.setSize(97, 50);
        lock.setPosition(xPos, Gdx.graphics.getHeight() - 152);
        lockList.add(lock);
        stage.addActor(lock);
    }

    private void clearLockList() {
        for (Image image : lockList) {
           image.remove();
        }
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
        font.draw(batch, playerTurn, 10, Gdx.graphics.getHeight()-10);
        batch.end();
        renderPriorities();
    }

    private void renderPriorities() {
        batch.begin();
        int xPos = 38;
        for (int i = 0; i < tempPriorities.length; i++) {
            tempPriorities[i] = cardByXPos.get(getDrawPos(i)).getPriority();
        }
        for (int i = 0; i < cardPriorities.length; i++) {
            cardPriorities[i].getData().setScale(0.90f);
            cardPriorities[i].setColor(0.109f, 0.258f, 0.168f, 1);
            cardPriorities[i].draw(batch, "" + tempPriorities[i], xPos, 120);
            xPos += 97;
        }
        batch.end();
    }

    private void createSubmitButton() {
        submit.setSize(95, 32);
        submit.setPosition(873, 50);
        TextureRegion pressed = new TextureRegion(assetHandler.getTexture("submit_press.png"));
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
        clear.setPosition(873, 10);
        TextureRegion pressed = new TextureRegion(assetHandler.getTexture("clear_press.png"));
        clear.getStyle().imageDown = new TextureRegionDrawable(pressed);
        stage.addActor(clear);

        clear.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                infoBar.remove();
                System.out.println("Clear");
                selectCards();
                return true;
            }
        });
    }

    public void dispose() {
        submit.clearListeners();
        clear.clearListeners();
        clearOldCards();
        if (texture != null){
            texture.dispose();
        }
        font.dispose();
        for (BitmapFont fonts : cardPriorities) {
            fonts.dispose();
        }
    }

    public void resize(){
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
