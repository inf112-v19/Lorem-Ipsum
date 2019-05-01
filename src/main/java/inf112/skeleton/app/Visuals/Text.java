package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Text extends Label {

	/**
	 * Enum for positions on the stage.
	 * TOP_LEFT is default
	 */
	public enum TextPosition {
		//TODO - make positioning better and correct
		TOP_LEFT(5, Gdx.graphics.getHeight()-(Text.fontSize * 35)),
		TOP_RIGHT(Gdx.graphics.getWidth() - DEFAULT_WIDTH - 20,Gdx.graphics.getHeight()-(Text.fontSize * 35)),
		BOTTOM_LEFT(20,20),
		BOTTOM_RIGHT(20,20),
		CENTER(0,0),
		TOP_CENTER(0,0),
		BOTTOM_CENTER(0,0);

		private float x;
		private float y;

		TextPosition(float x, float y){
			//System.out.println(x+","+y);
			this.x = x;
			this.y = y;
		}
	}

	private static final float fontSize = 1;
	private static final float DEFAULT_WIDTH = 190;
	private static final TextPosition DEFAULT_TEXT_POSITION = TextPosition.TOP_LEFT;
	private StringBuilder text;

	/**
	 * Use this constructor if the text should be positioned on the stage
	 * @param text
	 * @param skin
	 * @param textPosition
	 * @param width
	 */
	public Text(String text, Skin skin, TextPosition textPosition, float width) {
		super(text, skin);
		super.setWrap(true);
		super.setWidth(width);
		super.setFontScale(fontSize);
		super.setPosition(textPosition.x, textPosition.y);

		this.text = new StringBuilder();
		this.text.append(text);
	}

	public Text(String text, Skin skin, TextPosition textPosition) {
		this(text, skin, textPosition, DEFAULT_WIDTH);
	}


	/**
	 * Use this constructor if the text should be placed in a Table or WidgetGroup.
	 * in this case the positioning and width should be handled by the table and not us.
	 * @param text
	 * @param skin
	 */
	public Text(String text, Skin skin) {
		super(text, skin);
		super.setFontScale(fontSize);

		this.text = new StringBuilder();
		this.text.append(text);
	}


	public void appendText(String text) {
		this.text.append(text);
		this.setText(this.text);

	}

	public void prependText(String text) {
		this.text.insert(0, text);
		super.setText(this.text);
	}

	public void appendDynamicsText(String dynamicText) {
		StringBuilder builder = new StringBuilder();
		builder.append(this.text);
		builder.append(dynamicText);
		super.setText(builder);
	}

	public void prependDynamicsText(String dynamicText) {
		StringBuilder builder = new StringBuilder();
		builder.append(dynamicText);
		builder.append(this.text);
		super.setText(builder);
	}

	@Override
	public void setText(CharSequence newText) {
		super.setText(newText);
	}
}
