package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.Visuals.States.GameStateManager;
import inf112.skeleton.app.Visuals.States.MenuState;

public class RoboRally extends ApplicationAdapter {

    private Board board;

    public static final int WIDTH = 976;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Robo Rally";

    private GameStateManager gsm;
    private CardManager cardManager;

    public RoboRally() {
        super();
        board = new Board("Boards/BigBoard.txt");
        //board = new Board(setBoard());
        cardManager = new CardManager(board);
    }

    public String setBoard(int boardtype) {
        switch (boardtype) {
            case 1:
                return "Boards/BigBoard.txt";
            case 2:
                return "Boards/ExampleBoard.txt";
            case 3:
                return null;
            default:
                System.err.println("No board type found");
                return null;
        }
    }

    @Override
    public void create () {
        gsm = new GameStateManager();
        gsm.push(new MenuState(gsm, board, cardManager));
    }

    @Override
    public void render () {
        //TODO - handle input here
        //cleaning screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render();
    }

    @Override
    public void dispose () {
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gsm.resize();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
