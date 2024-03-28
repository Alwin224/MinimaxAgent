package cs440.c4;

/**
 * This Class is the connectBoard class that holds all of the rules for the game
 * itself.
 * 
 * @author alwinbhogal
 *
 */
public class ConnectBoard implements GameBoard {

	protected int[][] board;
	protected int maxRows;
	protected int maxCols;

	public ConnectBoard(int maxRows, int maxCols) {
		board = new int[maxRows][maxCols];
		this.maxRows = maxRows;
		this.maxCols = maxCols;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	public int getMaxCols() {
		return maxCols;
	}

	public void setMaxCols(int maxCols) {
		this.maxCols = maxCols;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	@Override
	public int addDisk(int c, int player) {

		for (int rows = maxRows - 1; rows >= 0; rows--) {
			for (int columns = 0; columns < maxCols; columns++) {

				if (player == GameBoard.USER && board[rows][c] == GameBoard.AVAIL) {
					return board[rows][c] = GameBoard.USER;
				} else if (player == GameBoard.AGENT && board[rows][c] == GameBoard.AVAIL) {
					return board[rows][c] = GameBoard.AGENT;

				}

			}
		}

		return 0;
	}

	@Override
	public int connected() {

		if (isHorizontal(GameBoard.USER) == true) {
			return GameBoard.USER;
		} else if (isHorizontal(GameBoard.AGENT) == true) {
			return GameBoard.AGENT;
		} else if (isVertical(GameBoard.USER) == true) {
			return GameBoard.USER;
		} else if (isVertical(GameBoard.AGENT) == true) {
			return GameBoard.AGENT;
		} else if (isDiagonal(GameBoard.USER) == true) {
			return GameBoard.USER;
		} else if (isDiagonal(GameBoard.AGENT) == true) {
			return GameBoard.AGENT;
		}
		return 0;
	}

	@Override
	public int twoConnected() {
		if (isTwoHorizontal(GameBoard.USER) == true) {
			return GameBoard.USER;
		} else if (isTwoHorizontal(GameBoard.AGENT) == true) {
			return GameBoard.AGENT;
		} else if (isTwoDiagonal(GameBoard.USER) == true) {
			return GameBoard.USER;
		} else if (isTwoDiagonal(GameBoard.AGENT) == true) {
			return GameBoard.AGENT;
		} else if (isTwoVertical(GameBoard.USER) == true) {
			return GameBoard.USER;
		} else if (isTwoVertical(GameBoard.AGENT) == true) {
			return GameBoard.AGENT;
		}
		return 0;
	}

	@Override
	public int threeConnected() {
		if (isThreeHorizontal(GameBoard.USER) == true) {
			return GameBoard.USER;
		} else if (isThreeHorizontal(GameBoard.AGENT) == true) {
			return GameBoard.AGENT;
		} else if (isThreeDiagonal(GameBoard.USER) == true) {
			return GameBoard.USER;
		} else if (isThreeDiagonal(GameBoard.AGENT) == true) {
			return GameBoard.AGENT;
		} else if (isThreeVertical(GameBoard.USER) == true) {
			return GameBoard.USER;
		} else if (isThreeVertical(GameBoard.AGENT) == true) {
			return GameBoard.AGENT;
		}
		return 0;
	}

	/**
	 * Checks the board for two horizontal and the other two are available slots.
	 * This is then used in the algorithm for the minimax that evaluates the states.
	 * 
	 * @param player
	 * @return true if two connected horizontally and false otherwise
	 */
	public boolean isTwoHorizontal(int player) {
		for (int rows = 0; rows < maxRows; rows++) {
			for (int columns = 0; columns < GameBoard.N; columns++) {

				// Checking horizontally
				if (board[rows][columns] == player && board[rows][columns + 1] == player
						&& board[rows][columns + 2] == GameBoard.AVAIL && board[rows][columns + 3] == GameBoard.AVAIL) {
					return true;

				}
			}
		}
		return false;
	}

	/**
	 * Checks the board for three horizontal and the other one is an available slot.
	 * This is then used in the algorithm for the minimax that evaluates the states.
	 * 
	 * @param player
	 * @return true if three connected horizontally and false otherwise
	 */
	public boolean isThreeHorizontal(int player) {
		for (int rows = 0; rows < maxRows; rows++) {
			for (int columns = 0; columns < GameBoard.N; columns++) {

				// Checking horizontally
				if (board[rows][columns] == player && board[rows][columns + 1] == player
						&& board[rows][columns + 2] == player && board[rows][columns + 3] == GameBoard.AVAIL) {
					return true;

				}
			}
		}
		return false;
	}

	/**
	 * Checks the board for horizontal discs and seeing if they are all the same
	 * 
	 * @param player
	 * @return true of four connected horizontally and false otherwise
	 */
	public boolean isHorizontal(int player) {
		for (int rows = 0; rows < maxRows; rows++) {
			for (int columns = 0; columns < GameBoard.N; columns++) {

				// Checking horizontally
				if (board[rows][columns] == player && board[rows][columns + 1] == player
						&& board[rows][columns + 2] == player && board[rows][columns + 3] == player) {
					return true;

				}
			}
		}
		return false;
	}

	/**
	 * Checks the board for two diagonal and the other two are available slots. This
	 * is then used in the algorithm for the minimax that evaluates the states.
	 * 
	 * @param player
	 * @return true if two connected diagonally and false otherwise
	 */
	public boolean isTwoDiagonal(int player) {
		for (int rows = 0; rows < GameBoard.BOUNDARY; rows++) {
			for (int columns = 0; columns < GameBoard.N; columns++) {
				// Checking Diagonally
				if (board[rows][columns] == player && board[rows + 1][columns + 1] == player
						&& board[rows + 2][columns + 2] == GameBoard.AVAIL
						&& board[rows + 3][columns + 3] == GameBoard.AVAIL) {
					return true;
				}
				if (board[rows][columns + 3] == player && board[rows + 1][columns + 2] == player
						&& board[rows + 2][columns + 1] == GameBoard.AVAIL
						&& board[rows + 3][columns] == GameBoard.AVAIL) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks the board for three diagonal and the other one is an available slot.
	 * This is then used in the algorithm for the minimax that evaluates the states.
	 * 
	 * @param player
	 * @return true if three connected diagonally and false otherwise
	 */
	public boolean isThreeDiagonal(int player) {
		for (int rows = 0; rows < GameBoard.BOUNDARY; rows++) {
			for (int columns = 0; columns < GameBoard.N; columns++) {
				// Checking Diagonally
				if (board[rows][columns] == player && board[rows + 1][columns + 1] == player
						&& board[rows + 2][columns + 2] == player && board[rows + 3][columns + 3] == GameBoard.AVAIL) {
					return true;
				}
				if (board[rows][columns + 3] == player && board[rows + 1][columns + 2] == player
						&& board[rows + 2][columns + 1] == player && board[rows + 3][columns] == GameBoard.AVAIL) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks the board for diagonal slots This is then used in the algorithm for
	 * the minimax that evaluates the states.
	 * 
	 * @param player
	 * @return true if four connected diagonally and false otherwise
	 */
	public boolean isDiagonal(int player) {
		for (int rows = 0; rows < GameBoard.BOUNDARY; rows++) {
			for (int columns = 0; columns < GameBoard.N; columns++) {
				// Checking Diagonally
				if (board[rows][columns] == player && board[rows + 1][columns + 1] == player
						&& board[rows + 2][columns + 2] == player && board[rows + 3][columns + 3] == player) {
					return true;
				}
				if (board[rows][columns + 3] == player && board[rows + 1][columns + 2] == player
						&& board[rows + 2][columns + 1] == player && board[rows + 3][columns] == player) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks the board for two vertical and the other two are available slots. This
	 * is then used in the algorithm for the minimax that evaluates the states.
	 * 
	 * @param player
	 * @return true if two connected vertically and false otherwise
	 */
	public boolean isTwoVertical(int player) {
		for (int rows = 0; rows < GameBoard.BOUNDARY; rows++) {
			for (int columns = 0; columns < maxCols; columns++) {
				if (board[rows][columns] == player && board[rows + 1][columns] == player
						&& board[rows + 2][columns] == GameBoard.AVAIL && board[rows + 3][columns] == GameBoard.AVAIL) {
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * Checks the board for three vertical and the other one is an available slot.
	 * This is then used in the algorithm for the minimax that evaluates the states.
	 * 
	 * @param player
	 * @return true if three connected vertically and false otherwise
	 */
	public boolean isThreeVertical(int player) {
		for (int rows = 0; rows < GameBoard.BOUNDARY; rows++) {
			for (int columns = 0; columns < maxCols; columns++) {
				if (board[rows][columns] == player && board[rows + 1][columns] == player
						&& board[rows + 2][columns] == player && board[rows + 3][columns] == GameBoard.AVAIL) {
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * Checks the board for four vertically. This is then used in the algorithm for
	 * the minimax that evaluates the states.
	 * 
	 * @param player
	 * @return true if all vertical and false otherwise
	 */
	public boolean isVertical(int player) {
		for (int rows = 0; rows < GameBoard.BOUNDARY; rows++) {
			for (int columns = 0; columns < maxCols; columns++) {
				if (board[rows][columns] == player && board[rows + 1][columns] == player
						&& board[rows + 2][columns] == player && board[rows + 3][columns] == player) {
					return true;
				}

			}
		}
		return false;
	}

	@Override
	public boolean gameOver() {
		return connected() == GameBoard.USER || connected() == GameBoard.AGENT;
	}

	@Override
	public int[][] getBoard() {
		return board;
	}

	@Override
	public boolean isColumnFull(int c) {
		for (int columns = 0; columns < maxCols; columns++) {
			if (board[0][c] != GameBoard.AVAIL) {
				return true;
			}

		}
		return false;
	}

	@Override
	public GameBoard copy() {
		GameBoard copiedBoard = new ConnectBoard(this.getMaxRows(), this.getMaxCols());
		for (int rows = 0; rows < copiedBoard.getMaxRows(); rows++) {
			for (int columns = 0; columns < copiedBoard.getMaxCols(); columns++) {
				copiedBoard.getBoard()[rows][columns] = this.getBoard()[rows][columns];
			}
		}
		return copiedBoard;
	}

}
