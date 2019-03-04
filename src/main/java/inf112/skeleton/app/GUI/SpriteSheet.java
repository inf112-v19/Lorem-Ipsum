package inf112.skeleton.app.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;

/**
 * This class handles all the sprites used in the game
 */
public final class SpriteSheet {

	private Texture texture;

	//Div Sprites
	private static TextureRegion tapToStartSprite;
	private static TextureRegion clickAnywhereSprite;
	private static TextureRegion playerSprite;

	//Menu
	private static TextureRegion menuBackground;
	private static TextureRegion menuPlayButton;

	//Card Sprites
	private static TextureRegion backUpSprite;
	private static TextureRegion leftTurnSprite;
	private static TextureRegion move1Sprite;
	private static TextureRegion move2Sprite;
	private static TextureRegion move3Sprite;
	private static TextureRegion rightTurnSprite;
	private static TextureRegion uTurnSprite;

	//The spriteSheet
	private static TextureRegion[][] spriteSheet;



	public SpriteSheet(){

		this.texture = new Texture("RoboRallyTiles.png");
		this.spriteSheet = new TextureRegion(texture,336,624).split(336/7, 624/13);
		flip();

		this.texture = new Texture("CardImages/BackUp.png");
		this.backUpSprite = new TextureRegion(texture);
		this.backUpSprite.flip(false, true);

		this.texture = new Texture("CardImages/LeftTurn.png");
		this.leftTurnSprite = new TextureRegion(texture);
		this.leftTurnSprite.flip(false, true);

		this.texture = new Texture("CardImages/Move1.png");
		this.move1Sprite = new TextureRegion(texture);
		this.move1Sprite.flip(false, true);

		this.texture = new Texture("CardImages/Move2.png");
		this.move2Sprite = new TextureRegion(texture);
		this.move2Sprite.flip(false, true);

		this.texture = new Texture("CardImages/Move3.png");
		this.move3Sprite = new TextureRegion(texture);
		this.move3Sprite.flip(false, true);

		this.texture = new Texture("CardImages/RightTurn.png");
		this.rightTurnSprite = new TextureRegion(texture);
		this.rightTurnSprite.flip(false, true);

		this.texture = new Texture("CardImages/U-Turn.png");
		this.uTurnSprite = new TextureRegion(texture);
		this.uTurnSprite.flip(false, true);

		this.texture = new Texture("tapToStart.gif");
		this.tapToStartSprite = new TextureRegion(texture);
		this.tapToStartSprite.flip(false, true);

		this.texture = new Texture("clickAnywhere.png");
		this.clickAnywhereSprite = new TextureRegion(texture);
		this.clickAnywhereSprite.flip(false, true);

		this.texture = new Texture("player.png");
		this.playerSprite = new TextureRegion(texture);
		this.playerSprite.flip(false,true);

		this.texture = new Texture("tempStart.jpg");
		this.menuBackground = new TextureRegion(texture);
		this.menuBackground.flip(false, true);

		this.texture = new Texture("tapToStart.gif");
		this.menuPlayButton = new TextureRegion(texture);
		this.menuPlayButton.flip(false, true);


	}


	/**
	 * Private method that flips the textures in the spriteSheet.
	 * This is necessary because 0,0 is top left and therefore the sprites are drawn
	 * upside down. Flipping the textures is a fix fore this
	 */
	private void flip(){
		for (int i = 0; i < spriteSheet.length; i++){
			for(int j = 0; j < spriteSheet[i].length; j++){
				spriteSheet[i][j].flip(false, true);
			}
		}
	}

	/**
	 * Private method that finds the correct texture for the given SpriteType
	 * @param spriteType
	 * @return TextureRegion for a given SpriteType
	 */
	private TextureRegion findCorrectTexture(SpriteType spriteType){
		if(spriteType.isUsingCoordinates()){
			return spriteSheet[spriteType.getY()][spriteType.getX()];
		}
		switch (spriteType){
			case BACKWARD_1:
				return backUpSprite;
			case FORWARD_1:
				return move1Sprite;
			case FORWARD_2:
				return move2Sprite;
			case FORWARD_3:
				return move3Sprite;
			case ROTATE_180:
				return uTurnSprite;
			case ROTATE_90_L:
				return leftTurnSprite;
			case ROTATE_90_R:
				return rightTurnSprite;
			case PLAYER:
				return playerSprite;
			case MENU_BACKGROUND:
				return menuBackground;
			case MENU_PLAY_BUTTON:
				return menuPlayButton;
			default:
				System.err.println("No sprite found");
				return null;
		}
	}


	/**
	 * Method that return correct sprite for a given SpriteType.
	 * @param spriteType
	 * @return TextureRegion for a given SpriteType
	 */
	public TextureRegion getTexture(SpriteType spriteType){
		return findCorrectTexture(spriteType);
	}

	/**
	 * Method that return correct sprite for a given Tile
	 * @param card
	 * @return TextureRegion for the given Tile
	 */
	public TextureRegion getTexture(Card card){
		return findCorrectTexture(card.getSprite());
	}

	/**
	 * Method that return correct sprite for a given Tile
	 * @param tile
	 * @return TextureRegion for the given GameObject
	 */
	public TextureRegion getTexture(Tile tile){
		return findCorrectTexture(tile.getSpriteType());
	}

	/**
	 * Method that return correct sprite for a given GameObject.
	 * @param gameObject
	 * @return TextureRegion for the given GameObject
	 */
	public TextureRegion getTexture(GameObject gameObject){
		return findCorrectTexture(gameObject.getSpriteType());
	}

	public void dispose(){
		this.texture.dispose();
	}



}
