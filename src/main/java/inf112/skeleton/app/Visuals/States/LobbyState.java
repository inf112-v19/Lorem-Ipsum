package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.Netcode.Host;
import inf112.skeleton.app.Visuals.Text;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetAddress;
import java.util.ArrayList;

public class LobbyState extends State {

	private Table table;
	private Skin skin;
	private Host host;
	private ArrayList<Channel> channels;
	private Text status;
	private boolean submit;

	public LobbyState(GameStateManager gsm) {
		super(gsm);
		this.submit = false;
		this.host = new Host(gsm);
		this.skin = super.assetHandler.getSkin();
		this.channels = new ArrayList<>();

		this.status = new Text("", skin, Text.TextPosition.TOP_LEFT);
		this.status.setColor(Color.RED);

		this.table = new Table();
		this.table.center();
		this.table.setFillParent(true);
		this.table.setBackground(new TextureRegionDrawable(super.assetHandler.getTexture("StateImages/secondBackground.png")));

		updateConnectedPlayers();

		super.stage.addActor(this.table);
		super.stage.addActor(status);

		startServer();

	}

	private void displayHostIP() {
		Text text;
		try {
			String hostIP = InetAddress.getLocalHost().getHostAddress();

			if (hostIP.substring(0, 3).equals("127")) {
				text = new Text("Security manager prevented application from retrieving host IP", skin);
			}
			else {
				text = new Text("Host IP: "+hostIP, skin);
			}
		}
		catch (Exception e) {
			text = new Text("Security manager prevented application from retrieving host IP", skin);
		}

		this.table.add(text);
		this.table.row();
	}

	@Override
	protected void handleInput() {
		if (this.submit){
			System.out.println("setting ChooseBoardState");
			gsm.set(new ChooseBoardState(gsm, this.host));
		}
	}

	/*
	public void addSocketChannel(Channel ch){
		channels.add(ch);
		updateConnectedPlayers();
	}
	 */

	public void updateConnectedPlayers(){
		this.table.clearChildren();
		displayHostIP();
		addTableHeader();

		ArrayList<ChannelHandlerContext> chctx = host.getHostHandler().getConnections();
		for (ChannelHandlerContext ctx : chctx) {
			channels.add(ctx.channel());
			this.table.add(new Text(ctx.channel().toString(), this.skin));
			this.table.row();
		}

		addSubmitButton();
		this.status.setText("");
	}

	private void addSubmitButton(){
		TextButton textButton = new TextButton("Submit", this.skin);
		textButton.setColor(Color.TEAL);
		textButton.getLabel().setFontScale(1.5f);
		textButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (channels.size() > 0){
					submit = true;
					return true;
				}
				status.setText("There must be at least one player connected to play online");
				return false;
			}
		});
		this.table.add(textButton);
	}

	private void addTableHeader(){
		Text text = new Text("Players joined:", this.skin);
		text.setFontScale(1.5f);
		text.setColor(Color.TEAL);
		this.table.add(text);
		this.table.row();
	}


	private void startServer(){
		System.out.println("Staring server... ");
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					host.start();
				}catch (Exception e){
					System.err.println("Error while starting the server");
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void render() {
		super.render();
		if (Gdx.input.isKeyPressed(Input.Keys.P)) {
			System.out.println("PAUSE!");
			this.gsm.push(new PauseState(this.gsm, this.host));
		}
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		updateConnectedPlayers();
	}
}
