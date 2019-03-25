package inf112.skeleton.app.GameMechanics.Cards;

import inf112.skeleton.app.Visuals.SpriteType;

public class Card implements Comparable<Card>{
    CardType type;
    int priority;

    public Card(CardType type, int priority) {
        this.type = type;
        this.priority = priority;
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
        if (this.equals(other)) {
            return 0;
        } else {
            return this.priority > other.priority ? 1 : -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }

        Card c = (Card) o;

        return this.priority == c.priority;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 53 * hash + this.priority;
        return hash;
    }
}
