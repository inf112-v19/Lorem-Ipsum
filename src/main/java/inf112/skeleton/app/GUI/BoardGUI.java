package inf112.skeleton.app.GUI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class BoardGUI {

    Board board;
    OrthogonalTiledMapRenderer tiledMapRenderer;

    public BoardGUI() {
        //board = new TmxMapLoader().load("EmptyBoard.tmx");
        //tiledMapRenderer = new OrthogonalTiledMapRenderer(board);
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        //tiledMapRenderer.setView(camera);
        //tiledMapRenderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //TODO - Draw the board
        


        //super.render(camera, batch);
        batch.end();
    }


    public void update() {

    }


    public void dispose() {
        board.dispose();
    }

}
