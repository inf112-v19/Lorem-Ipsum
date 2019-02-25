package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Position;

public interface IPlayer {
	
	void turnPlayer(int numberOfTurns);

	int getDirection();

	void setPlayerDirection(String direction);

	Card[] getCardSequence();

	void setCardSequence();

	void setCardHand();

	void sortCardSeqence();

	void decreaseHealth();

	void increaseHealth();

	void setBackup();

	Position getBackup();







}
