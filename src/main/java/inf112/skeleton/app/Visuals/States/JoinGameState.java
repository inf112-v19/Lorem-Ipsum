package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Netcode.Client;
import inf112.skeleton.app.Netcode.ClientHandler;
import inf112.skeleton.app.Visuals.Text;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class JoinGameState extends State {

	private Client client;
	private Table table;
	private Skin skin;
	private TextArea textArea;
	private Text status;
	private boolean tryConnect;
	private String buttonText;

	public JoinGameState(GameStateManager gsm) {
		super(gsm);
		this.table = new Table();
		this.table.center();
		this.table.setFillParent(true);
		this.table.setBackground(new TextureRegionDrawable(super.assetHandler.getTexture("StateImages/secondBackground.png")));
		this.skin = assetHandler.getSkin();
		this.tryConnect = false;

		this.status = new Text("", skin, Text.TextPosition.TOP_LEFT);
		this.status.setColor(Color.RED);

		addStatusText();
		addText("IP:");
		addTextArea();
		addSubmitButton();
		findHosts();

		super.stage.addActor(this.table);

		//this.client = new Client("localhost", 6666, "Testname");

		//checkHosts("127.0.0");

	}

	public void findHosts(){
		table.row().padTop(30);
		table.add(new Text("Searching for available hosts...", skin));
		table.row();

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				table.add(new Text("Hostname    -    IP", skin));
				table.row();
				addHostsToTable();
			}
		});
		thread.start();
	}

	private void addStatusText(){
		this.status = new Text("", skin, Text.TextPosition.TOP_LEFT);
		this.status.setColor(Color.RED);
		this.status.setAlignment(Align.topLeft);
		this.table.addActor(this.status);
	}

	private void addTextArea(){
		this.textArea = new TextArea("localhost", skin);
		this.table.add(textArea);
		this.table.row();
	}

	private void addText(String string){
		Text text = new Text(string, skin);
		this.table.add(text);
		this.table.row();
	}

	private void addSubmitButton(){
		TextButton textButton = new TextButton("Submit", this.skin);
		textButton.setColor(Color.TEAL);
		textButton.getLabel().setFontScale(1.5f);
		textButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				tryConnect = true;
				return true;
			}
		});
		this.table.add(textButton);
	}

	@Override
	protected void handleInput() {
		if(client != null){
			String boardName = client.getClientHandler().getBoardName();
			if (boardName != null){
				gsm.set(new PlayerNameState(gsm, new Board(boardName), this.client));
			}
		}


		if (this.tryConnect){
			String inputText = textArea.getText();
			if (buttonText != null){
				inputText = buttonText;
			}

			final int port = 6666;
			final int timeout = 100000;
			InetSocketAddress socketAddress = new InetSocketAddress(inputText, port);
			boolean isReachable = false;
			try{
				isReachable = socketAddress.getAddress().isReachable(timeout);
			}catch (IOException ioE){
				ioE.printStackTrace();
			}catch (NullPointerException nullPointerE){
				this.status.setText("IP is invalid");
				System.err.println("IP is invalid");
				this.tryConnect = false;
				return;
			}

			if (isReachable){
				client = new Client(inputText, port);
				startClient();
				this.table.reset();
				addText("Waiting for host");
			}else{
				System.err.println("unable to connect to host");
			}
			this.tryConnect = false;
		}
	}


	public static Future<Boolean> portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout) {
		return es.submit(new Callable<Boolean>() {
			@Override public Boolean call() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return true;
				} catch (Exception ex) {
					return false;
				}
			}
		});
	}

	private void startClient(){
		System.out.println("Staring client... ");
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					client.start();
				}catch (Exception e){
					System.err.println("Error while starting the client");
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	private void addHostsToTable() {
		final int port = 6666;
		final int timeout = 1000;
		final ExecutorService es = Executors.newFixedThreadPool(20);
		String localHost = "192.168.1.1";
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
		}
		catch (UnknownHostException e) {
			System.err.println("UnknownHostException was thrown - assumed localHost was 192.168.1.1");
		}

		String subnet = getSubnet(localHost);
		System.out.println(subnet);
		HashMap<String, Future<Boolean>> addresses = new HashMap<>();

		for (int i = 1; i < 256; i++) {
			String ip = subnet+"."+i;
			Future<Boolean> isReachable = portIsOpen(es, ip, port, timeout);
			addresses.put(ip, isReachable);
		}
		es.shutdown();

		for (final HashMap.Entry<String, Future<Boolean>> entry : addresses.entrySet()) {
			try {
				if (entry.getValue().get()) {
					InetAddress hostaddr = InetAddress.getByName(entry.getKey());
					TextButton hostButton = new TextButton((hostaddr.getHostAddress() + " - " + entry.getKey()), skin);

					hostButton.addListener(new InputListener() {
						@Override
						public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
							tryConnect = true;
							buttonText = entry.getKey();
							return true;
						}
					});

					table.add(hostButton);
					table.row();
				}
			}
			catch (Exception e) {
				System.err.println(e);
			}

		}
	}

	private String getSubnet(String ip) {
		int indexEnd = ip.lastIndexOf(".");
		return ip.substring(0, indexEnd);
	}


}
