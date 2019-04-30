package inf112.skeleton.app.GameMechanics;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;

import java.util.*;

/**
 * Class used to find better than random cards for an AI player
 */
public class AiSimulation {
    private Player originalPlayer;
    private Board originalBoard;
    private List<Card> cards;
    private Tile nextFlagTile;
    private int maxFlags;
    private CardManager cardManager;

    /**
     * Compares tiles with a flag, returns 1 if tile1 has a flag with lower index than flag2
     * returns 0 if tile2 has a flag with lower index than tile1
     */
    private Comparator<Tile> flagTileComparator = new Comparator<Tile>() {
        @Override
        public int compare(Tile tile1, Tile tile2) {
            if(getFlag(tile1).getIndex() < getFlag(tile2).getIndex()){
                return 1;
            }
            else {
                return 0;
            }
        }
        private Flag getFlag(Tile tile){
            for(GameObject object : tile.getGameObjects()){
                if(object instanceof Flag) return (Flag)object;
            }
            return null;
        }
    };

    private PriorityQueue<Tile> flagTiles = new PriorityQueue<>(6,flagTileComparator);

    public AiSimulation(Board board, Player player, CardManager cardManager){
        this.originalBoard = board;
        this.originalPlayer = player;
        this.cardManager = cardManager;

        HashMap<Position, Tile> tiles = originalBoard.getTileMap();
        for (Tile t : tiles.values()){
            GameObject[] objectsOnTile = t.getGameObjects();
            for (GameObject object : objectsOnTile) {
                if(object instanceof Flag) flagTiles.add(t);
            }
        }
        maxFlags = flagTiles.size();
        nextFlagTile = flagTiles.poll();
    }

    public Card[] findBestCards(){
        checkNextFlag();
        cards = originalPlayer.getCardHand();

        for(int i=0;i<cards.size();i++){

        }
        Board testBoard = originalBoard;
        Player testPlayer = originalPlayer;


        return null;
    }

    private void checkNextFlag(){
        while((originalPlayer.numberOfFlagsCollected() + flagTiles.size() + 1) > maxFlags){
            nextFlagTile = flagTiles.poll();
        }
    }

}
