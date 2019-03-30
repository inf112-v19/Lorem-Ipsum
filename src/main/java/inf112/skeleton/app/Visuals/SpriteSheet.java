package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;

import javax.xml.soap.Text;

/**
 * This class handles all the sprites used in the game
 */
public final class SpriteSheet {

    private Texture texture;

    //CardHandGUI
    private final TextureRegion cardGuiClearButton;
    private final TextureRegion cardGuiSubmitButton;
    private final TextureRegion cardBar;

    //Div Sprites
    private final TextureRegion tapToStartSprite;

    //Player spritesheet
    private final TextureRegion[][] redtankSheet;

    //Menu
    private final TextureRegion menuBackground;
    private final TextureRegion menuPlayButton;

    private final TextureRegion chooseBackground;

    //Card Sprites
    private final TextureRegion backUpSprite;
    private final TextureRegion leftTurnSprite;
    private final TextureRegion move1Sprite;
    private final TextureRegion move2Sprite;
    private final TextureRegion move3Sprite;
    private final TextureRegion rightTurnSprite;
    private final TextureRegion uTurnSprite;

    //The spriteSheet
    private final TextureRegion[][] spriteSheet;

    private final TextureRegion spawnTile;


    public SpriteSheet() {

        this.texture = new Texture("RoboRallyTiles.png");
        this.spriteSheet = new TextureRegion(texture, 336, 624).split(336 / 7, 624 / 13);
        spriteSheet
        flip(this.spriteSheet);
        dispose();

        this.texture = new Texture("Player/redtank.png");
        this.redtankSheet = new TextureRegion(this.texture, 224, 128).split(224 / 7, 128 / 4);
        flip(this.redtankSheet);
        dispose();

        this.texture = new Texture("CardImages/BackUp.png");
        this.backUpSprite = new TextureRegion(texture);
        this.backUpSprite.flip(false, true);
        dispose();

        this.texture = new Texture("CardImages/LeftTurn.png");
        this.leftTurnSprite = new TextureRegion(texture);
        this.leftTurnSprite.flip(false, true);
        dispose();

        this.texture = new Texture("CardImages/Move1.png");
        this.move1Sprite = new TextureRegion(texture);
        this.move1Sprite.flip(false, true);
        dispose();

        this.texture = new Texture("CardImages/Move2.png");
        this.move2Sprite = new TextureRegion(texture);
        this.move2Sprite.flip(false, true);
        dispose();

        this.texture = new Texture("CardImages/Move3.png");
        this.move3Sprite = new TextureRegion(texture);
        this.move3Sprite.flip(false, true);
        dispose();

        this.texture = new Texture("CardImages/RightTurn.png");
        this.rightTurnSprite = new TextureRegion(texture);
        this.rightTurnSprite.flip(false, true);
        dispose();

        this.texture = new Texture("CardImages/U-Turn.png");
        this.uTurnSprite = new TextureRegion(texture);
        this.uTurnSprite.flip(false, true);
        dispose();

        this.texture = new Texture("StateImages/tapToStart.gif");
        this.tapToStartSprite = new TextureRegion(texture);
        //this.tapToStartSprite.flip(false, true);
        dispose();

        this.texture = new Texture("StateImages/tempBackground.jpg");
        this.menuBackground = new TextureRegion(texture);
        //this.menuBackground.flip(false, true);
        dispose();

        this.texture = new Texture("StateImages/start.png");
        this.menuPlayButton = new TextureRegion(texture);
        //this.menuPlayButton.flip(false, true);
        dispose();

        this.texture = new Texture("StateImages/secondBackground.png");
        this.chooseBackground = new TextureRegion(texture);
        dispose();

        this.texture = new Texture("clear.png");
        this.cardGuiClearButton = new TextureRegion(texture);
        this.cardGuiClearButton.flip(false, true);
        dispose();

        this.texture = new Texture("submit.png");
        this.cardGuiSubmitButton = new TextureRegion(texture);
        this.cardGuiSubmitButton.flip(false, true);
        dispose();

        this.texture = new Texture("CardImages/cardBar.png");
        this.cardBar = new TextureRegion(texture);
        this.cardBar.flip(false, true);
        dispose();

        this.texture = new Texture("SpawnTile.png");
        this.spawnTile = new TextureRegion(this.texture);
        this.spawnTile.flip(false,true);
        dispose();



    }


    /**
     * Private method that flips the textures in the spriteSheet.
     * This is necessary because 0,0 is top left and therefore the sprites are drawn
     * upside down. Flipping the textures is a fix for this
     */
    private void flip(TextureRegion[][] sheet) {
        for (int i = 0; i < sheet.length; i++) {
            for (int j = 0; j < sheet[i].length; j++) {
                sheet[i][j].flip(false, true);
            }
        }
    }

    /**
     * Private method that finds the correct texture for the given SpriteType
     *
     * @param spriteType
     * @return TextureRegion for a given SpriteType
     */
    private TextureRegion findCorrectTexture(SpriteType spriteType) {
        if (spriteType.isUsingCoordinates()) {
            return spriteSheet[spriteType.getY()][spriteType.getX()];
        }
        switch (spriteType) {
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
            case MENU_BACKGROUND:
                return menuBackground;
            case MENU_PLAY_BUTTON:
                return menuPlayButton;
            case CHOOSE_BACKGROUND:
                return chooseBackground;
            case CARD_SUBMIT:
                return cardGuiSubmitButton;
            case CARD_CLEAR:
                return cardGuiClearButton;
            case CARD_BAR:
                return cardBar;
            case PLAYER1:
                return redtankSheet[0][0];
            case PLAYER2:
                return redtankSheet[0][1];
            case PLAYER3:
                return redtankSheet[0][2];
            case PLAYER4:
                return redtankSheet[0][3];
            case PLAYER5:
                return redtankSheet[0][4];
            case PLAYER6:
                return redtankSheet[0][5];
			case SPAWN_TILE:
				return spawnTile;
            default:
                System.err.println("No sprite found");
                return null;
        }
    }


    /**
     * method that return correct sprite for a given player and it's direction
     *
     * @param player
     * @return TextureRegion for a given Player
     */
    public TextureRegion getTexture(Player player) {
        return findCorrectTexture(player.getSpriteType());
    }

    /**
     * Method that return correct sprite for a given SpriteType.
     *
     * @param spriteType
     * @return TextureRegion for a given SpriteType
     */
    public TextureRegion getTexture(SpriteType spriteType) {
        return findCorrectTexture(spriteType);
    }

    /**
     * Method that return correct sprite for a given Tile
     *
     * @param card
     * @return TextureRegion for the given Tile
     */
    public TextureRegion getTexture(Card card) {
        return findCorrectTexture(card.getSprite());
    }

    /**
     * Method that return correct sprite for a given Tile
     *
     * @param tile
     * @return TextureRegion for the given GameObject
     */
    public TextureRegion getTexture(Tile tile) {
        return findCorrectTexture(tile.getSpriteType());
    }

    /**
     * Method that return correct sprite for a given GameObject.
     *
     * @param gameObject
     * @return TextureRegion for the given GameObject
     */
    public TextureRegion getTexture(GameObject gameObject) {
        return findCorrectTexture(gameObject.getSpriteType());
    }

    public void dispose() {
        this.texture.dispose();
    }
}
