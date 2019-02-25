package inf112.skeleton.app;

import inf112.skeleton.app.GUI.Board;

import java.util.ArrayList;


    //TODO:fill hand with cards method
    //TODO:move forward method(ask board)
    //TODO:set backup method
    //TODO:move to backup method
    //TODO:keep track of burned down cards(and tell board)
    //TODO:Let player hold its own sprite/picture
    //TODO:Take damage method
    //TODO:Restore health method



public class Player {


    private String playerID;
//    private ArrayList<Card> playerHand;
    private Board board;
    private int playerHealth = 5;

    String playerDirection; //Direction the player is facing
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
     * Set players direction
     * @param direction should be "North", "East", "South" or "West"
     * @return returns 1 for success or -1 for failed direction change
     */
    public int setPlayerDirection(String direction){

        if(direction == "North"){
            directionNumber = 0;
            playerDirection = direction;

        }
        else if(direction == "East"){
            directionNumber = 1;
            playerDirection = direction;

        }
        else if(direction == "South"){
            directionNumber = 2;
            playerDirection = direction;

        }
        else if(direction == "West"){
            directionNumber = 3;
            playerDirection = direction;

        }
        else{
            return -1;
        }
        return 1
    }


//TODO:add a way to move backwards and tell board what way player is facing

    /**
     * Tries to move the player 'numberOfSteps' in the direction the player is facing
     * @param numberOfSteps to move
     */
    public void moveForward(int numberOfSteps) {
        for (int i = 0; i < numberOfSteps; i++) {

            board.movePlayer(playerID, playerDirection);
        }
    }
    */




}
