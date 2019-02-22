package inf112.skeleton.app;

import java.util.ArrayList;

public class Player {

    private Position playerPostition;
    private String playerID;
//    private ArrayList<Card> playerHand;
    private Board board;


    public Player(Position playerPosition, String playerID, Board board){
        this.playerID = playerID;
        this.playerPostition = playerPosition;
        this.board = board;
    }


}
