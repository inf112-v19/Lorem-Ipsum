package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Netcode.Client;
import inf112.skeleton.app.Netcode.MulticastReceiver;
import inf112.skeleton.app.Visuals.Text;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;


public class JoinGameState extends State {

    private Client client;
    private Table table;
    private Skin skin;
    private TextArea textArea;
    private Text status;
    private boolean tryConnect;
    private String buttonText;

    private Thread findHostThread;
    private ArrayList<String> hosts = new ArrayList<>();
    private MulticastReceiver receiver = new MulticastReceiver(hosts);
    private boolean exitFindHost = false;

    private boolean clientFail;

    public JoinGameState(GameStateManager gsm) {
        super(gsm);
        this.table = new Table();
        this.table.center();
        this.table.setFillParent(true);
        this.table.setBackground(new TextureRegionDrawable(assetHandler.getTexture("StateImages/secondBackground.png")));
        this.skin = assetHandler.getSkin();
        this.tryConnect = false;
        this.clientFail = false;

        this.status = new Text("", skin, Text.TextPosition.TOP_LEFT);
        this.status.setColor(Color.RED);

        addStatusText();
        addText("IP:");
        addTextArea();
        addSubmitButton();
        findHosts();

        super.stage.addActor(this.table);

    }

    public void findHosts() {
        table.row().padTop(30);
        table.add(new Text("Searching for available hosts...", skin));
        table.row();

        findHostThread = new Thread(new Runnable() {
            @Override
            public void run() {
                table.row();
                receiver.start();

                int i = 0;

                while (true) {
                    if (exitFindHost) {
                        break;
                    }

                    if (i < hosts.size()) {
                        addHostButton(hosts.get(i));
                        i++;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }


        });

        findHostThread.start();
    }

    private void addHostButton(final String host) {
        TextButton hostButton = new TextButton(host, skin);
        hostButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                tryConnect = true;
                buttonText = host;
                return true;
            }
        });

        table.add(hostButton);
        table.row();
    }


    private void addStatusText() {
        this.status = new Text("", skin, Text.TextPosition.TOP_LEFT);
        this.status.setColor(Color.RED);
        this.status.setAlignment(Align.topLeft);
        this.table.addActor(this.status);
    }

    private void addTextArea() {
        this.textArea = new TextArea("localhost", skin);
        this.table.add(textArea);
        this.table.row();
    }

    private void addText(String string) {
        Text text = new Text(string, skin);
        this.table.add(text);
        this.table.row();
    }

    private void addSubmitButton() {
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
        if (client != null) {
            String boardName = client.getClientHandler().getBoardName();
            if (boardName != null) {
                exitFindHost = true;
                receiver.interrupt();
                gsm.set(new PlayerNameState(gsm, new Board(boardName), this.client));
            }
        }


        if (this.tryConnect) {
            String inputText = textArea.getText();
            if (buttonText != null) {
                inputText = buttonText;
            }

            final int port = 6666;
            final int timeout = 1000;
            InetSocketAddress socketAddress = new InetSocketAddress(inputText, port);
            boolean isReachable = false;
            try {
                isReachable = socketAddress.getAddress().isReachable(timeout);
            } catch (IOException ioE) {
                ioE.printStackTrace();
            } catch (NullPointerException nullPointerE) {
                this.status.setText("IP is invalid");
                System.err.println("IP is invalid");
                this.tryConnect = false;
                return;
            }

            if (isReachable) {
                client = new Client(inputText, port);
                startClient();
                this.table.reset();
                addText("Waiting for host");
            } else {
                this.status.setText("Unable to connect to host");
                System.err.println("Unable to connect to host");
            }
            this.tryConnect = false;
        }
    }


    private void startClient() {
        System.out.println("Staring client... ");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.start();
                } catch (Exception e) {
                    clientFail = true;
                    System.err.println("Error while starting the client");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    @Override
    public void render() {
        super.render();
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            System.out.println("PAUSE!");
            this.gsm.push(new PauseState(this.gsm, this.client));
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (this.clientFail) {
            gsm.set(new MenuState(gsm, "An error occurred while starting the client"));
        }

    }
}
