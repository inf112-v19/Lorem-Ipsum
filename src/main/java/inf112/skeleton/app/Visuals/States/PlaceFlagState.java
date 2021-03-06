package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import inf112.skeleton.app.Netcode.Client;
import inf112.skeleton.app.Netcode.Host;
import inf112.skeleton.app.Netcode.INetCode;
import inf112.skeleton.app.Visuals.BoardGUI;
import inf112.skeleton.app.Visuals.Text;

public class PlaceFlagState extends State {

	private Board board;
	private CardManager cardManager;
	private BoardGUI boardGUI;
	private int flagCount;
	private Player[] players;
	private Text text;

	private INetCode net;
	private Host host;
	private Client client;


	public PlaceFlagState(GameStateManager gsm, Board board, CardManager cardManager, INetCode net) {
		super(gsm);
		this.board = board;
		this.cardManager = cardManager;
		this.boardGUI = new BoardGUI(board, super.camera, super.stage, gsm, assetHandler);
		this.boardGUI.create();
		this.boardGUI.addListenersToStage();
		this.players = board.getAllPlayers();
		this.text = new Text("'s turn to place flag", assetHandler.getSkin(), Text.TextPosition.TOP_LEFT);
		this.text.prependDynamicsText(players[0].getPlayerName());
		super.stage.addActor(text);

		this.net = net;
		if (net instanceof Host){
			this.host = (Host)net;
			this.host.getHostHandler().requireSend();
			this.client = null;
		}else if(net instanceof Client){
			this.client = (Client)net;
			this.host = null;
		}else{
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

	private synchronized void isHostHandle(){
		this.host.getHostHandler().sendWhenReqiured();

		Position flagPosition = this.host.getHostHandler().getFlagPosition();
		if(flagPosition != null){
			if(placeFlag(board.getTile(flagPosition))){
				this.host.getHostHandler().requireSend();
			}
		}


	}

	private synchronized void isClientHandle(){
		Position flagPosition = this.client.getClientHandler().getFlagPosition();
		if(flagPosition != null){
			System.out.println("the flag possition is: " + flagPosition );
			placeFlag(board.getTile(flagPosition));
		}
	}


	@Override
	public synchronized void update(float dt) {
		Gdx.input.setInputProcessor(stage);
		if(players.length > flagCount){
			this.text.prependDynamicsText(players[flagCount].getPlayerName());
		}

		if(this.host != null){
			isHostHandle();
		} else if(this.client != null){
			isClientHandle();
		}

		if (flagCount >= players.length) {
			boardGUI.removeAllListeners();
			gsm.set(new CardState(gsm, board, cardManager, this.net));
		}
	}

	private synchronized boolean placeFlag(Tile tile){
		Flag flag = new Flag(Direction.NORTH, flagCount);
		if (tile.placeFlagOnTile(flag)) {
			flag.setDrawable(new TextureRegionDrawable(assetHandler.getTexture(flag)));
			flag.setSize(tile.getWidth(), tile.getHeight());
			flag.setPosition(tile.getX(), tile.getY());

			stage.addActor(flag);
			flagCount++;
			return true;
		}
		return false;
	}

	@Override
	public synchronized void tileEventHandle(Tile tile) {
		if (this.net != null){
			if(this.net.isThisTurn()){
				if(placeFlag(tile)){
					this.net.send("PLACE_FLAG!" + new Position(tile, this.boardGUI));
				}
			}
			return;
		}
		placeFlag(tile);
	}
}
