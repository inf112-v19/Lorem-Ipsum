package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.Netcode.Host;
import inf112.skeleton.app.Visuals.Text;
import io.netty.channel.socket.SocketChannel;

import java.util.ArrayList;

public class LobbyState extends State {

	private Table table;
	private Skin skin;
	private Host server;
	private ArrayList<SocketChannel> socketChannels;
	private Text status;

	public LobbyState(GameStateManager gsm) {
		super(gsm);
		this.server = new Host(gsm);
		this.skin = super.assetHandler.getSkin();
		this.socketChannels = new ArrayList<>();

		this.status = new Text("", skin, Text.TextPosition.TOP_LEFT);
		this.status.setColor(Color.RED);

		this.table = new Table();
		this.table.center();
		this.table.setFillParent(true);
		this.table.setBackground(new TextureRegionDrawable(super.assetHandler.getTexture("StateImages/menuBackground.jpg")));
		updateConnectedPlayers();

		super.stage.addActor(this.table);
		super.stage.addActor(status);

		startServer();
	}

	public void addSocketChannel(SocketChannel ch){
		socketChannels.add(ch);
		updateConnectedPlayers();
	}

	public void updateConnectedPlayers(){
		this.table.clearChildren();
		addTableHeader();
		for (SocketChannel ch : socketChannels) {
			this.table.add(new Text(ch.toString(), this.skin));
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
				if (socketChannels.size() > 0){
					System.out.println("setting ChooseBoardState");
					gsm.set(new ChooseBoardState(gsm));
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
		System.out.print("Staring server... ");
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					server.start();
				}catch (Exception e){
					System.err.println("Error while starting the server");
					e.printStackTrace();
				}
			}
		}).start();
		System.out.println("Success");
	}



}
