package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.GUI.SpriteType;
import inf112.skeleton.app.Position;
import inf112.skeleton.app.Direction;

public interface IPlayer {

	void turnPlayer(int numberOfTurns);

	Direction getDirection();

	void setPlayerDirection(Direction direction);

	Card[] getCardSequence();

	void setCardSequence();

	void setCardHand();

	void sortCardSeqence();

	void decreaseHealth();

	void increaseHealth();

	void setBackup();

	Position getBackup();

	SpriteType getSpriteType();







}
