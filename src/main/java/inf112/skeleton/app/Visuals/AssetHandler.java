package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;

/**
 * This class handles all the sprites used in the game
 */
public final class AssetHandler {
    private Texture texture;

    //Player spritesheet
    private final TextureRegion[][] redtankSheet;

    //Card Sprites
    private final TextureRegion backUpSprite;
    private final TextureRegion leftTurnSprite;
    private final TextureRegion move1Sprite;
    private final TextureRegion move2Sprite;
    private final TextureRegion move3Sprite;
    private final TextureRegion rightTurnSprite;
    private final TextureRegion uTurnSprite;

    private final TextureRegion[][] spriteSheet;

    private final TextureRegion spawnTile;

    private AssetManager manager;

    public AssetHandler() {
        manager = new AssetManager();
        manager.load("uiskin.json", Skin.class);

        manager.load("RoboRallyTiles.png", Texture.class);
        manager.load("Player/redtank.png", Texture.class);
        manager.load("CardImages/BackUp.png", Texture.class);
        manager.load("CardImages/LeftTurn.png", Texture.class);
        manager.load("CardImages/Move1.png", Texture.class);
        manager.load("CardImages/Move2.png", Texture.class);
        manager.load("CardImages/Move3.png", Texture.class);
        manager.load("CardImages/RightTurn.png", Texture.class);
        manager.load("CardImages/U-Turn.png", Texture.class);
        manager.load("SpawnTile.png", Texture.class);
        manager.load("lock.png", Texture.class);
        manager.load("CardImages/button1.png", Texture.class);
        manager.load("CardImages/button2.png", Texture.class);
        manager.load("CardImages/button3.png", Texture.class);
        manager.load("CardImages/button4.png", Texture.class);
        manager.load("CardImages/button5.png", Texture.class);
        manager.load("damageBar.png", Texture.class);
        manager.load("emptyDamageBar.png", Texture.class);
        manager.load("heart.png", Texture.class);

        //stateimages
        manager.load("StateImages/board.png", Texture.class);
        manager.load("StateImages/board1.png", Texture.class);
        manager.load("StateImages/board2.png", Texture.class);
        manager.load("StateImages/board3.png", Texture.class);
        manager.load("StateImages/chooseBoardType.png", Texture.class);
        manager.load("StateImages/choosePlayerAmount.png", Texture.class);
        manager.load("StateImages/exit.png", Texture.class);
        manager.load("StateImages/gameOver.png", Texture.class);
        manager.load("StateImages/mainMenu.png", Texture.class);
        manager.load("StateImages/no1.png", Texture.class);
        manager.load("StateImages/no2.png", Texture.class);
        manager.load("StateImages/no3.png", Texture.class);
        manager.load("StateImages/no4.png", Texture.class);
        manager.load("StateImages/no5.png", Texture.class);
        manager.load("StateImages/no6.png", Texture.class);
        manager.load("StateImages/resume.png", Texture.class);
        manager.load("StateImages/secondBackground.png", Texture.class);
        manager.load("StateImages/start.png", Texture.class);
        manager.load("StateImages/menuBackground.jpg", Texture.class);

        manager.update();
        manager.finishLoading();

        this.texture = manager.get("RoboRallyTiles.png", Texture.class);
        this.spriteSheet = new TextureRegion(texture, 336, 624).split(336 / 7, 624 / 13);

        this.texture = manager.get("Player/redtank.png", Texture.class);
        this.redtankSheet = new TextureRegion(this.texture, 224, 128).split(224 / 7, 128 / 4);

        this.texture = manager.get("CardImages/BackUp.png", Texture.class);
        this.backUpSprite = new TextureRegion(texture);

        this.texture = manager.get("CardImages/LeftTurn.png", Texture.class);
        this.leftTurnSprite = new TextureRegion(texture);

        this.texture = manager.get("CardImages/Move1.png", Texture.class);
        this.move1Sprite = new TextureRegion(texture);

        this.texture = manager.get("CardImages/Move2.png", Texture.class);
        this.move2Sprite = new TextureRegion(texture);

        this.texture = manager.get("CardImages/Move3.png", Texture.class);
        this.move3Sprite = new TextureRegion(texture);

        this.texture = manager.get("CardImages/RightTurn.png", Texture.class);
        this.rightTurnSprite = new TextureRegion(texture);

        this.texture = manager.get("CardImages/U-Turn.png", Texture.class);
        this.uTurnSprite = new TextureRegion(texture);

        this.texture = manager.get("SpawnTile.png", Texture.class);
        this.spawnTile = new TextureRegion(this.texture);
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

    public Texture getTexture(String filename) {
        try {
            return manager.get(filename, Texture.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TextureRegion getTextureRegion(String filename) {
        TextureRegion t = new TextureRegion(manager.get(filename, Texture.class));
        return t;
    }

    public Skin getSkin() {
        return manager.get("uiskin.json");
    }

    public void dispose() {
        this.texture.dispose();
        manager.dispose();
    }
}
