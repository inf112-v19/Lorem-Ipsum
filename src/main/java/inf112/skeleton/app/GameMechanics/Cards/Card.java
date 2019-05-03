package inf112.skeleton.app.GameMechanics.Cards;

import inf112.skeleton.app.Visuals.SpriteType;

public class Card implements Comparable<Card> {
    private CardType type;
    private int priority;

    public Card(CardType type, int priority) {
        this.type = type;
        this.priority = priority;
    }

    public Card(String cardType, int priority) {
        this.priority = priority;
        this.type = getCardTypeByString(cardType);
    }

    public CardType getCardTypeByString(String s) {
        switch (s) {
            case "BACKWARD_1":
                return CardType.BACKWARD_1;
            case "FORWARD_1":
                return CardType.FORWARD_1;
            case "FORWARD_2":
                return CardType.FORWARD_2;
            case "FORWARD_3":
                return CardType.FORWARD_3;
            case "ROTATE_180":
                return CardType.ROTATE_180;
            case "ROTATE_90_L":
                return CardType.ROTATE_90_L;
            case "ROTATE_90_R":
                return CardType.ROTATE_90_R;
            default:
                System.out.println("Invalid input CardType");
                return CardType.FORWARD_1;
        }
    }

    public CardType getCardType() {
        return this.type;
    }

    public int getPriority() {
        return priority;
    }

    public SpriteType getSprite() {
        return type.getSprite();
    }

    public String toString() {
        return type.toString();
    }

    @Override
    public int compareTo(Card other) {
        if (this.priority == other.priority) {
            return 0;
        } else {
            return this.priority > other.priority ? -1 : 1;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 53 * hash + this.priority;
        return hash;
    }
}
