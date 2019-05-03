package inf112.skeleton.app.Netcode;

/**
 * Interface that establishes some kind of relation between Client and Host
 */
public interface INetCode {

	void start() throws Exception;

	void send(String msg);

	boolean isThisTurn();

	int getIndex();

	void resetCards();

	void disconnect();

	//String receive();
}
