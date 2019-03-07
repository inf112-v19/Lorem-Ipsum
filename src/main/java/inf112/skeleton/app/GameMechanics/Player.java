package inf112.skeleton.app.GameMechanics;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.Interfaces.IPlayer;

import java.util.List;

public class Player implements IPlayer {

    private SpriteType spriteType = SpriteType.PLAYER;
    private String playerID;
    private List<Card> playerHand;
    private Card[] playerCardSequence;
    private int playerHealth = 10; //Number of damage the player can take before getting destroyed
    private int playerlives = 4; //Number for lives the player has before losing the game
    private Position backup;
    private boolean ready = false;


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
                playerDirection = Direction.NORTH;
                break;
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
    public int getLives(){return playerlives; }

    /**
     * decreses the players total lives
     */
    public void decreseLives() { playerlives--; }

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
     * destroy the player (lose a total life and set health to max)
     */
    public void destroyPlayer(){
        playerlives--;
        playerHealth = 10;
    }

    /**
     * Decrease the players health/damage by 1
     */
    @Override
    public void decreaseHealth() {
        playerHealth--;
        if(playerHealth<=0){
            playerHealth = 10;
            decreseLives();
        }
    }

    /**
     * Increase the players health/damage by 1
     */
    @Override
    public void increaseHealth() {
        playerHealth++;
        if(playerHealth>10) playerHealth = 10;

    }

    /**
     * @return returns the health/damage of the player
     */
    @Override
    public int getHealth() {
        return playerHealth;
    }

    @Override
    public void setBackup(Position backupPosition) { this.backup = backupPosition; }

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
}
