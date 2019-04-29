package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.Text;

public class PlayerNameState extends State {
	private int numPlayers;
	private Board board;
	private Queue<Player> players;
	private TextArea[] textAreas;

    private Skin skin;
    private TextureRegionDrawable background;
	private Table table;
    private Table tableSubmit;
    private Table tableButton;

    private boolean row;

	public PlayerNameState(GameStateManager gsm, Board board, int numPlayers) {
		super(gsm);
		super.camera.setToOrtho(false);
		this.row = false;

		this.numPlayers = numPlayers;
		this.board = board;
		this.players = new Queue<>();
		this.textAreas = new TextArea[numPlayers];

        this.skin = assetHandler.getSkin();
        this.table = new Table(this.skin);
		this.table.setFillParent(true);
        this.tableSubmit = new Table(this.skin);
        this.tableButton = new Table(this.skin);
        //this.table.setDebug(true);
        //this.tablebutton.setDebug(true);
        //this.tableName.setDebug(true);

        setBackground();
        this.table.defaults().padBottom(60F);
        this.table.add(getTopLabel());
        this.table.row();
        this.table.add(getTextFields());
        this.table.row();
        this.table.add(getSubmitButton());

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

    private Table getSubmitButton() {
        TextButton button = new TextButton("SUBMIT", this.skin);
        button.getLabel().setFontScale(1.5f);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                for (int i = 0; i < textAreas.length; i++) {
                    System.out.println(textAreas[i].getText());
                    Player player = new Player(i, textAreas[i].getText(), Direction.EAST);
                    players.addLast(player);
                }
                gsm.set(new SpawnPointState(gsm, board, players));
            }
        });
        this.tableSubmit.defaults().width(150).height(50);
        this.tableSubmit.add(button);
        return this.tableSubmit;
    }

}
