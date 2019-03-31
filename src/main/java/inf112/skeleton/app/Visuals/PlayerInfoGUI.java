package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Player;

public class PlayerInfoGUI {
    private BitmapFont[] fonts;
    private String[] playerNames;

    private Texture texture;

    private Stage stage;
    private AssetHandler assetHandler;

    private Player[] players;
    private Batch batch;

    public PlayerInfoGUI(Board board, Batch batch, Stage stage, AssetHandler assetHandler) {
        this.batch = batch;
        this.stage = stage;

        this.assetHandler = assetHandler;

        players = board.getAllPlayers();
        playerNames = new String[players.length];
        fonts = new BitmapFont[players.length];
        for (int i = 0; i < fonts.length; i++) {
            fonts[i] = new BitmapFont(true);
        }
        for (int i = 0; i < players.length; i++) {
            playerNames[i] = players[i].getPlayerID();
        }
        update();
    }

    /**
     * Updates health and lives of each player
     */
    public void update() {
        for (int i = 0; i < players.length; i++) {
            int lives = players[i].getLives();
            int health = players[i].getHealth();
            drawLives(i, lives);
            drawHealthPoint(i, health);
            drawPlayerImage(i, players[i].getSpriteType());
        }
        render();
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
        texture.dispose();
    }

    public void resize() {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void drawPlayerImage(int row, SpriteType sprite) {
        TextureRegion texture = assetHandler.getTexture(sprite);
        int yDrawPos = playerYbyRow(row);
        int xDrawPos = Gdx.graphics.getWidth() - 60;
        createActor(texture, 30, 30, xDrawPos, yDrawPos, false);
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
            texture = assetHandler.getTexture("healthbar.png");
            TextureRegion textureRegion = new TextureRegion(texture);
            createActor(textureRegion, 10, 20, xDrawPos, yDrawPos, true);
            deltaX -= 22;
        }
    }

    private void drawLives(int row, int numberOfLives) {
        int yDrawPos = lifeYbyRow(row);
        int deltaX = 180;

        for (int i = 0; i < numberOfLives; i++) {
            int xDrawPos = Gdx.graphics.getWidth() - deltaX;
            texture = assetHandler.getTexture("heart.png");
            TextureRegion textureRegion = new TextureRegion(texture);
            createActor(textureRegion, 20, 20, xDrawPos, yDrawPos, true);
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
    private void createActor(TextureRegion textureRegion, int height, int width, int xpos, int ypos, boolean flip) {
        TextureRegion texture = textureRegion;
        if (flip) {
            texture.flip(false, true);
        }
        Image image = new Image(texture);
        image.setSize(width, height);
        image.setPosition(xpos, ypos);
        stage.addActor(image);
    }

    private int lifeYbyRow(int row) {
        if (row == 0) {
            return 20;
        } else {
            return 20 + (row * 75);
        }
    }

    private int healthYbyRow(int row) {
        if (row == 0) {
            return 45;
        } else {
            return 45 + (row * 75);
        }
    }

    private int playerYbyRow(int row) {
        if (row == 0) {
            return 38;
        } else {
            return 38 + (row * 75);
        }
    }

    private void renderNames() {
        batch.begin();
        int xpos = Gdx.graphics.getWidth() - 180;
        int ypos = 5;
        for (int i = 0; i < playerNames.length; i++) {
            fonts[i].draw(batch, playerNames[i], xpos, ypos);
            ypos += 75;
        }
        batch.end();
    }
}
