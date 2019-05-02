package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Netcode.Client;
import inf112.skeleton.app.Netcode.Host;
import inf112.skeleton.app.Visuals.Text;

import java.util.Arrays;
import java.util.HashMap;


public class PlayerNameState extends State {
	private Client client;
	private Host host;

	private Skin skin;
	private int numPlayers;
	private Board board;
	private Queue<Player> players;
	private TextArea[] textAreas;
	private Texture texture;
	private TextureRegionDrawable background;
	private Table table;
	private boolean continueToNextState;
	private HashMap<Integer, String> clientNames;
	private boolean clientHasSent = false;

	private Text waitingText;
	private boolean row;

	private Table lowerTable;
	private Table tableButton;

	//em
    private int aiAmount;

	public PlayerNameState(GameStateManager gsm, Board board, int numPlayers, Host host) {
		super(gsm);
		this.host = host;
		this.client = null;
		this.board = board;
		this.row = false;
		//table
		this.skin = assetHandler.getSkin();
		this.table = new Table(this.skin);
		this.table.setFillParent(true);
		this.lowerTable = new Table(this.skin);
		this.tableButton = new Table(this.skin);
		// should be one if host is null
		this.numPlayers = numPlayers;
		this.players = new Queue<>();
		this.textAreas = new TextArea[this.numPlayers];
		this.waitingText = new Text("Waiting on clients", this.skin);

		setBackground();
		this.table.defaults().padBottom(60F);
		this.table.add(getTopLabel());
		this.table.row();
		this.table.add(getTextFields());
		this.table.row();
		this.table.add(getSliderAndSubmit());

		//DEBUG
        //this.lowerTable.setDebug(true);

		super.stage.addActor(this.table);
	}

	public PlayerNameState(GameStateManager gsm, Board board, Client client) {
		super(gsm);
		this.host = null;
		this.client = client;
		this.board = board;
		this.row = false;
		//table
		this.skin = assetHandler.getSkin();
		this.table = new Table(this.skin);
		this.table.setFillParent(true);
		this.lowerTable = new Table(this.skin);
		this.tableButton = new Table(this.skin);
		// should be one if host is null
		this.numPlayers = numPlayers;
		this.players = new Queue<>();
		this.textAreas = new TextArea[this.numPlayers];
		this.waitingText = new Text("Waiting on clients", this.skin);

		setBackground();
		this.table.defaults().padBottom(60F);
		this.table.add(getTopLabel());
		this.table.row();
		this.table.add(getTextFields());
		this.table.row();
        this.table.add(getSliderAndSubmit());

        super.stage.addActor(this.table);
	}

	private void setBackground() {
		this.background = new TextureRegionDrawable(super.assetHandler.getTexture("StateImages/secondBackground.png"));
		this.table.setBackground(this.background);
	}

	private Label getTopLabel() {
		Label topLabel = new Label("NAME YOUR PLAYER", this.skin);
		topLabel.setFontScale(2);
		topLabel.setAlignment(Align.center);
		return topLabel;
	}

	private Table getTextFields() {
		for (int i = 0; i < this.numPlayers; i++) {
			this.textAreas[i] = new TextArea("", this.skin);

			Text text = new Text("Player " + (i + 1), this.skin);
			text.setFontScale(1.5f);

			this.tableButton.defaults().pad(20,5,30,5);
			if (i <= 2) {
				this.tableButton.add(text).width(100);
				this.tableButton.add(this.textAreas[i]).width(150);
			} else {
				if (!this.row) {
					this.tableButton.row();
					this.row = true;
				}
				this.tableButton.add(text).width(100);
				this.tableButton.add(this.textAreas[i]).width(150);
			}
		}
		return this.tableButton;
	}

	private Table getSliderAndSubmit() {
	    //submit
		TextButton button = new TextButton("SUBMIT", this.skin);
		button.getLabel().setFontScale(1.5f);
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				continueToNextState = true;
				table.clearChildren();
				table.add(waitingText);
                System.out.println("You chose " + aiAmount + " AI(s)");
            }
		});

		//slider
        aiAmount = 6-this.numPlayers;
        final Slider slider = new Slider(0, aiAmount, 1, false, skin);
        Label aiAmounts = new Label("Max " + aiAmount + " AI(s)", skin);
        aiAmounts.setFontScale(1.5f);
        final int[] value = {0};

        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                value[0] = (int) slider.getValue();
                saveAIAmount(value);

            }
        });
        this.lowerTable.defaults().width(150).height(50).padLeft(25);
        this.lowerTable.add(aiAmounts);
        this.lowerTable.add(slider);
        this.lowerTable.add(button);
        return this.lowerTable;
    }

    private void saveAIAmount(int[] value) {
        aiAmount = value[0];
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
				playernames += player.getPlayerName() + ",";
			}

			this.host.send("PLAYER_NAMES!" + playernames);
			gsm.set(new SpawnPointState(gsm, board, players, host));
		}
	}

	private synchronized void isClientHandling(){
		//clients should only send name one time
		if (!clientHasSent){
			String name = textAreas[0].getText();
			this.client.send("NAME!" + name);
			this.clientHasSent = true;
		}
		// listen to host
		String playerNames = this.client.getClientHandler().getNames();
		System.out.println("playernames = " + playerNames);
		if (playerNames!= null && clientHasSent){
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
