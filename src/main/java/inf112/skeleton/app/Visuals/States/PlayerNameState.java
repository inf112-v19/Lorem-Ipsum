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
import inf112.skeleton.app.Visuals.Text;


public class PlayerNameState extends State {

	private int numPlayers;
	private Board board;
	private Queue<Player> players;
	private TextArea[] textAreas;
	private Texture texture;

    private Skin uiSkin;
    private TextureRegionDrawable background;
	private Table table;
    private Table tablebutton;
    private Table nameButton;

    private boolean space;

	public PlayerNameState(GameStateManager gsm, Board board, int numPlayers) {
		super(gsm);
		super.camera.setToOrtho(false);
		this.space = false;

		this.uiSkin = assetHandler.getSkin();
		this.numPlayers = numPlayers;
		this.board = board;
		this.players = new Queue<>();
		this.textAreas = new TextArea[numPlayers];

		this.table = new Table(uiSkin);
		this.table.setFillParent(true);
        this.tablebutton = new Table(uiSkin);
        this.nameButton = new Table(uiSkin);
        this.table.setDebug(true);
        this.tablebutton.setDebug(true);
        this.nameButton.setDebug(true);

        //set background
        this.background = new TextureRegionDrawable(super.assetHandler.getTextureRegion("StateImages/secondBackground.png"));
        this.table.setBackground(this.background);

        //label
        Label topLabel = new Label("NAME YOUR PLAYER", uiSkin);
        topLabel.setFontScale(2);
        topLabel.setAlignment(Align.center);

        //this.nameButton.defaults().space(0, 40, 40, 40);

        //the visuals
        table.defaults().padBottom(60F);
        table.add(topLabel);
        table.row();
		creatTextFields();
		table.add(nameButton);
		table.row();
        submitButton();
        table.add(tablebutton);

		stage.addActor(table);
	}

	private void creatTextFields() {
		for (int i = 0; i < numPlayers; i++) {
			textAreas[i] = new TextArea("", uiSkin);

			Text text = new Text("Player " + (i + 1), uiSkin);
			text.setFontScale(1.5f);

            nameButton.defaults().pad(20,5,30,5).width(150).height(50);
            if (i <= 2) {
                nameButton.add(text).width(100);
                nameButton.add(textAreas[i]).width(150);
            } else {
                if (!space) {
                    nameButton.row();
                    this.space = true;
                }
                nameButton.add(text).width(100);
                nameButton.add(textAreas[i]).width(150);
            }
        }
	}

	/*private void creatSubmitButton() {
		TextureRegion textureRegion = assetHandler.getTextureRegion("submit.png");
		Image submit = new Image(textureRegion);
		submit.setPosition((Gdx.graphics.getWidth() / (float) 2) - (submit.getWidth() / (float) 2), 50);
		submit.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				for (int i = 0; i < textAreas.length; i++) {
					System.out.println(textAreas[i].getText());
					Player player = new Player(i, textAreas[i].getText(), Direction.EAST);
					players.addLast(player);
				}
				gsm.set(new SpawnPointState(gsm, board, players));
				return true;
			}
		});
		table.add(submit);
	}*/

    private void submitButton() {
        //submit button
        TextButton button = new TextButton("SUBMIT", uiSkin);
        button.getLabel().setFontScale(1.5f);
        //clickable
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
        tablebutton.add(button);
    }

}
