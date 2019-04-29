package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Netcode.Client;
import inf112.skeleton.app.Netcode.Host;
import inf112.skeleton.app.Netcode.INetCode;
import inf112.skeleton.app.Visuals.Text;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.HashMap;


public class PlayerNameState extends State {

	private Client client;
	private Host host;

	private Skin uiSkin;
	private int numPlayers;
	private Board board;
	private Queue<Player> players;
	private TextArea[] textAreas;
	private Texture texture;
	private TextureRegionDrawable background;
	private Table table;
	private boolean continueToNextState;
	private HashMap<Integer, String> clientNames;
	private boolean clientHasSendt = false;

	private Text waitingText;

	public PlayerNameState(GameStateManager gsm, Board board, int numPlayers, Host host) {
		super(gsm);
		super.camera.setToOrtho(false);

		this.host = host;
		this.client = null;

		this.uiSkin = assetHandler.getSkin();

		// should be one if host is null
		this.numPlayers = numPlayers;

		this.board = board;
		this.players = new Queue<>();
		this.textAreas = new TextArea[numPlayers];
		this.texture = super.assetHandler.getTexture("StateImages/secondBackground.png");
		this.background = new TextureRegionDrawable(texture);
		this.waitingText = new Text("Waiting on clients", uiSkin);

		this.table = new Table(uiSkin);
		this.table.setFillParent(true);
		this.table.setBackground(background);
		this.table.defaults().space(0, 40, 40, 40);

		creatTextFields();
		creatSubmitButton();

		stage.addActor(table);
	}

	public PlayerNameState(GameStateManager gsm, Board board, Client client) {
		super(gsm);
		super.camera.setToOrtho(false);

		this.host = null;
		this.client = client;

		this.uiSkin = assetHandler.getSkin();
		this.numPlayers = 1;
		this.board = board;
		this.players = new Queue<>();
		this.textAreas = new TextArea[numPlayers];
		this.texture = super.assetHandler.getTexture("StateImages/secondBackground.png");
		this.background = new TextureRegionDrawable(texture);

		this.table = new Table(uiSkin);
		this.table.setFillParent(true);
		this.table.setBackground(background);
		this.table.defaults().space(0, 40, 40, 40);

		this.waitingText = new Text("Waiting on host", uiSkin);

		creatTextFields();
		creatSubmitButton();

		stage.addActor(table);
	}

	private void creatTextFields() {
		int numberOfTextAreas = 1;

		// if playing local
		if (host == null){
			numberOfTextAreas = numPlayers;
		}

		for (int i = 0; i < numberOfTextAreas; i++) {
			textAreas[i] = new TextArea("", uiSkin);

			Text text = new Text("Player " + (i + 1), uiSkin);
			text.setFontScale(1.5f);

			table.add(text).width(100);
			table.add(textAreas[i]).width(150);
			table.row();
		}
	}

	private void creatSubmitButton() {
		TextureRegion textureRegion = assetHandler.getTextureRegion("submit.png");
		Image submit = new Image(textureRegion);
		submit.setPosition((Gdx.graphics.getWidth() / (float) 2) - (submit.getWidth() / (float) 2), 50);
		submit.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				continueToNextState = true;
				table.clearChildren();
				table.add(waitingText);
				return true;
			}
		});
		table.add(submit);
	}

	private synchronized void isHostHandling(){
		if (this.clientNames == null){
			return;
		}

		//this should never happen
		if (this.clientNames.size() != host.getHostHandler().getNumClients()){
			return;
		}

		//adding connected clients to players queue and at last the host
		for (int i = 0; i < clientNames.size(); i++){
			players.addLast(new Player(i, clientNames.get(i), Direction.EAST));
		}
		players.addLast(new Player(clientNames.size(), textAreas[0].getText(), Direction.EAST));

		//checking if correct amount of players are in the queue
		if (players.size == host.getHostHandler().getNumClients() + 1){
			System.out.println("Players: " + players.toString());

			String playernames = "";
			for (Player player : players){
				playernames += player.getPlayerID() + ",";
			}

			this.host.send("PLAYER_NAMES!" + playernames);
			gsm.set(new SpawnPointState(gsm, board, players, host));
		}
	}

	private synchronized void isClientHandling(){
		//clients should only send name one time
		if (!clientHasSendt){
			String name = textAreas[0].getText();
			this.client.send("NAME!" + name);
			this.clientHasSendt = true;
		}

		// listen to host
		String playerNames = this.client.getClientHandler().getNames();
		System.out.println("playernames = " + playerNames);
		if (playerNames!= null && clientHasSendt){
			String[] playernames = playerNames.split(",");
			for (int i = 0; i < playernames.length; i++){
				players.addLast(new Player(i, playernames[i], Direction.EAST));
			}
			gsm.set(new SpawnPointState(gsm, board, players, client));
		}
	}

	private void localGameHandling(){
		for (int i = 0; i < textAreas.length; i++) {
			System.out.println(textAreas[i].getText());
			Player player = new Player(i, textAreas[i].getText(), Direction.EAST);
			players.addLast(player);
			gsm.set(new SpawnPointState(gsm, board, players, null));
		}
	}


	@Override
	protected void handleInput() {
		if (this.continueToNextState){
			if (this.host != null){
				isHostHandling();
			}else if (this.client != null){
				isClientHandling();
			}else{
				localGameHandling();
			}
		}
	}


	@Override
	public synchronized void update(float dt) {
		super.update(dt);
		if (this.host != null && this.clientNames == null){
			this.clientNames = this.host.getHostHandler().getNames();
			System.out.println(this.clientNames.size());
		}

	}
}
