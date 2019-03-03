package inf112.skeleton.app;

import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.GUI.SpriteType;
import inf112.skeleton.app.Interfaces.IPlayer;

public class Player implements IPlayer {

    private SpriteType spriteType = SpriteType.PLAYER;
    private String playerID;
//    private ArrayList<Card> playerHand;
    private Board board;
    private int playerHealth = 5;


    private Direction playerDirection; //Direction the player is facing
    private int directionNumber = 0;  //number used to turn player around



    public Player(String playerID, Board board) {
        this.board = board;
        this.playerID = playerID;
    }


    /**
     * Turn the player around 'numberOfTurns' to the right.
     * Input negative numbers to turn left.
     * @param numberOfTurns to the right, negative number turns left.
     */
    public void turnPlayer(int numberOfTurns){

        directionNumber = (directionNumber + numberOfTurns) % 4;

        switch (directionNumber){

            case 0:  playerDirection = Direction.NORTH;
                break;

            case 1: playerDirection = Direction.EAST;
                break;

            case 2: playerDirection = Direction.SOUTH;
                break;

            case 3: playerDirection = Direction.WEST;
                break;

            default: playerDirection = Direction.NORTH;
                break;
        }
    }

    /**
     * Sets the players direction
     * @param direction to set the player
     */
    public void setPlayerDirection(Direction direction){

        if(direction == Direction.NORTH){
            directionNumber = 0;
            playerDirection = direction;

        }
        else if(direction == Direction.EAST){
            directionNumber = 1;
            playerDirection = direction;

        }
        else if(direction == Direction.SOUTH){
            directionNumber = 2;
            playerDirection = direction;

        }
        else if(direction == Direction.WEST){
            directionNumber = 3;
            playerDirection = direction;

        }

    }


    /**
     *
     * @return players Direction
     */
    public Direction getDirection() {
        return playerDirection;
    }

    @Override
    public Card[] getCardSequence() {
        return new Card[0];
    }

    @Override
    public void setCardSequence() {

    }

    @Override
    public void setCardHand() {

    }

    @Override
    public void sortCardSeqence() {

    }



   //TODO: should have a way to tell board if the player is dead
    @Override
    public void decreaseHealth() {
        playerHealth--;
    }

    @Override
    public void increaseHealth() {
        playerHealth++;
    }

    @Override
    public void setBackup() {

    }

    @Override
    public Position getBackup() {
        return null;
    }

    @Override
    public SpriteType getSpriteType() {
        return null;
    }
}
