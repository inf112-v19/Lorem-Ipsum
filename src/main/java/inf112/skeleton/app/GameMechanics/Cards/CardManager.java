package inf112.skeleton.app.GameMechanics.Cards;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Interfaces.ICardDeck;
import inf112.skeleton.app.Netcode.INetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CardManager {

    private ICardDeck cardDeck;
    private Player[] players;
    private HashMap<Integer, HashSet<Card>> lockedCards;
    private INetCode net;

    public CardManager(Board board, INetCode net) {
        players = board.getAllPlayers();
        cardDeck = new ProgramCardDeck();
        this.net = net;
    }

    /**
     * Initialize new round. Fills card deck with last round cards.
     * Draw new cards for each player.
     */
    public void newRound() {
        lockCards();
        sendCardsBackToDeck();
        cardDeck.shuffleDeck();
        dealCards();
    }

    /**
     * @return current player for assigning play sequence
     */
    public Player getPlayer() {
        Player currentPlayer = null;
        for (Player player : players) {
            if (!player.isReady() && !player.isDead() && player.getPowerDown() != 1 && net == null) {
                currentPlayer = player;
                break;
            } else if (!player.isReady() && !player.isDead() && player.getPowerDown() != 1 && player.getIndex() == net.getIndex()) {
                currentPlayer = player;
                break;
            }
        }
        return currentPlayer;
    }

    /**
     * Checks if all players has a valid play card sequence
     *
     * @return true if there is one or more player without a valid card sequence ready to execute
     */
    public boolean hasNotReadyPlayers() {
        for (Player player : players) {
            if (net != null) {
                if (player.getIndex() != net.getIndex()) {
                    continue;
                }
            }
            if (player.getPowerDown() == 1) {
                Card[] cards = new Card[5];
                for (int i = 0; i < cards.length; i++) {
                    cards[i] = new Card(CardType.FORWARD_1, 10);
                }
                player.setCardSequence(cards);
                player.setReady();
                continue;
            }
            if (player.isDead()) {
                player.setReady();
            }
            if (player.isReady()) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets a five card play sequence for a given player
     *
     * @param player
     * @param cards
     * @return true if its a valid 5 card sequence
     */
    public boolean setCardSeq(Player player, Card[] cards) {
        if (cards.length == 5) {
            for (Card card : cards) {
                if (card == null) {
                    return false;
                }
            }
            player.setCardSequence(cards);
            player.setReady();
            return true;
        } else {
            return false;
        }
    }

    public boolean isLocked(Card c, int playerIndex) {
        if (lockedCards.get(playerIndex) != null) {
            return lockedCards.get(playerIndex).contains(c);
        } else {
            return false;
        }
    }

    private void sendCardsBackToDeck() {
        for (int i = 0; i < players.length; i++) {
            List<Card> cards = players[i].getCardHand();
            if (cards != null) {
                for (int j = 0; j < cards.size(); j++) {
                    if (!isLocked(cards.get(j), players[i].getIndex())) {
                        cardDeck.addCard(cards.get(j));
                    }

                }
            }
        }
    }

    private void lockCards() {
        lockedCards = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            int playerIndex = players[i].getIndex();
            lockedCards.put(playerIndex, new HashSet<Card>());
            if (players[i].getCardSequence() != null) {
                Card[] tempCards = players[i].getCardSequence();
                int playerDamage = players[i].getDamage();
                if (playerDamage > 4) {
                    lockedCards.get(playerIndex).add(tempCards[4]);
                    if (playerDamage > 5) {
                        lockedCards.get(playerIndex).add(tempCards[3]);
                    }
                    if (playerDamage > 6) {
                        lockedCards.get(playerIndex).add(tempCards[2]);
                    }
                    if (playerDamage > 7) {
                        lockedCards.get(playerIndex).add(tempCards[1]);
                    }
                    if (playerDamage > 8) {
                        lockedCards.get(playerIndex).add(tempCards[0]);
                    }
                }
            }
        }
    }

    private void dealCards() {
        for (int i = 0; i < players.length; i++) {
            Card[] oldCardSeq = players[i].getCardSequence();
            List<Card> newCardHand = new ArrayList<>();

            for (int j = 0; j < 9; j++) {
                if (oldCardSeq != null && oldCardSeq.length > j) {
                    if (oldCardSeq[j] != null) {
                        if (isLocked(oldCardSeq[j], players[i].getIndex())) {
                            newCardHand.add(oldCardSeq[j]);
                            continue;
                        }
                    }
                }
                newCardHand.add(cardDeck.drawCard());
            }

            int playerDamage = players[i].getDamage();
            int cardsToRemove = playerDamage;
            if (cardsToRemove > 0) {
                for (int j = 0; j < cardsToRemove; j++) {
                    if (j > 3) {
                        break;
                    }
                    cardDeck.addCard(newCardHand.remove(newCardHand.size() - 1));
                }
            }
            players[i].setCardHand(newCardHand);
        }
    }

    public int getCardDeckSize() {
        return cardDeck.numbersOfCardsLeft();
    }

}

