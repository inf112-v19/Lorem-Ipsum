package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Player;

public class PlayerInfoGUI {
    private BitmapFont[] fonts;
    private String[] playerNames;

    private Stage stage;

    private Player[] players;
    private Batch batch;

    public PlayerInfoGUI(Board board, Batch batch, Stage stage) {
        this.batch = batch;
        this.stage = stage;

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

    public void update() {
        for (int i = 0; i < players.length; i++) {
            int lives = players[i].getLives();
            int health = players[i].getHealth();
            drawLives(i, lives);
            drawHealthPoint(i, health);
        }
        render();
    }

    public void render() {
        renderNames();
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
            createActor("healthbar.png", 10, 20, xDrawPos, yDrawPos);
            deltaX -= 22;
        }
    }

    private void drawLives(int row, int numberOfLives) {
        int yDrawPos = lifeYbyRow(row);
        int deltaX = 180;

        for (int i = 0; i < numberOfLives; i++) {
            int xDrawPos = Gdx.graphics.getWidth() - deltaX;
            createActor("heart.png", 20, 20, xDrawPos, yDrawPos);
            deltaX -= 25;
        }
    }

    /**
     * creates an actor of type Image and adds on stage
     *
     * @param filepath
     * @param height
     * @param width
     * @param xpos
     * @param ypos
     */
    private void createActor(String filepath, int height, int width, int xpos, int ypos) {
        TextureRegion texture = new TextureRegion(new Texture(filepath));
        texture.flip(false, true);
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

    public void dispose() {
        for (BitmapFont font : fonts) {
            font.dispose();
        }
    }

    public void resize() {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

}
