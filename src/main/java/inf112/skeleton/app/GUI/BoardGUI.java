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

import java.io.IOException;

import static com.badlogic.gdx.Gdx.gl;


public class BoardGUI {

    Board board;
    int xOffset;
    int yOffset;
    OrthographicCamera camera;
    SpriteBatch batch;


    int tilesize = 50;
    int boardWidth = 400;
    int boardHeight = 400;
    Texture texture = new Texture("RoboRallyTiles.png");
    TextureRegion[][] spriteSheet = new TextureRegion(texture,336,624).split(336/7, 624/13);


    public BoardGUI(OrthographicCamera camera, SpriteBatch batch) {
        this.camera = camera;
        this.batch = batch;


        //TODO - when board handles IOExeption and easy to use filepath.
        //board = new Board("BoardBuilderTest.txt");
        //tilesize = Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth())/Math.min(board.getHeight(), board.getWidth());

        //setting initial position
        reposition();

    }

    /**
     * function that calls drawBoard for the actual drawing of the board
     * the function is called from RoboRally.render()
     */
    public void render() {
        drawBoard();

    }

    /**
     * function that should resize the board
     * the function is called from RoboRally.resize()
     */
    public void resize(){
        camera.setToOrtho(false);
        batch.setProjectionMatrix(camera.combined);
        reposition();

        //TODO - implement resize logic (maybe not needed becaus of the batch.setProjectMatrix)
        if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth()){

        }else{

        }
    }


    /**
     * function that sets the position of the board.
     * is called from the constructor and resize();
     */
    public void reposition(){
        //TODO - implement repositioning logic
        yOffset = Gdx.graphics.getWidth()/2 - boardWidth/2;
        xOffset = Gdx.graphics.getHeight()/2 - boardHeight/2;
    }

    /**
     * function that draws the board using the spriteSheet and a double for-loop
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
