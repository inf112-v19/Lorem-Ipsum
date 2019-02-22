package inf112.skeleton.app;

import java.util.ArrayList;

public class Player extends GameObject {

    private Position playerPostition;
    private String playerID;
//    private ArrayList<Card> playerHand;
    private Board board;

    String playerDirection; //Direction the player is facing
    private int direction = 0;  //number used to turn player around



    public Player(Position playerPosition, String playerID, Board board) {
        super(playerPosition, playerID, board);
    }


    /**
     * Turn the player around 'numberOfTurns' to the right.
     * Input negative numbers to turn left.
     * @param numberOfTurns to the right, negative number turns left.
     */
    public void turnPlayer(int numberOfTurns){

        direktion = (direktion + numberOfTurns) % 4;

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

    public moveX(){

    }

}
