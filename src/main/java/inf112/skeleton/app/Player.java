package inf112.skeleton.app;

import java.util.ArrayList;

public class Player {

    private Position playerPostition;
    private String playerID;
    private ArrayList<Card> playerHand;

    public Player(Position playerPosition, String playerID){
        this.playerID = playerID;
        this.playerPostition = playerPosition;
    }

  //  public class Card{}
}
