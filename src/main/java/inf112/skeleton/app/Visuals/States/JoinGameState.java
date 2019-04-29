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
import java.util.ArrayList;
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

		super.stage.addActor(this.table);

		//this.client = new Client("localhost", 6666, "Testname");

		//checkHosts("127.0.0");

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
		this.table.add(text).align(Align.left);
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
			String boardName = client.receive();
			if (boardName != null){
				gsm.set(new PlayerNameState(gsm, new Board(boardName), this.client));
			}
		}


		if (this.tryConnect){
			String inputText = textArea.getText();
			final int port = 6666;
			final int timeout = 1000;
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


	private void addHostsToTabel(){

	}

	public void checkHosts(String subnet){
		try{
			final ExecutorService es = Executors.newFixedThreadPool(20);
			final String ip = "127.0.0.1";
			final int timeout = 200;
			final List<Future<Boolean>> futures = new ArrayList<>();
			for (int port = 1; port <= 65535; port++) {
				futures.add(portIsOpen(es, ip, port, timeout));
			}
			es.shutdown();
			int openPorts = 0;
			for (final Future<Boolean> f : futures) {
				if (f.get()) {
					openPorts++;
				}
			}
			System.out.println("There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of " + timeout + "ms)");

		}catch (Exception e){
			e.printStackTrace();
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


}
