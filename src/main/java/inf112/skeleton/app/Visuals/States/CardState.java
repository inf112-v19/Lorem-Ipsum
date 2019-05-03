package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.Netcode.Client;
import inf112.skeleton.app.Netcode.Host;
import inf112.skeleton.app.Netcode.INetCode;
import inf112.skeleton.app.Visuals.*;
import inf112.skeleton.app.GameMechanics.Player;

import java.util.HashMap;

public class CardState extends State {

    private Board board;
    private BoardGUI boardGUI;
    private SpriteBatch batch;
    private Player[] players;

    private CardHandGUI cardHandGUI;

    private PlayerInfoGUI playerInfoGUI;

    private CardManager cardManager;

    private INetCode net;
    private boolean shouldSend;

    public CardState(GameStateManager gsm, Board board, CardManager cardManager, INetCode net) {
        super(gsm);
        this.board = board;
        this.batch = new SpriteBatch();
        this.boardGUI = new BoardGUI(board, this.camera, this.stage, this.gsm, super.assetHandler);
        this.boardGUI.create();

        this.players = board.getAllPlayers();

        this.playerInfoGUI = new PlayerInfoGUI(board, batch, stage, super.assetHandler);
        this.cardManager = cardManager;

        this.cardHandGUI = new CardHandGUI(cardManager, stage, super.assetHandler);

        this.net = net;
        if (this.net != null) {
            this.net.resetCards();
        }
        this.shouldSend = true;
    }

    private synchronized void isHostHandling() {
        Host host = (Host) this.net;

        HashMap<Integer, String> cards = host.getHostHandler().getCards();
        if (cards != null && this.shouldSend) {
            this.shouldSend = false;
            host.send("CARDS!" + cards.toString());
            for (int i = 0; i < players.length; i++) {
                HashMap<Integer, Integer> powerdownStatusMap = host.getHostHandler().getPowerdownStatus();
                if (powerdownStatusMap != null) {
                    try {
                        int powerdownStatus = powerdownStatusMap.get(players[i].getIndex());
                        players[i].setPowerDown(powerdownStatus);
                    } catch (NullPointerException e) {
                        gsm.set(new MenuState(gsm, "Something unexpected happened! Try to host again"));
                    }
                }
                if (net.getIndex() != players[i].getIndex()) {
                    cardManager.setCardSeq(players[i], host.getHostHandler().getCardArray(i));
                }
            }
        }

        if (players[host.getIndex()].isReady()) {
            String cardString = players[host.getIndex()].getPowerDown() + "%";
            for (Card card : players[host.getIndex()].getCardSequence()) {
                cardString += card.getCardType() + "&" + card.getPriority() + ",";
            }
            host.getHostHandler().addHostCards(cardString);
        }
    }

    private synchronized void isClientHandling() {
        Client client = (Client) this.net;
        if (players[client.getIndex()].isReady() && this.shouldSend) {
            String sendString = "CARDS!" + players[client.getIndex()].getPowerDown() + "%";
            for (Card card : players[client.getIndex()].getCardSequence()) {
                sendString += card.getCardType() + "&" + card.getPriority() + ",";
            }
            client.send(sendString);
            this.shouldSend = false;
        }

        HashMap<Integer, Card[]> cards = client.getClientHandler().getCards();
        if (cards != null) {
            for (Player player : players) {
                HashMap<Integer, Integer> powerdownStatusMap = client.getClientHandler().getPowerdownStatus();
                if (powerdownStatusMap != null) {
                    try {
                        int powerdownStatus = powerdownStatusMap.get(player.getIndex());
                        player.setPowerDown(powerdownStatus);
                    } catch (NullPointerException e) {
                        gsm.set(new MenuState(gsm, "Something unexpected happened! Try to join a game again"));

                    }
                }
                if (net.getIndex() != player.getIndex()) {
                    if (cards.get(player.getIndex()) != null) {
                        cardManager.setCardSeq(player, cards.get(player.getIndex()));
                    }
                }
            }
        }
    }


    @Override
    public void update(float dt) {
        super.update(dt);
        playerInfoGUI.update();


        if (this.net instanceof Client) {
            //client
            isClientHandling();
        } else if (this.net instanceof Host) {
            isHostHandling();
            //host
        } else {
            //local
        }

        for (Player player : players) {
            if (!player.isReady()) {
                return;
            }
        }
        board.initRound();
        gsm.set(new ActionState(gsm, board, cardManager, this.net));
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            System.out.println("PAUSE!");
            this.gsm.push(new PauseState(this.gsm, this.net));
        }
        super.render();
    }

    @Override
    public void dispose() {
        cardHandGUI.dispose();
        playerInfoGUI.dispose();
        batch.dispose();
    }
}
