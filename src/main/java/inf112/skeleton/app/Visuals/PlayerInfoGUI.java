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
    }


    public void render() {
        renderNames();
        for (int i = 0; i < players.length; i++) {
        }
    }

    private void drawHealthPoint(int col, int row) {

    }

    private void renderNames() {
        batch.begin();

        int xpos = Gdx.graphics.getWidth()-180;
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
    }

}
