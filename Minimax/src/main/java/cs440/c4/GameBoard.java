package cs440.c4;

/**
 * An object instance that implements the integer will
 */
public interface GameBoard {

	public final int N = 4; // Four adjacent checkers wins the game.

	public final int NONE = 0;
	public final int AVAIL = 0;
	public final int USER = 1;
	public final int AGENT = -1;
	public final int BOUNDARY = 3; // a boundary that is set for the diagonal and verticals

	/**
	 * A specified player (USER or AGENT), drops a checker in the specified column
	 * (c). Gravity pulls the checker down to its final row column position. The
	 * algorithm returns the final row position within the specified column.
	 * 
	 * @param c      int value specifying with column
	 * @param player constant integer value (GameBoard.USER or GameBoard.AGENT)
	 * @return row at which the user's checker is placed.
	 */
	public int addDisk(int c, int player);

	/**
	 * Checks the gameboard for N connected checker pieces.
	 * 
	 * @return int user constant identifying which player is the winner
	 *         ((GameBoard.USER or GameBoard.AGENT).
	 */
	public int connected();

	/**
	 * answers true if game is over, false otherwise
	 * 
	 * @return boolean indicating if game is over
	 */
	public boolean gameOver();

	/**
	 * Provides access to the game board's physical representation -- a 2D array of
	 * integers.
	 * 
	 * @return a 2D int array handle
	 */
	public int[][] getBoard();

	/**
	 * Checks the specified column to see if it is full of checkers.
	 * 
	 * @param c int specifying which column to check
	 * @return true if column is full; false otherwise;
	 */
	public boolean isColumnFull(int c);

	/**
	 * A getter method to access the getMaxRows()
	 * 
	 * @return maxRows
	 */
	public int getMaxRows();

	/**
	 * A getter method to access the getMaxCols()
	 * 
	 * @return maxCols
	 */
	public int getMaxCols();

	/**
	 * Returns a copy of the board that is made at the start of the algorithm. Each
	 * row and column is then copied into a new board and returned as the copy.
	 * 
	 * @return
	 */
	public GameBoard copy();

	/**
	 * Checks to see if two checkers are connected in a horizontal, vertical, or
	 * diagonal fashion. It sets the last two slots as GameBoard.AVAIL to be able to
	 * trick and have two connected.
	 * 
	 * @return GameBoard.USER or GameBoard.AGENT
	 */
	public int twoConnected();

	/**
	 * Checks to see if three checkers are connected in a horizontal, vertical, or
	 * diagonal fashion. It sets the last two slots as GameBoard.AVAIL to be able to
	 * trick and have two connected.
	 * 
	 * @return GameBoard.USER or GameBoard.AGENT
	 */
	public int threeConnected();

}