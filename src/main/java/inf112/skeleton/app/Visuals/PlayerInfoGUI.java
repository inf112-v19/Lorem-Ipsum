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
    private final OrthographicCamera camera;
    private BitmapFont[] fonts;
    private String[] playerNames;

    private Stage stage;
    private ScreenViewport screenViewport;

    private Board board;
    private Player[] players;
    private Batch batch;

    public PlayerInfoGUI(Board board, Batch batch, OrthographicCamera camera) {
        this.board = board;
        this.batch = batch;
        this.camera = camera;
        this.screenViewport = new ScreenViewport(camera);
        this.stage = new Stage(screenViewport);

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
        stage.draw();
    }

    private void drawHealthPoint(int row, int numberOfPoints) {
        int yDrawPos = healthYbyRow(row);
        int deltaX = 180;

        for (int i = 0; i < numberOfPoints; i++) {
            if (i == 5) {
                deltaX = 180;
                yDrawPos += 12;
            }

            TextureRegion texture = new TextureRegion(new Texture("healthbar.png"));
            texture.flip(false, true);
            Image health = new Image(texture);
            health.setSize(20, 10);
            health.setPosition(Gdx.graphics.getWidth() - deltaX, yDrawPos);
            stage.addActor(health);
            deltaX -= 22;
        }
    }

    private void drawLives(int row, int numberOfLives) {
        int yDrawPos = lifeYbyRow(row);
        int deltaX = 180;

        for (int i = 0; i < numberOfLives; i++) {
            TextureRegion texture = new TextureRegion(new Texture("heart.png"));
            texture.flip(false, true);
            Image life = new Image(texture);
            life.setSize(20, 20);
            life.setPosition(Gdx.graphics.getWidth() - deltaX, yDrawPos);
            stage.addActor(life);
            deltaX -= 25;
        }
    }

    private int lifeYbyRow(int row) {
        if (row == 0) {
            return 30;
        } else {
            return 30 + (row * 80);
        }
    }
    
    private int healthYbyRow(int row) {
        if (row == 0) {
            return 55;
        } else {
            return 55 + (row * 80);
        }
    }

    private void renderNames() {
        batch.begin();
        int xpos = Gdx.graphics.getWidth() - 180;
        int ypos = 15;
        for (int i = 0; i < playerNames.length; i++) {
            fonts[i].draw(batch, playerNames[i], xpos, ypos);
            ypos += 80;
        }
        batch.end();
    }

    public void dispose() {
        for (BitmapFont font : fonts) {
            font.dispose();
        }
        stage.dispose();
    }

    public void resize(){
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

}
