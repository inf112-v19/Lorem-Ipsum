package inf112.skeleton.app.Visuals.States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Visuals.SpriteSheet;

public class InputTest extends State implements Screen {

    private Stage stage;
    private TextField txt;
    private SpriteSheet spriteSheet;

    public InputTest(GameStateManager gsm) {
        super(gsm);

        this.spriteSheet = new SpriteSheet();
        this.stage = new Stage(new ScreenViewport());
        this.stage.getBatch().setProjectionMatrix(camera.combined);

        Gdx.input.setInputProcessor(stage);
        //input

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton atxtB = new TextButton("click me", skin);
        //TextField.TextFieldStyle test = new TextField.TextFieldStyle();

        //TextButton atxtB = new TextButton("tester", new TextButton.TextButtonStyle());
        atxtB.setPosition(300, 300);
        atxtB.setSize(300, 40);
        stage.addActor(atxtB);

        /*txt = new TextField("", skin);
        //txt.setPosition((RoboRally.WIDTH/2)-((821/3)/2), 102-(102/3));
        txt.setPosition(300, 250);
        txt.setSize(300, 40);
        this.stage.addActor(txt);
*/

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
