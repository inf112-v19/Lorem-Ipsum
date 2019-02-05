package inf112.skeleton.app;

public interface IBoard<T> {

    /** @return The height of the grid. */
    int getHeight();

    /** @return The width of the grid. */
    int getWidth();

    /**
     * Set the contents of the cell in the given x,y location.
     *
     * y must be greater than or equal to 0 and less than getHeight(). x must be
     * greater than or equal to 0 and less than getWidth().
     *
     * @param x
     *            an x coordinate
     * @param y
     *            an y coordinate
     * @param element
     *            The contents the cell is to have.
     * @throws IndexOutOfBoundsException
     *             if !isValid(x,y)
     */
    void setObject(int x, int y, T element);

    /**
     * Get the contents of the cell in the given x,y location.
     *
     * y must be greater than or equal to 0 and less than getHeight(). x must be
     * greater than or equal to 0 and less than getWidth().
     *
     * @param x
     *            The column of the cell to get the contents of.
     * @param y
     *            The row of the cell to get contents of.
     * @return the object that you are getting
     * @throws IndexOutOfBoundsException
     *             if !isValid(x,y)
     */
    T getObject(int x, int y);

    /**
     * Move the current player in the given direction.
     *
     * @param x
     *            an x coordinate
     * @param y
     *            an y coordinate
     */
    void movePlayer(int x, int y);

    /**
     * Remove an element from the cell at the given x,y location.
     *
     * y must be greater than or equal to 0 and less than getHeight(). x must be
     * greater than or equal to 0 and less than getWidth().
     *
     * @param x
     *            an x coordinate
     * @param y
     *            an y coordinate
     * @param element
     *            An element to be removed from the cell
     * @throws IndexOutOfBoundsException
     *             if !isValid(x,y)
     */
    void removeObject(int x, int y, T element);

    /**
     * Check if coordinates are valid.
     *
     * Valid coordinates are 0 <= x < getWidth(), 0 <= y < getHeight().
     *
     * @param x
     *            an x coordinate
     * @param y
     *            an y coordinate
     * @return true if the (x,y) position is within the grid
     */
    boolean isValid(int x, int y);

}
