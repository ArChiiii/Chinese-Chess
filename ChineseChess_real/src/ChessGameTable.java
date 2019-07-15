import java.awt.Point;

/**
 * An interface for a general chess game table (GUI)
 * 
 * @author Kenneth Wong n Archie Loong
 * 
 */
public interface ChessGameTable {
	/**
	 * Sets the index of the active player (i.e., the current player).
	 * 
	 * @param activePlayer
	 *            an int value representing the index of the active player
	 */
	public void setActivePlayer(int activePlayer);

	/**
	 * Returns an point of the chess selected.
	 * 
	 * @return an point of the chess selected
	 */
	public Point getSelected();

	/**
	 * Resets selected cards to an empty .
	 */
	public void resetSelected();

	/**
	 * Repaints the GUI.
	 */
	public void repaint();

	/**
	 * Prints the specified string to the message area of the card game table.
	 * 
	 * @param msg
	 *            the string to be printed to the message area of the card game
	 *            table
	 */
	public void printMsg(String msg);

	/**
	 * Clears the message area of the card game table.
	 */
	public void clearMsgArea();

	/**
	 * Resets the GUI.
	 */
	public void reset();

	/**
	 * Enables user interactions.
	 */
	public void enable();

	/**
	 * Disables user interactions.
	 */
	public void disable();
	/**
	 * Returns an point of the target selected.
	 * 
	 * @return an point of the target selected
	 */
	public Point getTarget();
}
