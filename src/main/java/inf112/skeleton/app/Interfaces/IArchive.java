package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Position;

public interface IArchive {
	/**
	 * Interface for backup.
	 * Classes that implement this interface should call
	 * players backup methods using the setBackup method.
	 */



	/**
	 * Setting new backup Position for players by
	 * calling players backup method
	 * @param player
	 * @param pos
	 */
	void setBackup(Player player, Position pos);

}
