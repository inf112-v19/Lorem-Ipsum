package inf112.skeleton.app.Visuals;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Interfaces.IGameObject;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;


public class BoardGUI {

    private Board board;

    private int xOffset;
    private int yOffset;

    private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureRegion textureRegion;

	private SpriteSheet spriteSheet;

	private int tilesize;
	private int boardWidth;
	private int boardHeight;
	private int boardTileWidth;
	private int boardTileHeight;

    public BoardGUI(OrthographicCamera camera, SpriteBatch batch) {
        this.camera = camera;
        this.batch = batch;


        this.spriteSheet = new SpriteSheet();

        this.board = new Board("Boards/ExampleBoard.txt");
        this.boardWidth = board.getWidth();
        this.boardHeight = board.getHeight();

        this.tilesize = Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth())/(Math.min(boardWidth, boardHeight)*2);

        this.boardTileWidth = boardWidth *tilesize;
        this.boardTileHeight = boardHeight * tilesize;

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

    private void drawTile(Tile tile, int x, int y){
		SpriteType spriteType = tile.getSpriteType();
		textureRegion = spriteSheet.getTexture(spriteType);
		batch.draw(textureRegion, x, y, tilesize, tilesize);

	}


	private void drawGameObjects(Tile tile, int x, int y){
		if (tile.hasAnyGameObjects()){
			for(int i = 0; i < tile.getGameObjects().length; i++){
				IGameObject[] gameObjects = tile.getGameObjects();
				SpriteType gameObjectSpriteType = gameObjects[i].getSpriteType();
				textureRegion = spriteSheet.getTexture(gameObjectSpriteType);
				batch.draw(textureRegion, x, y, tilesize, tilesize);
			}
		}

	}

    /**
     * function that loops through the tiles of a Board and then calles the drawTiles() and drawGameObjects()
     *
     */
    public void drawBoard(){
        batch.begin();
        int xPos = 0;
        int yPos = 0;
        Position pos;

        for (int y = yOffset; y < yOffset + boardTileHeight; y+= tilesize){
            for (int x = xOffset; x < xOffset + boardTileWidth; x+= tilesize){
                pos = new Position(xPos, yPos);
                Tile curTile = board.getTile(pos);
                drawTile(curTile, x, y);
                drawGameObjects(curTile, x, y);
                xPos++;
            }
            xPos = 0;
            yPos++;
        }

        batch.end();
    }


}
