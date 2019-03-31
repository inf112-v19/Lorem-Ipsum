package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.assets.AssetManager;
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
public final class AssetHandler {

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

    private AssetManager manager;

    public AssetHandler() {
        manager = new AssetManager();
        manager.load("RoboRallyTiles.png", Texture.class);
        manager.load("Player/redtank.png", Texture.class);
        manager.load("CardImages/BackUp.png", Texture.class);
        manager.load("CardImages/LeftTurn.png", Texture.class);
        manager.load("CardImages/Move1.png", Texture.class);
        manager.load("CardImages/Move2.png", Texture.class);
        manager.load("CardImages/Move3.png", Texture.class);
        manager.load("CardImages/RightTurn.png", Texture.class);
        manager.load("CardImages/U-Turn.png", Texture.class);
        manager.load("StateImages/tapToStart.gif", Texture.class);
        manager.load("StateImages/tempBackground.jpg", Texture.class);
        manager.load("StateImages/start.png", Texture.class);
        manager.load("StateImages/secondBackground.png", Texture.class);
        manager.load("clear.png", Texture.class);
        manager.load("submit.png", Texture.class);
        manager.load("CardImages/cardBar.png", Texture.class);
        manager.load("SpawnTile.png", Texture.class);
        manager.load("clear_press.png", Texture.class);
        manager.load("submit_press.png", Texture.class);
        manager.load("lock.png", Texture.class);
        manager.load("CardImages/button1.png", Texture.class);
        manager.load("CardImages/button2.png", Texture.class);
        manager.load("CardImages/button3.png", Texture.class);
        manager.load("CardImages/button4.png", Texture.class);
        manager.load("CardImages/button5.png", Texture.class);
        manager.load("healthbar.png", Texture.class);
        manager.load("heart.png", Texture.class);
        //manager.load("", Texture.class);

        manager.update();
        manager.finishLoading();

        this.texture = manager.get("RoboRallyTiles.png", Texture.class);
        this.spriteSheet = new TextureRegion(texture, 336, 624).split(336 / 7, 624 / 13);
        flip(this.spriteSheet);

        this.texture = manager.get("Player/redtank.png", Texture.class);
        this.redtankSheet = new TextureRegion(this.texture, 224, 128).split(224 / 7, 128 / 4);
        flip(this.redtankSheet);

        this.texture = manager.get("CardImages/BackUp.png", Texture.class);
        this.backUpSprite = new TextureRegion(texture);
        this.backUpSprite.flip(false, true);

        this.texture = manager.get("CardImages/LeftTurn.png", Texture.class);
        this.leftTurnSprite = new TextureRegion(texture);
        this.leftTurnSprite.flip(false, true);

        this.texture = manager.get("CardImages/Move1.png", Texture.class);
        this.move1Sprite = new TextureRegion(texture);
        this.move1Sprite.flip(false, true);

        this.texture = manager.get("CardImages/Move2.png", Texture.class);
        this.move2Sprite = new TextureRegion(texture);
        this.move2Sprite.flip(false, true);

        this.texture = manager.get("CardImages/Move3.png", Texture.class);
        this.move3Sprite = new TextureRegion(texture);
        this.move3Sprite.flip(false, true);

        this.texture = manager.get("CardImages/RightTurn.png", Texture.class);
        this.rightTurnSprite = new TextureRegion(texture);
        this.rightTurnSprite.flip(false, true);

        this.texture = manager.get("CardImages/U-Turn.png", Texture.class);
        this.uTurnSprite = new TextureRegion(texture);
        this.uTurnSprite.flip(false, true);

        this.texture = manager.get("StateImages/tapToStart.gif", Texture.class);
        this.tapToStartSprite = new TextureRegion(texture);
        //this.tapToStartSprite.flip(false, true);

        this.texture = manager.get("StateImages/tempBackground.jpg", Texture.class);
        this.menuBackground = new TextureRegion(texture);
        //this.menuBackground.flip(false, true);

        this.texture = manager.get("StateImages/start.png", Texture.class);
        this.menuPlayButton = new TextureRegion(texture);
        //this.menuPlayButton.flip(false, true);

        this.texture = manager.get("StateImages/secondBackground.png", Texture.class);
        this.chooseBackground = new TextureRegion(texture);

        this.texture = manager.get("clear.png", Texture.class);
        this.cardGuiClearButton = new TextureRegion(texture);
        this.cardGuiClearButton.flip(false, true);

        this.texture = manager.get("submit.png", Texture.class);
        this.cardGuiSubmitButton = new TextureRegion(texture);
        this.cardGuiSubmitButton.flip(false, true);

        this.texture = manager.get("CardImages/cardBar.png", Texture.class);
        this.cardBar = new TextureRegion(texture);
        this.cardBar.flip(false, true);

        this.texture = manager.get("SpawnTile.png", Texture.class);
        this.spawnTile = new TextureRegion(this.texture);
        this.spawnTile.flip(false, true);


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


    ///NB!!!!!! will crash program if file is not loaded!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public Texture getTexture(String filename) {
        return manager.get(filename, Texture.class);
    }

    public void dispose() {
        this.texture.dispose();
        manager.dispose();
    }
}
