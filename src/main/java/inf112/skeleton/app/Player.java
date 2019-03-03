package inf112.skeleton.app;

import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.GUI.SpriteType;
import inf112.skeleton.app.Interfaces.IPlayer;


//TODO:fill hand with cards method
    //TODO:move forward method(ask board)
    //TODO:set backup method
    //TODO:move to backup method
    //TODO:keep track of burned down cards(and tell board)
    //TODO:Let player hold its own sprite/picture
    //TODO:Take damage method
    //TODO:Restore health method



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










}
