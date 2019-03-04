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

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.priority, other.priority);
    }
}
