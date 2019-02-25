package inf112.skeleton.app;

import java.util.ArrayList;


    //TODO:fill hand with cards method
    //TODO:move forward method(ask board)
    //TODO:set backup method
    //TODO:move to backup method
    //TODO:keep track of burned down cards(and tell board)
    //TODO:Let player hold its own sprite/picture
    //TODO:Take damage method
    //TODO:Restore health method
    //


public class Player {

    private Position playerPostition;
    private String playerID;
//    private ArrayList<Card> playerHand;
    private Board board;
    private int playerHealth = 5;

    String playerDirection; //Direction the player is facing
    private int direction = 0;  //number used to turn player around



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

        direction = (direction + numberOfTurns) % 4;

        switch (direction){

            case 0:  playerDirection = "North";
                break;

            case 1: playerDirection = "East";
                break;

            case 2: playerDirection = "South";
                break;

            case 3: playerDirection = "West";
                break;

            default: playerDirection = "North";
                break;
        }
    }

    /**
     * Tries to move the player 'numberOfSteps' in the direction the player is facing
     * @param numberOfSteps to move
     */
    public void moveForward(int numberOfSteps) {

        for (int i = 0; i < numberOfSteps; i++) {

            board.movePlayer(playerID, playerDirection);
        }
    }

}
