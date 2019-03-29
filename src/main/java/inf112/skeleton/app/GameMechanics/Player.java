package inf112.skeleton.app.GameMechanics;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.Interfaces.IPlayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Player extends Image implements IPlayer {

    private int index;
    private SpriteType spriteType;
    private String playerID;
    private List<Card> playerHand;
    private Card[] playerCardSequence;
    private int playerHealth = 10; //Number of damage the player can take before getting destroyed
    private int playerlives = 3; //Number for lives the player has before losing the game
    private Position backup;
    private boolean ready = false;
    private boolean isOnTheBoard = true;
    private ArrayList<Flag> collectedFlags = new ArrayList<>();

    private Direction playerDirection; //Direction the player is facing
    private int directionNumber = 0;  //number used to turn player around


    /**
     * Create a player object
     *
     * @param playerID
     * @param direction
     */
    public Player(String playerID, Direction direction) {
        this.playerID = playerID;
        setPlayerDirection(direction);
        spriteType = SpriteType.PLAYER1;
    }

    public Player(int index, String playerID, Direction direction) {
        this.index = index;
        this.playerID = playerID;
        setPlayerDirection(direction);
        assignSpriteType();
    }


    /**
     * Turn the player around 'numberOfTurns' to the right.
     * Input negative numbers to turn left.
     *
     * @param numberOfTurns to the right, negative number turns left.
     */
    public void turnPlayer(int numberOfTurns) {

        directionNumber = (directionNumber + numberOfTurns) % 4;
        if (directionNumber < 0) directionNumber += 4;


        switch (directionNumber) {
            case 0:
                playerDirection = Direction.NORTH;
                break;
            case 1:
                playerDirection = Direction.EAST;
                break;
            case 2:
                playerDirection = Direction.SOUTH;
                break;
            case 3:
                playerDirection = Direction.WEST;
                break;
            default:
                System.err.println("invalid directionnumber! direction not changed");
        }
    }


    /**
     * Sets the players direction
     *
     * @param direction to set the player
     */
    public void setPlayerDirection(Direction direction) {

        if (direction == Direction.NORTH) {
            directionNumber = 0;
            playerDirection = direction;

        } else if (direction == Direction.EAST) {
            directionNumber = 1;
            playerDirection = direction;

        } else if (direction == Direction.SOUTH) {
            directionNumber = 2;
            playerDirection = direction;

        } else if (direction == Direction.WEST) {
            directionNumber = 3;
            playerDirection = direction;
        }
    }

    /**
     * @return returns the total number of lives the player has before losing the game
     */
    public int getLives() {
        return playerlives;
    }

    /**
     * @return players Direction
     */
    public Direction getDirection() {
        return playerDirection;
    }


    @Override
    public Card[] getCardSequence() {
        return playerCardSequence;
    }

    @Override
    public void setCardSequence(Card[] cardSequence) {
        this.playerCardSequence = cardSequence;
    }

    @Override
    public void setCardHand(List<Card> cards) {
        playerHand = cards;
    }

    public List<Card> getCardHand() {
        return playerHand;
    }

    @Override
    public void sortCardSeqence() {

    }

    /**
     * destroy the player (lose a total life and set health to the right amount depending on number of lives lost) or if no more lives set health to 0
     * and remove player from the board
     */
    public void destroyPlayer() {
        playerlives--;

        if(playerlives >= 2){
            playerHealth = 8;
        }
        else if(playerlives == 1){
            playerHealth = 6;
        }
        else {
            playerHealth = 0;
            isOnTheBoard = false;
        }
    }

    /**
     * Decrease the players health/damage by 1
     */
    @Override
    public void decreaseHealth() {
        playerHealth--;
        if (playerHealth <= 0) {
            destroyPlayer();
        }
    }

    /**
     * Increase the players health/damage by 1
     */
    @Override
    public void increaseHealth() {
        playerHealth++;
        if (playerHealth > 10) playerHealth = 10;

    }

    /**
     * @return returns the health/damage of the player
     */
    @Override
    public int getHealth() {
        return playerHealth;
    }

    @Override
    public void setBackup(Position backupPosition) {
        this.backup = backupPosition;
    }

    @Override
    public Position getBackup() {
        return backup;
    }

    /**
     * @return returns the players current sprite
     */
    @Override
    public SpriteType getSpriteType() {
        return spriteType;
    }

    @Override
    public boolean isReady() {
        return ready;
    }

    @Override
    public void setReady() {
        ready = true;
    }

    public void setNotReady() {
        ready = false;
    }

    public String getPlayerID() {
        return playerID;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        return this.playerID.equals(((Player) obj).playerID);
    }

    @Override
    public int hashCode() {
        return this.index;
    }

    @Override
    public boolean onBoardCheck() {
        return isOnTheBoard;
    }

    @Override
    public void setOnTheBoard(Boolean isOnTheBoard) {
        this.isOnTheBoard = isOnTheBoard;
    }

    public int getIndex() {
        return index;
    }

    public boolean isDead() {
        return !(getLives() > 0);
    }

    /**
     * Method that checks if the flag is the next flag to be collected (index of the flag matches how many flags have
     * already been collected) and adds the flag to the collectedFlags HashSet.
     *
     * @param flag
     */
    public void collectFlag(Flag flag) {
        if (collectedFlags.size() == flag.getIndex()) {
            collectedFlags.add(flag);
            System.out.println(playerID + " collected flag number " + flag.getIndex());
			System.out.println(collectedFlags.size());
        }
    }

    public int numberOfFlagsCollected() {
        return collectedFlags.size();
    }

    private void assignSpriteType() {
        switch (index) {
            case 0:
                spriteType = SpriteType.PLAYER1;
                break;
            case 1:
                spriteType = SpriteType.PLAYER2;
                break;
            case 2:
                spriteType = SpriteType.PLAYER3;
                break;
            case 3:
                spriteType = SpriteType.PLAYER4;
                break;
            case 4:
                spriteType = SpriteType.PLAYER5;
                break;
            case 5:
                spriteType = SpriteType.PLAYER6;
                break;
            default:
                spriteType = SpriteType.PLAYER1;
        }
    }
}
