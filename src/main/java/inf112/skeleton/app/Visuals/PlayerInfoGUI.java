package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Player;

public class PlayerInfoGUI {
    private BitmapFont[] fonts;
    private String[] playerNames;

    private Stage stage;

    private Board board;
    private Player[] players;
    private Batch batch;

    private int healthCol;
    private int healthRow;
    private int lifeCol;
    private int lifeRow;

    public PlayerInfoGUI(Board board, Batch batch) {
        this.board = board;
        this.batch = batch;

        this.stage = new Stage();

        players = board.getAllPlayers();
        playerNames = new String[players.length];
        fonts = new BitmapFont[players.length];
        for (int i = 0; i < fonts.length; i++) {
            fonts[i] = new BitmapFont(true);
        }
        for (int i = 0; i < players.length; i++) {
            playerNames[i] = players[i].getPlayerID();
        }

        displayNames();
    }


    public void render() {
        for (int i = 0; i < players.length; i++) {
        }
        displayNames();
    }

    private void drawHealthPoint(int col, int row) {

    }

    private void displayNames() {
        batch.begin();

        //int xpos = Gdx.graphics.getWidth()-100;
        //int ypos = 20;

        int xpos = 20;
        int ypos = 20;

        for (int i = 0; i < playerNames.length; i++) {
            fonts[i].draw(batch, playerNames[i], xpos, ypos);
            ypos += 80;
        }

        batch.end();
    }

}
