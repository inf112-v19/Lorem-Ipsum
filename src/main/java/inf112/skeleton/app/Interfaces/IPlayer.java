package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GUI.SpriteType;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Direction;

public interface IPlayer {

	void turnPlayer(int numberOfTurns);

	Direction getDirection();

	void setPlayerDirection(Direction dir);

	Card[] getCardSequence();

	void setCardSequence();

	void setCardHand();

	void sortCardSeqence();

	void decreaseHealth();

	void increaseHealth();

	int getHealth();

	void setBackup(Position backupPosition);

	Position getBackup();

	SpriteType getSpriteType();







}
