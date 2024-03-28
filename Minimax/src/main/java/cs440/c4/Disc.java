package cs440.c4;

/**
 * This is a Disc class that holds the board, the depth, and the column. This is
 * what will help us in the minimax and minimax with alpha beta pruning. This
 * helps us place a handle of discs.
 * 
 * @author alwinbhogal
 *
 */
public class Disc {
	protected GameBoard board;
	protected int column;
	protected int depth;

	public Disc(GameBoard board, int depth, int column) {
		this.board = board;
		this.depth = depth;
		this.column = column;
	}

	public GameBoard getBoard() {
		return board;
	}

	public void setBoard(GameBoard board) {
		this.board = board;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

}
