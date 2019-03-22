package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.RoboRally;
import inf112.skeleton.app.Visuals.SpriteSheet;
import inf112.skeleton.app.Visuals.SpriteType;

public class ChoosePlayerState extends State {
    private SpriteSheet spriteSheet;
    private TextureRegion background1;

    private Stage stage;
    private Image startButton;

    private int amountPlayers = 3;
    private int modifiedHeight;

    private boolean start;

    public ChoosePlayerState(GameStateManager gsm, Board board) {
        super(gsm, board);

        this.spriteSheet = new SpriteSheet();
        this.stage = new Stage(new ScreenViewport());

        this.stage.getBatch().setProjectionMatrix(camera.combined);
        this.background1 = this.spriteSheet.getTexture(SpriteType.TEST_A);

        //Set start button
        this.startButton = new Image(new TextureRegionDrawable(new Texture("StateImages/start.png")));
        this.startButton.setSize(192, 49);
        this.startButton.setPosition((RoboRally.WIDTH / 2) - (192/2), RoboRally.HEIGHT-(49*11));
        this.stage.addActor(this.startButton);

        //set players
        SetAmountPlayers(amountPlayers);

        this.start = false;
        clickable();


    }

    private void clickable() {
        this.startButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (amountPlayers == 1) {
                    System.out.println("Player ready!");
                } else {
                    System.out.println(amountPlayers + " players ready!");
                }
                start = true;
                return true;
            }
        });
        Gdx.input.setInputProcessor(this.stage);
    }

    /**
     * adds the amount of players that can be chosen
     */
    private void SetAmountPlayers(int amountPlayers) {
        this.modifiedHeight = 102+51;
        for (int i = 0; i < amountPlayers; i++) {
            String filename = "no" + (i+1);
            Image nplayers = new Image(new TextureRegionDrawable(new Texture("StateImages/" + filename + ".png")));
            nplayers.setSize((390 / 2), (102 / 2));
            nplayers.setPosition((RoboRally.WIDTH / 2) - ((390/2)/ 2), RoboRally.HEIGHT - modifiedHeight);
            this.stage.addActor(nplayers);

            this.modifiedHeight += 102;
        }
    }

    @Override
    public void handleInput() {
        if (this.start) {
            gsm.set(new CardState(gsm, board));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render() {
        this.stage.act();
        this.stage.getBatch().begin();
        this.stage.getBatch().draw(this.background1, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
        this.stage.getBatch().end();
        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.spriteSheet.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        this.stage.getBatch().setProjectionMatrix(camera.combined);
    }
}