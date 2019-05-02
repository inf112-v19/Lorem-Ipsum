package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Player;

import java.util.HashSet;
import java.util.List;

public class CardHandGUI {
    private Stage stage;
    private CardManager cardManager;
    private AssetHandler assetHandler;

    private Table table;
    private Table buttonTable;
    private Table cancelPowerDownTable;

    private Player currentPlayer;
    private List<Card> currentCards;
    private String playerTurn;

    private Card[] tempCardSeq;
    private HashSet<Card> selectedCards;
    private int cardsToSelect;
    private boolean powerDownPressed;
    private boolean showGreetingText;

    private TextButton clear;
    private TextButton submit;
    private TextButton powerDown;

    public CardHandGUI(CardManager cardManager, Stage stage, AssetHandler assetHandler) {
        this.stage = stage;
        this.cardManager = cardManager;
        this.assetHandler = assetHandler;

        table = new Table();
        table.bottom().left();
        table.setFillParent(true);
        buttonTable = new Table();

        clear = new TextButton("Clear", assetHandler.getSkin());
        submit = new TextButton("Submit", assetHandler.getSkin());
        powerDown = new TextButton("Power Down", assetHandler.getSkin());

        cardManager.newRound();
        createOptionButtons();
        selectCards();
    }

    /**
     * Empties tables used in the GUI
     */
    public void dispose() {
        table.clearChildren();
        buttonTable.clearChildren();
        if (cancelPowerDownTable != null) {
            cancelPowerDownTable.clearChildren();
        }
    }

    /**
     * Checks card manager for players that is not ready
     * and calls appropriate methods
     */
    private void selectCards() {
        if (cardManager.hasNotReadyPlayers()) {
            powerDown.setText("Power Down");
            powerDownPressed = false;
            showGreetingText = true;
            currentPlayer = cardManager.getPlayer();
            playerTurn = currentPlayer.getPlayerName() + "'s turn";
            currentCards = currentPlayer.getCardHand();


            if (currentPlayer.isControlledByAI()) {
                cardManager.setCardSeq(currentPlayer, currentPlayer.chooseAICards(cardManager));
            } else {
                if (currentPlayer.getPowerDown() == 3) {
                    table.clearChildren();
                    drawPowerDownOptions();
                } else {
                    resetPlayerValues();
                    draw(currentCards);
                }
            }
        } else {
            enterWait(); //LAN feature
        }
    }

    private void enterWait() {
        table.clearChildren();
        buttonTable.clearChildren();
        Label waitLabel = new Label("Waiting for other players", assetHandler.getSkin());
        table.add(waitLabel).padBottom(40).center();
        stage.addActor(table);
    }

    /**
     * Resets variables uses to keep track of selected cards
     */
    private void resetPlayerValues() {
        tempCardSeq = new Card[5];
        selectedCards = new HashSet<>();
        cardsToSelect = 5;

        for (int i = 0; i < currentCards.size(); i++) {
            if (cardManager.isLocked(currentCards.get(i))) {
                tempCardSeq[i] = currentCards.get(i);
                cardsToSelect--;
            }
        }
    }

    /**
     * Draws a given set of cards and adds listener on them
     *
     * @param cards to draw on screen
     */
    private void draw(List<Card> cards) {
        table.clearChildren();

        Label infoField = new Label(playerTurn, assetHandler.getSkin());
        table.add(infoField).top().left().colspan(3).expandY().row();

        if (showGreetingText) {
            Label txt = new Label("Select 5 cards", assetHandler.getSkin());
            table.add(txt).right().colspan(5).row();
        }

        //handle indicators over cards
        for (int i = 0; i < cards.size(); i++) {
            if (cardManager.isLocked(cards.get(i))) {
                addLockLabel();
            } else if (selectedCards.contains(cards.get(i))) {
                addSelectLabel(i);
            } else {
                table.add(); //empty cell
            }
        }

        table.row();

        for (int i = 0; i < cards.size(); i++) {
            putCardInTable(cards.get(i));
        }
        table.add(buttonTable).expandX().right();
        stage.addActor(table);
    }

    private void addSelectLabel(int index) {
        String filename = "button" + (index + 1);
        Image image = new Image(new TextureRegion(assetHandler.getTexture("CardImages/" + filename + ".png")));
        table.add(image).height(30).width(97);
    }

    private void addLockLabel() {
        Image lock = new Image(new TextureRegion(assetHandler.getTexture("lock.png")));
        table.add(lock).height(30).width(97);
    }

    private void putCardInTable(Card c) {
        final Card card = c;
        final ImageButton cardButton = new ImageButton(new TextureRegionDrawable(assetHandler.getTexture(c)));

        cardButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!selectedCards.contains(card) && cardsToSelect > 0 && !cardManager.isLocked(card)) {
                    showGreetingText = false;
                    putInTempSeq(card);
                    swapCards(card);
                    cardsToSelect--;
                }
                if (cardManager.isLocked(card)) {
                    System.out.println("CARD IS LOCKED IN HAND");
                }
                System.out.println("Card priority: " + card.getPriority() + ", card type: " + card.getCardType());
                draw(currentCards);
                return true;
            }
        });

        Stack drawStack = new Stack();
        drawStack.add(cardButton);

        Label priority = new Label("" + card.getPriority(), assetHandler.getSkin());
        priority.setColor(0.109f, 0.258f, 0.168f, 1);
        priority.setFontScale(0.9f);
        priority.setTouchable(Touchable.disabled);
        priority.setAlignment(Align.center);
        priority.setAlignment(Align.top);
        drawStack.add(priority);

        table.add(drawStack).width(97).height(120).padBottom(5).left();
    }

    /**
     * swap card placement with first available position in currentCards
     *
     * @param card to swap
     */
    private void swapCards(Card card) {
        for (int i = 0; i < currentCards.size(); i++) {
            if (currentCards.get(i) == card) {
                break;
            }
            if (!selectedCards.contains(currentCards.get(i)) && !cardManager.isLocked(currentCards.get(i))) {
                Card temp = currentCards.get(i);
                int tempPos = currentCards.indexOf(temp);
                int swapPos = currentCards.indexOf(card);

                currentCards.set(tempPos, card);
                currentCards.set(swapPos, temp);
                break;
            }
        }
    }

    private void drawPowerDownOptions() {
        cancelPowerDownTable = new Table();
        cancelPowerDownTable.bottom().padBottom(30);
        cancelPowerDownTable.setFillParent(true);

        ImageButton cancel = new ImageButton(new TextureRegionDrawable(assetHandler.getTexture("cancelPowerDown.png")));
        ImageButton proceed = new ImageButton(new TextureRegionDrawable(assetHandler.getTexture("proceed.png")));

        cancel.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                cancelPowerDownTable.clearChildren();
                currentPlayer.setPowerDown(0);
                selectCards();
                return true;
            }
        });

        proceed.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                cancelPowerDownTable.clearChildren();
                currentPlayer.setPowerDown(1);
                currentPlayer.setReady();
                selectCards();
                return true;
            }
        });

        cancelPowerDownTable.add(proceed);
        cancelPowerDownTable.row();
        cancelPowerDownTable.add(cancel);

        stage.addActor(cancelPowerDownTable);
    }

    /**
     * @param card to put in card sequence ready to execute
     */
    private void putInTempSeq(Card card) {
        for (int i = 0; i < tempCardSeq.length; i++) {
            if (tempCardSeq[i] == null) {
                tempCardSeq[i] = card;
                selectedCards.add(card);
                break;
            }
        }
    }

    private void createOptionButtons() {
        buttonTable.defaults().width(100).height(30).padBottom(5);
        clear.setColor(Color.RED);
        clear.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Clear");
                selectCards();
                return true;
            }
        });

        submit.setColor(Color.GREEN);
        submit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if (cardManager.setCardSeq(currentPlayer, tempCardSeq)) {
                    if (powerDownPressed) {
                        currentPlayer.setPowerDown(2);
                    }
                    System.out.print("Cards submitted: ");
                    for (int i = 0; i < tempCardSeq.length; i++) {
                        System.out.print(tempCardSeq[i].toString() + ", ");
                    }
                    //TODO - send cards to host
                    System.out.println();
                    selectCards();
                } else {
                    System.out.println("Select 5 cards!");
                }
                return true;
            }
        });

        powerDown.setColor(Color.ORANGE);
        powerDown.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (powerDownPressed) {
                    powerDown.setText("Power Down");
                    powerDownPressed = false;
                } else {
                    powerDown.setText("Undo");
                    powerDownPressed = true;
                }
                System.out.println("Power Down");
                return true;
            }
        });

        buttonTable.add(powerDown).height(25).padBottom(10).row();
        buttonTable.add(submit).row();
        buttonTable.add(clear);
    }
}
