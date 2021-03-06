package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Queue;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import inf112.skeleton.app.Netcode.Client;
import inf112.skeleton.app.Netcode.Host;
import inf112.skeleton.app.Netcode.INetCode;
import inf112.skeleton.app.Visuals.BoardGUI;
import inf112.skeleton.app.Visuals.Text;

public class SpawnPointState extends State {
    private Board board;
    private Queue<Player> players;
    private BoardGUI boardGUI;
    private Text text;

    private Host host;
    private Client client;
    private INetCode net;

    public SpawnPointState(GameStateManager gsm, Board board, Queue<Player> players, INetCode net) {
        super(gsm);
        this.players = players;
        this.board = board;
        this.boardGUI = new BoardGUI(board, super.camera, super.stage, gsm, assetHandler);
        this.boardGUI.create();
        this.boardGUI.addListenersToStage();
        this.text = new Text("'s turn to choose spawn", assetHandler.getSkin(), Text.TextPosition.TOP_LEFT);
        this.text.prependDynamicsText(players.first().getPlayerName());
        super.stage.addActor(text);

        this.net = net;
        if (net instanceof Host) {
            this.host = (Host) net;
            this.client = null;
        } else if (net instanceof Client) {
            this.client = (Client) net;
            this.host = null;
        } else {
            this.client = null;
            this.host = null;
        }
    }

    @Override
    public void render() {
        super.render();
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            System.out.println("PAUSE!");
            this.gsm.push(new PauseState(this.gsm, this.net));
        }
    }

    private synchronized void isHostHandle() {
        this.host.getHostHandler().sendWhenReqiured();

        Position spawnPosition = this.host.getHostHandler().getSpawnPosition();
        if (spawnPosition != null) {
            putPlayerOnStage(board.getTile(spawnPosition));
            host.getHostHandler().requireSend();
        }
    }

    private synchronized void isClientHandle() {
        Position spawnPosition = this.client.getClientHandler().getSpawnPosition();
        if (spawnPosition != null) {
            System.out.println("the spawn possition is: " + spawnPosition);
            putPlayerOnStage(board.getTile(spawnPosition));
        }
    }

    @Override
    public synchronized void update(float dt) {
        Gdx.input.setInputProcessor(stage);
        if (this.host != null) {
            isHostHandle();
        } else if (this.client != null) {
            isClientHandle();
        }

        if (players.isEmpty()) {
            System.out.println("setting PlaceFlagState");
            CardManager cardManager = new CardManager(board, net);
            boardGUI.removeAllListeners();
            gsm.set(new PlaceFlagState(this.gsm, this.board, cardManager, this.net));
        }
    }

    private Position calcTilePosition(Tile tile) {
        float x = (tile.getX() - boardGUI.getxOffset()) / (tile.getWidth());
        float y = (tile.getY() - boardGUI.getyOffset()) / (tile.getHeight());
        return new Position((int) x, (int) y);
    }

    private synchronized boolean putPlayerOnStage(Tile tile) {
        if (!players.isEmpty()) {
            Position playerPos = calcTilePosition(tile);

            Player player = players.first();
            if (board.spawnPlayer(playerPos, player)) {
                players.removeFirst();
                player.setDrawable(new TextureRegionDrawable(assetHandler.getTexture(player)));
                player.setSize(tile.getWidth(), tile.getHeight());
                player.setPosition(tile.getX(), tile.getY());
                stage.addActor(player);
                System.out.println("placing " + player.getPlayerName());

                if (!players.isEmpty()) {
                    this.text.prependDynamicsText(players.first().getPlayerName());
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized void tileEventHandle(Tile tile) {
        if (this.net != null) {
            if (net.isThisTurn()) {
                if (putPlayerOnStage(tile)) {
                    net.send("SPAWN!" + new Position(tile, this.boardGUI));
                }
            }
            return;
        }
        putPlayerOnStage(tile);
    }
}
