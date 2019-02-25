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
import inf112.skeleton.app.Position;
import inf112.skeleton.app.Tile;

import java.io.IOException;

import static com.badlogic.gdx.Gdx.gl;


public class BoardGUI {

    Board board;
    int xOffset;
    int yOffset;
    OrthographicCamera camera;
    SpriteBatch batch;


    int tilesize;
    int boardWidth;
    int boardHeight;
    int boardTileWidth;
    int boardTileHeight;

    Texture texture = new Texture("RoboRallyTiles.png");
    TextureRegion[][] spriteSheet = new TextureRegion(texture,336,624).split(336/7, 624/13);


    public BoardGUI(OrthographicCamera camera, SpriteBatch batch) {
        this.camera = camera;
        this.batch = batch;

        board = new Board("BoardBuilderTest2.txt");
        boardWidth = board.getWidth();
        boardHeight = board.getHeight();

        tilesize = Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth())/(Math.min(boardWidth, boardHeight)*5);

        boardTileWidth = boardWidth *tilesize;
        boardTileHeight = boardHeight * tilesize;

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
        yOffset = Gdx.graphics.getHeight()/2 - boardTileHeight/2;
        xOffset = Gdx.graphics.getWidth()/2 - boardTileWidth/2;
    }

    /**
     * function that draws the board using the spriteSheet and a double for-loop
     *
     */
    //TODO - should work width different tiles
    public void drawBoard(){
        batch.begin();
        int xPos = 1;
        int yPos = 1;

        for (int y = yOffset; y < yOffset + boardTileHeight; y+= tilesize){
            for (int x = xOffset; x < xOffset + boardTileWidth; x+= tilesize){
                Position pos = new Position(xPos, yPos);
                Tile curTile = board.getTile(pos);
                int spriteX = curTile.getSpriteX();
                int spriteY = curTile.getSpriteY();
                TextureRegion tileSprite = spriteSheet[spriteX][spriteY];
                batch.draw(tileSprite, x, y, tilesize, tilesize);
                xPos++;
            }
            yPos++;
        }

        batch.end();
    }


}
