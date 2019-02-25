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


public class Player extends GameObject {

    private Position playerPostition;
    private String playerID;
//    private ArrayList<Card> playerHand;
    private Board board;
    private int playerHealth = 5;

    String playerDirection; //Direction the player is facing
    private int direction = 0;  //number used to turn player around



    public Player(Position playerPosition, Board board) {
        super(playerPosition, board);
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

    public moveForward(){
        if(playerDirection == "North"){
            
        }
    }

}
