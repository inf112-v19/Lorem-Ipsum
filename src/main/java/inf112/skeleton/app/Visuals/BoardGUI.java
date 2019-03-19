package inf112.skeleton.app.Visuals;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;


public class BoardGUI {

    private Board board;
	private SpriteSheet spriteSheet;

	private Stage stage;
	private FitViewport fitViewport;

	private int xOffset;
	private int yOffset;
	private int tilesize;
	private int boardWidth;
	private int boardHeight;
	private int boardTileWidth;
	private int boardTileHeight;

	private static final float MOVE_DURATION = 1;

    public BoardGUI(Board board, OrthographicCamera camera) {
        this.fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		this.stage = new Stage(fitViewport);

        this.spriteSheet = new SpriteSheet();
        this.board = board;

        this.boardWidth = board.getWidth();
        this.boardHeight = board.getHeight();

        this.tilesize = Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth())/(Math.min(boardWidth, boardHeight))-8;

        this.boardTileWidth = boardWidth *tilesize;
        this.boardTileHeight = boardHeight * tilesize;

        reposition();
        create();

    }

	/**
	 * Method that adds all players from the board to the stage
	 */
	private void addPlayersToStage(){
    	Player[] allPlayers = board.getAllPlayers();
    	for (Player player : allPlayers) {

    		player.setScaling(fitViewport.getScaling());
    		player.setDrawable(new TextureRegionDrawable(spriteSheet.getTexture(player)));

			player.setSize(tilesize,tilesize);
			player.setOrigin(player.getWidth()/2, player.getHeight()/2);
			player.setRotation(player.getDirection().directionToDegrees());

			Position pos = board.getPlayerPos(player);
			player.setPosition(pos.getX() * tilesize + xOffset, pos.getY() * tilesize + yOffset);

			stage.addActor(player);

		}
	}


    private void addTilesToStage(Tile tile, int x, int y){
		Image image = new Image(spriteSheet.getTexture(tile));
		image.setSize(tilesize,tilesize);
		image.setPosition(x,y);
		stage.addActor(image);
	}

	private void addGameObjectsOnTileToStage(Tile tile, int x, int y){
		Image image;
    	if (tile.hasAnyGameObjects()){
			GameObject[] gameObjects = tile.getGameObjects();
			for(int i = 0; i < tile.getGameObjects().length; i++){
				GameObject gameObject = gameObjects[i];
				image = new Image(spriteSheet.getTexture(gameObject));
				image.setSize(tilesize,tilesize);
				image.setPosition(x,y);
				stage.addActor(image);
			}
		}
	}

    private void create(){
		int xPos = 0;
		int yPos = 0;
		Position pos;

		for (int y = yOffset; y < yOffset + boardTileHeight; y+= tilesize){
			for (int x = xOffset; x < xOffset + boardTileWidth; x+= tilesize){
				pos = new Position(xPos, yPos);
				Tile curTile = board.getTile(pos);
				addTilesToStage(curTile,x, y);
				addGameObjectsOnTileToStage(curTile, x, y);
				xPos++;
			}
			xPos = 0;
			yPos++;
		}
		addPlayersToStage();
	}

	private void hideDeadPlayer(Player player){
		if (!player.onBoardCheck()){
			Action hide = Actions.fadeOut(0.1f);
			player.addAction(hide);
		}
	}

	public void hideDeadPlayers(){
		for (Player player : board.getAllPlayers()) {
			hideDeadPlayer(player);
		}
	}


	public void showRevivedPlayer(Player player){
		if (player.onBoardCheck()){
			Action show = Actions.fadeIn(0.1f);
			player.addAction(show);
		}
	}

	public void showRevivedPlayers(){
		for (Player player : board.getAllPlayers()) {
			showRevivedPlayer(player);
		}
	}

	private void reposition(){
		yOffset = 0;
		//yOffset = Gdx.graphics.getHeight()/2 - boardTileHeight/2;
		xOffset = Gdx.graphics.getWidth()/2 - boardTileWidth/2;
	}

	public void update(){
		for (Player player : board.getAllPlayers()) {
			Position pos = board.getPlayerPos(player);

			Action move = Actions.moveTo(pos.getX()*tilesize + xOffset, pos.getY()*tilesize + yOffset,MOVE_DURATION);
			RotateToAction rotate = Actions.rotateTo(player.getDirection().directionToDegrees(), MOVE_DURATION);
			rotate.setUseShortestDirection(true);
			player.addAction(rotate);
			player.addAction(move);

		}
	}

    /**
     * Method that calls drawBoard for the actual drawing of the board
     * the function is called from RoboRally.render()
     */
    public void render() {
		stage.act();//Gdx.graphics.getDeltaTime());
		stage.draw();
    }

    /**
     * Method that should resize the board
     * the function is called from RoboRally.resize()
     */
    public void resize(){
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void dispose(){
    	stage.dispose();
	}


}
