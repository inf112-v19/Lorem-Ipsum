package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class Text extends Widget {

	private String text;
	private String dynamicsText;
	private BitmapFont font;
	private SpriteBatch batch;
	private Stage stage;

	public Text(String text, int x, int y, Stage stage){
		this.font = new BitmapFont(Gdx.files.internal("default.fnt"));
		this.stage = stage;
		this.batch = new SpriteBatch();
		this.text = text;
		this.dynamicsText = text;
		this.setPosition(x,y);
		this.stage.addActor(this);
	}

	public Text(Stage stage) {
		this("", 0,0, stage);
	}

	public Text(int x, int y, Stage stage){
		this("", x,y,stage);
	}

	public Text(String text, Stage stage){
		this(text, 0, 0, stage);
	}

	public void setText(String text){
		this.text = text;
		this.dynamicsText = text;
	}

	public void appendText(String text){
		this.text = this.text + text;
		this.dynamicsText = this.text;
	}

	public void prependText(String text){
		this.text = text + this.text;
		this.dynamicsText = this.text;
	}

	public void appendDynamicsText(String dynamicText){
		this.dynamicsText = this.text + dynamicsText;
	}

	public void prependDynamicsText(String dynamicText){
		this.dynamicsText = dynamicText + this.text;
	}

	public void draw(){
		batch.begin();
		font.draw(batch, dynamicsText, super.getX(), super.getY());
		batch.end();
		//System.out.println(super.getX() + "," + super.getY());
	}

	public void dispose(){
		font.dispose();
		batch.dispose();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		this.draw();
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, stage.getHeight() - y);
	}


}
