package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Player;

import java.util.HashSet;
import java.util.List;

public class CardGUI {
    private Stage stage;
    private CardManager cardManager;
    private AssetHandler assetHandler;

    private Table table;

    private Player currentPlayer;
    private String playerTurn;

    private Card[] tempCardSeq;
    private HashSet<Card> selectedCards;
    private ImageButton[] displayedCardsArr;
    private int cardsToSelect;

    private ImageButton clear;
    private ImageButton submit;
    private ImageButton powerDown;

    public CardGUI(CardManager cardManager, Stage stage, AssetHandler assetHandler) {
        this.stage = stage;
        this.cardManager = cardManager;
        this.assetHandler = assetHandler;

        table = new Table();
        table.bottom().left();
        table.setFillParent(true);

        clear = new ImageButton(new TextureRegionDrawable(assetHandler.getTexture(SpriteType.CARD_CLEAR)));
        submit = new ImageButton(new TextureRegionDrawable(assetHandler.getTexture(SpriteType.CARD_SUBMIT)));
        powerDown = new ImageButton(new TextureRegionDrawable(assetHandler.getTexture("powerDown.png")));

        cardManager.newRound();
        selectCards();
    }

    private void selectCards() {
        if (cardManager.hasNotReadyPlayers()) {
            currentPlayer = cardManager.getPlayer();
            playerTurn = currentPlayer.getPlayerID() + "'s turn";
            List<Card> currentCards = currentPlayer.getCardHand();

            //if (currentPlayer.isAI())
            //do something

            //else

            if (currentPlayer.getPowerDown() == 3) {
                //drawPowerDownOptions(currentCards);
            } else {
                draw(currentCards);
            }
        }
    }

    private void draw(List<Card> cards) {
        table.clearChildren();

        tempCardSeq = new Card[5];
        selectedCards = new HashSet<>();
        cardsToSelect = 5;

        //handle indicators over cards
        for (int i = 0; i < cards.size(); i++) {
            if (cardManager.isLocked(cards.get(i))) {
                cardsToSelect--;
                //do something
            }
            if (selectedCards.contains(cards.get(i))) {
                cardsToSelect--;
                //do something
            }
        }

        table.row();

        for (int i = 0; i < cards.size(); i++) {
            putCardInTable(cards.get(i));
        }

    }

    private void putCardInTable(Card c) {
        final Card card = c;
        final ImageButton cardButton = new ImageButton(new TextureRegionDrawable(assetHandler.getTexture(c)));

        cardButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!selectedCards.contains(cardButton) && cardsToSelect > 0 && !cardManager.isLocked(card)) {
                    putInTempSeq(card);
                }
                if (cardManager.isLocked(card)) {
                    System.out.println("CARD IS LOCKED IN HAND");
                }
                System.out.println("Card priority: " + card.getPriority() + ", card type: " + card.getCardType());
                return true;
            }
        });

        table.add(cardButton).width(97).height(135);
    }

    private void putInTempSeq(Card card) {
        for (int i = 0; i < tempCardSeq.length; i++) {
            if (tempCardSeq[i] != null) {
                tempCardSeq[i] = card;
            } else {
                System.out.println("TempCardSeq full");
            }
        }
    }

}
