package inf112.skeleton.app;

/**
 * Interface for Tiles
 *
 */

public interface iTile {


	/**
	 * gir posisjonen til tilen
	 *
	 * @return int array burde endres til Position
	 */
	int[][] getPosition();


	/**
	 * en metode for å sjekke om et object er på tilen eller ikke
	 *
	 * @return returnerer true hvis et object er på tilen
	 */
	boolean objectOnTile();

	/**
	 * gir et object på tilen dersom det finnes
	 *
	 * @return Object må endres til riktig type object som kan stå på en tile
	 */
	Object getObjectOnTile();


	/**
	 * en metode for å finne ut om tilen er farlig eller ikke
	 *
	 * @return true hvis en spiller mister liv av å gå på tilen
	 */
	boolean isHostile();






}
