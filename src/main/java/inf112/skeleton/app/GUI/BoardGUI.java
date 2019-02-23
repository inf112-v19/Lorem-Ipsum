package inf112.skeleton.app.GUI;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.badlogic.gdx.Gdx.gl;


public class BoardGUI {

    Board board;
    int xOffset;
    int yOffset;
    OrthographicCamera camera;
    SpriteBatch batch;


    int tilesize = 100;
    int boardWidth = 200;
    int boardHeight = 200;
    Texture texture = new Texture("RoboRallyTiles.png");
    TextureRegion[][] spriteSheet = new TextureRegion(texture,336,624).split(336/7, 624/13);


    public BoardGUI(OrthographicCamera camera, SpriteBatch batch) {
        this.camera = camera;
        this.batch = batch;

        xOffset = Gdx.graphics.getWidth()/2 - boardWidth/2;
        yOffset = Gdx.graphics.getHeight()/2 - boardHeight/2;

    }

    /**
     * function that sets the size of the board and tiles so that it is static
     * calling drawBoard for actuall drawing of the board
     *
     * this function is called from RoboRally
     */
    public void render() {
        camera.setToOrtho(false);
        batch.setProjectionMatrix(camera.combined);
        drawBoard();

    }


    /**
     * function that draws the board using the spriteSheet
     *
     */
    //TODO - should work width different tiles
    public void drawBoard(){
        batch.begin();

        for (int i = yOffset; i < boardHeight + yOffset; i+= tilesize){
            for (int j = xOffset; j < boardWidth + xOffset; j+= tilesize){
                batch.draw(spriteSheet[0][4], i, j, tilesize, tilesize);
            }
        }

        batch.end();
    }


}
