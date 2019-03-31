package inf112.skeleton.app.Visuals;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import inf112.skeleton.app.Visuals.States.GameStateManager;


public class BoardGUI {

	private GameStateManager gsm;

    private Board board;
	private AssetHandler assetHandler;

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

    public BoardGUI(Board board, OrthographicCamera camera, Stage stage, GameStateManager gsm, AssetHandler assetHandler) {
    	this.gsm = gsm;

    	this.fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		this.stage = stage;

        this.assetHandler = assetHandler;
        this.board = board;

        this.boardWidth = board.getWidth();
        this.boardHeight = board.getHeight();

        this.tilesize = Math.min(RoboRally.HEIGHT, RoboRally.WIDTH)/(Math.min(boardWidth, boardHeight))-8;

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
    		player.setDrawable(new TextureRegionDrawable(assetHandler.getTexture(player)));

			player.setSize(tilesize,tilesize);
			player.setOrigin(player.getWidth()/2, player.getHeight()/2);
			player.setRotation(player.getDirection().directionToDegrees());

			Position pos = board.getPlayerPos(player);
			player.setPosition(pos.getX() * tilesize + xOffset, pos.getY() * tilesize + yOffset);

			stage.addActor(player);
		}
	}

	private void addGameObjectToStage(GameObject gameObject, float x, float y){
		gameObject.setDrawable(new TextureRegionDrawable(assetHandler.getTexture(gameObject)));
		gameObject.setSize(tilesize,tilesize);
		gameObject.setPosition(x,y);
		stage.addActor(gameObject);
	}


    private void addTilesToStage(final Tile tile, int x, int y){
		tile.setDrawable(new TextureRegionDrawable(assetHandler.getTexture(tile)));
		tile.setSize(tilesize,tilesize);
		tile.setPosition(x,y);
		//tile.addListener(createListener(tile));
		stage.addActor(tile);
	}

	private void addGameObjectsOnTileToStage(final Tile tile, int x, int y){
    	if (tile.hasAnyGameObjects()){
			GameObject[] gameObjects = tile.getGameObjects();
			for(int i = 0; i < tile.getGameObjects().length; i++){
				GameObject gameObject = gameObjects[i];
				//gameObject.addListener(createListener(tile));
				addGameObjectToStage(gameObject, x, y);
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
		//stage.act(Gdx.graphics.getDeltaTime());
		//stage.draw();
    }

    /**
     * Method that should resize the board
     * the function is called from RoboRally.resize()
     */
    public void resize(){
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    public void removeAllListeners(){
		for (Actor actor: stage.getActors()) {
			removeListener(actor);
		}
	}

	public void removeListener(Actor actor){
		actor.clearListeners();
	}

	public InputListener createListener(final Tile tile){
		return new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				gsm.tileEventHandle(tile);
				return true;
			}
		};
	}

	public void addListenersToStage(){
		for (Actor actor : stage.getActors()) {
			if (actor instanceof Tile){
				Tile tile = (Tile)actor;
				tile.addListener(createListener(tile));
				for(GameObject gameObject : tile.getGameObjects()){
					gameObject.addListener(createListener(tile));
				}

			}
		}
	}

	public int getxOffset(){
    	return xOffset;
	}

	public int getyOffset(){
    	return yOffset;
	}

}
