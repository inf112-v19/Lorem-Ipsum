package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Board.BoardBuilder;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardType;
import inf112.skeleton.app.GameObjects.GameObject;
import inf112.skeleton.app.Tiles.Tile;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

public final class SpriteSheet extends Sprite {

	private Texture texture;

	//Div Sprites
	private static TextureRegion tapToStartSprite;
	private static TextureRegion clickAnywhereSprite;

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

	boolean useSpriteSheet;


	public SpriteSheet(){
		this.useSpriteSheet = true;

		this.texture = new Texture("RoboRallyTiles.png");
		this.spriteSheet = new TextureRegion(texture,336,624).split(336/7, 624/13);
		flip();

		/*
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

		*/

	}


	private void flip(){
		for (int i = 0; i < spriteSheet.length; i++){
			for(int j = 0; j < spriteSheet[i].length; j++){
				spriteSheet[i][j].flip(false, true);
			}
		}
	}


	public TextureRegion getTexture(SpriteType spriteType){
		if (spriteType.isUsingCoordinates()) {
			return spriteSheet[spriteType.getY()][spriteType.getX()];
		}
		Texture tex = new Texture(spriteType.getFilename());
		return new TextureRegion(tex);
	}

	public TextureRegion getTexture(Card card){
		String filename = card.getSprite().getFilename();
		Texture texture = new Texture(filename);
		return new TextureRegion(texture);
	}

	public TextureRegion getTexture(Tile tile){
		return spriteSheet[tile.getSpriteType().getY()][tile.getSpriteType().getX()];
	}

	public TextureRegion getTexture(GameObject gameObject){
		return spriteSheet[gameObject.getSpriteType().getY()][gameObject.getSpriteType().getX()];
	}



}
