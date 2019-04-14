package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Player;
import java.util.HashMap;

public class PendingCardsGUI {

    private SpriteBatch batch;
    private Board board;
    private AssetHandler assetHandler;
    private Stage stage;


    private Card currentCard;
    private Card nextCard;
    private Player currentPlayer;
    private Player nextPlayer;
    private HashMap<Player, TextureRegion> playerTextures;

    Table table;
    Label playingCard;
    Label pendingCard;

    public PendingCardsGUI(SpriteBatch batch, Board board, Stage stage, AssetHandler assetHandler) {

        table = new Table();
        table.top().left();
        table.setFillParent(true);

        this.batch = batch;
        this.board = board;
        this.assetHandler = assetHandler;
        this.stage = stage;

        playerTextures = new HashMap<>();
        Player[] players = board.getAllPlayers();
        for (int i = 0; i < players.length; i++) {
            playerTextures.put(players[i], assetHandler.getTexture(players[i]));
        }

        playingCard = new Label("card being played: ", assetHandler.getSkin());
        pendingCard = new Label("next up: ", assetHandler.getSkin());



    }

    public void update() {
        currentCard = board.getCurCard();
        currentPlayer = board.getCurPlayer();
        nextCard = board.peekNextCard();
        nextPlayer = board.getNextPlayer();
    }

    public void render() {
        table.clearChildren();

        if (currentCard != null) {
            table.add(playingCard);
            table.row();
            drawCurrentCard();
        }
        if (nextCard != null) {
            table.add(pendingCard).left();
            table.row();
            drawNextCard();
        }

        stage.addActor(table);
    }

    private void drawCurrentCard() {
        TextureRegion card = new TextureRegion(assetHandler.getTexture(currentCard));
        addCardToStage(card, Gdx.graphics.getHeight()-145);
        TextureRegion player = new TextureRegion(playerTextures.get(currentPlayer));
        addPlayerImageToStage(player, Gdx.graphics.getHeight()-145);
    }

    private void drawNextCard() {
        TextureRegion card = new TextureRegion(assetHandler.getTexture(nextCard));
        addCardToStage(card, Gdx.graphics.getHeight()-300);
        TextureRegion player = new TextureRegion(playerTextures.get(nextPlayer));
        addPlayerImageToStage(player, Gdx.graphics.getHeight()-300);
    }

    private void addCardToStage(TextureRegion cardTexture, int yPos) {
        Image image = new Image(cardTexture);
        image.setSize(97, 135);
        image.setPosition(10, yPos);

        table.add(image).width(97).height(135);
    }

    private void addPlayerImageToStage(TextureRegion playerTexture, int yPos) {
        Image image = new Image(playerTexture);
        image.setSize(40, 40);
        image.setPosition(107, yPos + 10);
        table.add(image).width(40).height(40);
        table.row();
    }

    public void dispose() {
        table.clearChildren();
    }
}
