package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import inf112.skeleton.app.Netcode.INetCode;

public class PauseState extends State {
    private boolean mainMenu;
    private boolean resume;
    private boolean exit;
    private boolean start;

    private Skin skin;
    private Table table;
    private Table tablebutton;

    private INetCode net;


    public PauseState(GameStateManager gsm, INetCode net) {
        super(gsm);

        this.mainMenu = false;
        this.resume = false;
        this.exit = false;
        this.start = false;

        this.skin = assetHandler.getSkin();
        this.table = new Table(this.skin);
        this.table.setFillParent(true);
        this.tablebutton = new Table(this.skin);

        this.table.defaults().padBottom(100F);
        setLabel();
        this.table.row();
        setButtons();
        this.net = net;
        super.stage.addActor(this.table);
    }

    private void setLabel() {
        Label topLabel = new Label("PAUSE", this.skin);
        topLabel.setFontScale(2);
        topLabel.setAlignment(Align.center);
        this.table.add(topLabel);
    }

    private void setButtons() {
        TextButton resumeBut = new TextButton("RESUME", this.skin);
        resumeBut.setColor(Color.TEAL);
        TextButton mainMenuBut = new TextButton("MAIN MENU", this.skin);
        mainMenuBut.setColor(Color.TEAL);
        TextButton exitBut = new TextButton("EXIT", this.skin);
        exitBut.setColor(Color.TEAL);

        resumeBut.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                start = true;
                resume = true;
            }
        });
        mainMenuBut.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                start = true;
                mainMenu = true;
            }
        });
        exitBut.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                start = true;
                exit = true;
            }
        });
        this.tablebutton.defaults().pad(0, 80, 20, 80).width(150).height(50);
        this.tablebutton.add(resumeBut);
        this.tablebutton.row();
        this.tablebutton.add(mainMenuBut);
        this.tablebutton.row();
        this.tablebutton.add(exitBut);
        this.table.add(this.tablebutton);
    }

    @Override
    protected void handleInput() {
        if (this.start) {
            if (this.resume) {
                System.out.println("Resume Game!");
                this.gsm.pop();

            } else if (this.mainMenu) {
                System.out.println("New Game!");
                this.gsm.set(new MenuState(this.gsm));
                if (this.net != null) {
                    this.net.disconnect();
                }
            } else if (this.exit) {
                System.out.println("Exit Game!");
                if (this.net != null) {
                    this.net.disconnect();
                }
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

}
