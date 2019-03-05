package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Direction;

import java.util.List;

public interface IPlayer {

	void turnPlayer(int numberOfTurns);

	Direction getDirection();

	void setPlayerDirection(Direction dir);

	Card[] getCardSequence();

    void setCardSequence(Card[] cardSequence);

    void setCardHand(List<Card> cards);

	void sortCardSeqence();

	void decreaseHealth();

	void increaseHealth();

	int getHealth();

	void setBackup(Position backupPosition);

	Position getBackup();

	SpriteType getSpriteType();

	boolean isReady();

	void setReady();







}
