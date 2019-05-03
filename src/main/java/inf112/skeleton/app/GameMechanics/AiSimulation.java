package inf112.skeleton.app.GameMechanics;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Cards.CardType;
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
    private List<Card> lockedCards;

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

    private PriorityQueue<Tile> flagTiles = new PriorityQueue<>(1,flagTileComparator);

    /**
     * Creates a simulation object for a given player on a given board.
     * Used to find the best cards for the player
     * @param board
     * @param player
     */
    public AiSimulation(Board board, Player player){
        this.originalBoard = board;
        this.originalPlayer = player;
    }

    public Card[] findBestCards(){
        if(flagTiles.isEmpty()) findFlags();
        checkNextFlag();
        cards = originalPlayer.getCardHand();
        lockedCards = new ArrayList<>();
        int numLockedCards = originalPlayer.getDamage()+4;

        lockedCards = new ArrayList<>();
        lockedCards = cards.subList(cards.size()-numLockedCards, cards.size());



        ArrayList<Card> toRemove = new ArrayList<>();

        for (Card c : lockedCards) {
            if(cards.contains(c)) {
                toRemove.add(c);
            }
        }
        cards.removeAll(toRemove);

        List<Card> testList = new ArrayList<>();
        for(Card c : lockedCards){
            testList.add(c);
        }

        lockedCards = testList;

        //for(Card card : lockedCards){
        //    cards.remove(card);
       // }

        Board testBoard = originalBoard;
        Player testPlayer = originalPlayer;

        Card[] testedCards;
        testedCards = testCards(cards, testPlayer, testBoard);
        Card[] bestCards = new Card[5];
        System.arraycopy(testedCards, 0, bestCards, 0, testedCards.length);
        for(int i=0;i<lockedCards.size();i++){
            bestCards[testedCards.length + i] = lockedCards.get(i);
        }
        return bestCards;
    }

    private void findFlags(){
        HashMap<Position, Tile> tiles = originalBoard.getTileMap();
        for (Tile t : tiles.values()){
            GameObject[] objectsOnTile = t.getGameObjects();
            for (GameObject object : objectsOnTile) {
                if(object instanceof Flag) {
                    flagTiles.add(t);
                    break;
                }
            }
        }
        maxFlags = flagTiles.size();
        nextFlagTile = flagTiles.poll();
    }


    /**
     * Method that will simulate a player to find cards that moves it closer to its next flag
     * @param cards the AIs not-locked cards
     * @param player the player to be simulated
     * @param board a copy of the original board
     * @return list cards to get the AI closest to the checkpoint
     */
    private Card[] testCards(List<Card> cards, Player player, Board board){
        List<Card> bestCards = new ArrayList<>();
        Player tempPlayer = player;
        Board tempBoard = board;
        while(true){
            //map of cards with a number that represents the new distance to the flag after they are played
            HashMap<Card, Double> cardRating = new HashMap<>();
            Card tempBestCard = cards.get(0);

            for(Card c : cards) {
                player = tempPlayer;
                board = tempBoard;
                CardType curCard = c.getCardType();
                useMovementCard(player, board, curCard);
                if (player.onBoardCheck()) {
                    cardRating.put(c, distFromFlag(board.getPlayerPos(player), nextFlagTile.getPosition()));
                }

            }
            if(cardRating.size() != 0) {
                for (Card c : cardRating.keySet()) {
                    if (cardRating.get(c) < cardRating.get(tempBestCard)) tempBestCard = c;
                }
            }

            cards.remove(tempBestCard);
            bestCards.add(tempBestCard);

            useMovementCard(tempPlayer, tempBoard, tempBestCard.getCardType());

            if(bestCards.size() == 5-lockedCards.size()) break;
        }

        Card[] bestCardsArray = new Card[5-lockedCards.size()];
        for(int i=0;i<bestCardsArray.length;i++){
            bestCardsArray[i] = bestCards.get(i);
        }

        return bestCardsArray;
    }

    /**
     * Method used to simulate a card on a given board for a given player
     * @param player
     * @param board
     * @param card
     */
    private void useMovementCard(Player player, Board board, CardType card){
        if (card.getMovement() > 0) {
            board.movePlayer(player, player.getDirection(), card.getMovement());
        } else if (card.getMovement() < 0) {
            board.movePlayer(player, player.getDirection().oppositeDirection());
        }
        else{
            player.turnPlayer(card.getRotation());
        }
    }

    /**
     * checks and updates what flag the player should go to next
     */
    private void checkNextFlag(){
        if(originalPlayer.numberOfFlagsCollected() + flagTiles.size() < maxFlags){
            nextFlagTile = flagTiles.poll();
        }

    }

    /**
     * Method that calculates the distance from a given position to another
     * @param pos1
     * @param flagPos
     * @return distance from the flag
     */
    public double distFromFlag(Position pos1, Position flagPos){
        double distanceToFlag = Integer.MAX_VALUE;
        if(pos1 == null || flagPos == null) return distanceToFlag;

        distanceToFlag = Math.sqrt(Math.pow(flagPos.getX() - pos1.getX(), 2)
                + Math.pow(flagPos.getY() - pos1.getY(), 2));
        return distanceToFlag;
    }

}
