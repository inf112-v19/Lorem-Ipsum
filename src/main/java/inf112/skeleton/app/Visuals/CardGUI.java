package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Player;

import java.util.List;

public class CardGUI {
    Stage stage;
    CardManager cardManager;
    AssetHandler assetHandler;

    Table table;

    Player currentPlayer;
    String playerTurn;

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
                drawPowerDownOptions(currentCards);
            } else {
                draw(currentCards);
            }
        }
    }

    private void draw(List<Card> c) {
        
    }

}
