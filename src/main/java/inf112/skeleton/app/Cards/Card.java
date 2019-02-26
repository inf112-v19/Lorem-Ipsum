package inf112.skeleton.app.Cards;

import com.badlogic.gdx.graphics.Texture;

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

    public Texture getImage() {
        return type.getImage();
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.priority, other.priority);
    }
}
