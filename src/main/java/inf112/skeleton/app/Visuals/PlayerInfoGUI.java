package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfoGUI {
	private BitmapFont[] fonts;
	private String[] playerNames;

	private Stage stage;
	private AssetHandler assetHandler;

	private Player[] players;
	private Batch batch;

	private List<Image> images;

	private Table table;

	public PlayerInfoGUI(Board board, Batch batch, Stage stage, AssetHandler assetHandler) {
		this.batch = batch;
		this.stage = stage;

		this.assetHandler = assetHandler;

		table = new Table();
		table.top().right();
		table.setFillParent(true);

		players = board.getAllPlayers();
		playerNames = new String[players.length];
		fonts = new BitmapFont[players.length];
		for (int i = 0; i < fonts.length; i++) {
			fonts[i] = new BitmapFont();
		}
		for (int i = 0; i < players.length; i++) {
			playerNames[i] = players[i].getPlayerID();
		}

		images = new ArrayList<>();

		//update();
	}

	/**
	 * Updates health and lives of each player
	 */
	public void update() {
		if (images.size() > 0) {
			clearImages();
		}
		for (int i = 0; i < players.length; i++) {
			int lives = players[i].getLives();
			int health = players[i].getHealth();
			drawLives(i, lives);
			drawHealthPoint(i, health);
			drawPlayerImage(i, players[i].getSpriteType());
		}
		render();
	}

	private void clearImages() {
		for (Image image : images) {
			image.remove();
		}
		images = new ArrayList<>();
	}

	/**
	 * renders fonts on screen
	 */
	public void render() {
		renderNames();
	}

	/**
	 * removes font from screen
	 */
	public void dispose() {
		for (BitmapFont font : fonts) {
			font.dispose();
		}

		table.clearChildren();

	}

	public void resize() {
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	private void drawPlayerImage(int row, SpriteType sprite) {
		TextureRegion texture = assetHandler.getTexture(sprite);
		int yDrawPos = playerYbyRow(row);
		int xDrawPos = Gdx.graphics.getWidth() - 60;
		createActor(texture, 30, 30, xDrawPos, yDrawPos);
	}

	private void drawHealthPoint(int row, int numberOfPoints) {
		int yDrawPos = healthYbyRow(row);
		int deltaX = 180;

		for (int i = 0; i < numberOfPoints; i++) {
			if (i == 5) {
				deltaX = 180;
				yDrawPos += 12;
			}
			int xDrawPos = Gdx.graphics.getWidth() - deltaX;
			TextureRegion textureRegion = new TextureRegion(assetHandler.getTexture("healthbar.png"));
			createActor(textureRegion, 10, 20, xDrawPos, yDrawPos);
			deltaX -= 22;
		}
	}

	private void drawLives(int row, int numberOfLives) {
		int yDrawPos = lifeYbyRow(row);
		int deltaX = 180;

		for (int i = 0; i < numberOfLives; i++) {
			int xDrawPos = Gdx.graphics.getWidth() - deltaX;
			TextureRegion textureRegion = new TextureRegion(assetHandler.getTexture("heart.png"));
			createActor(textureRegion, 20, 20, xDrawPos, yDrawPos);
			deltaX -= 25;
		}
	}

	/**
	 * creates an actor of type Image and adds on stage
	 *
	 * @param textureRegion
	 * @param height
	 * @param width
	 * @param xpos
	 * @param ypos
	 */
	private void createActor(TextureRegion textureRegion, int height, int width, int xpos, int ypos) {
		TextureRegion texture = textureRegion;
		Image image = new Image(texture);
		image.setSize(width, height);
		image.setPosition(xpos, ypos);
		images.add(image);
		stage.addActor(image);
	}

	private int lifeYbyRow(int row) {
		if (row == 0) {
			return Gdx.graphics.getHeight() - 40;
		} else {
			return Gdx.graphics.getHeight() - 40 - (row * 75);
		}
	}

	private int healthYbyRow(int row) {
		if (row == 0) {
			return Gdx.graphics.getHeight()-70;
		} else {
			return Gdx.graphics.getHeight()-70 - (row * 75);
		}
	}

	private int playerYbyRow(int row) {
		if (row == 0) {
			return Gdx.graphics.getHeight()-70;
		} else {
			return Gdx.graphics.getHeight()-70 - (row * 75);
		}
	}

	private void renderNames() {
		batch.begin();
		int xpos = Gdx.graphics.getWidth() - 180;
		int ypos = Gdx.graphics.getHeight()-5;
		for (int i = 0; i < playerNames.length; i++) {
			fonts[i].draw(batch, playerNames[i], xpos, ypos);
			ypos -= 75;
		}
		batch.end();
	}
}
