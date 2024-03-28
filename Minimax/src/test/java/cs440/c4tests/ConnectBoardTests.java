package cs440.c4tests;

import static org.junit.Assert.*;


import org.junit.Test;

import cs440.c4.ConnectBoard;
import cs440.c4.GameBoard;

public class ConnectBoardTests {
	protected ConnectBoard connectBoard;
 
	public ConnectBoardTests() {
		connectBoard = new ConnectBoard(6, 7);
	}
	


	/**Checks to see if the user is added 
	 * to the correct column
	 */
	@Test
	public void testAddDisk_User() {
		int[][] board = { 
				{ 0, -1, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0, 0, 0 }, 
				{ 0, -1, 0, -1, 0, 0, 1 },
				{ 0, 1, 0, 1, 0, 1, -1 }, 
				{ 0, -1, 0, -1, 1, 0, 1 }, 
				{ 0, 1, 0, 1, 0, -1, -1 } 
				};
		connectBoard.setBoard(board);
		connectBoard.addDisk(3, 1);

		assertTrue(board[5][3]==1); 
	}
	
	/**Checks to see if the column is full in
	 * the board.
	 */
	@Test
	public void test_isColumnFull() {
		int[][] board = { 
				{ 0, -1, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0, 0, 0 }, 
				{ 0, -1, 0, -1, 0, 0, 1 },
				{ 0, 1, 0, 1, 0, 1, -1 }, 
				{ 0, -1, 0, -1, 1, 0, 1 }, 
				{ 0, 1, 0, 1, 1, -1, -1 } 
				};
		connectBoard.setBoard(board);
		assertEquals(connectBoard.isColumnFull(1),true);
	}
	
	@Test
	public void test_isConnected_Diagonally_USER_positiveslope() {
		int[][] board = { 
				{ 0, -1, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0, 0, 0 }, 
				{ 0, -1, 0, -1, 0, 1, 1 },
				{ 0, 1, 0, 1, 0, 1, -1 }, 
				{ 0, -1, 0, -1, 1, 0, 1 }, 
				{ 0, 1, 0, 1, 1, -1, -1 } 
				};
		connectBoard.setBoard(board);
		assertEquals(connectBoard.isDiagonal(GameBoard.USER),true);
	}
	@Test
	public void test_isConnected_Diagonally_AGENT_postiveslope() {
		int[][] board = { 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, -1},
				{ 0, 0, 0, 0, 0, -1, -1}, 
				{ 0, 0, 0, 0, -1, 1, 1 }, 
				{ 0, 0, 0, -1, 1, 1, 1 } 
				};
		connectBoard.setBoard(board);
		assertEquals(connectBoard.isDiagonal(GameBoard.AGENT),true);
	}
	@Test
	public void test_isConnected_Diagonally_USER_negativeslope() {
		int[][] board = { 
				{ 0, -1, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, -1, 0, 1, 1 },
				{ 0, 1, 1, 1, 0, -1, -1 }, 
				{ 0, -1, 0, 1, 1, 1, 1 }, 
				{ 0, 1, 0, 1, 1, -1, -1 } 
				};
		connectBoard.setBoard(board);
		assertEquals(connectBoard.isDiagonal(GameBoard.USER),true);
	}
	@Test
	public void test_isConnected_Diagonally_AGENT_negativeslope() {
		int[][] board = { 
				{ 0, -1, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 1, 0, 0, 0, 0 }, 
				{ 0, -1, -1, -1, 0, 1, 1 },
				{ 0, 1, 1, -1, -1, 1, -1}, 
				{ 0, -1, 1, -1, -1, -1, 1 }, 
				{ 0, 1, -1, 1, 1, -1, -1 } 
				};
		connectBoard.setBoard(board);
		assertEquals(connectBoard.isDiagonal(GameBoard.AGENT),true);
	}
	@Test
	public void test_isConnected_Horizontally_USER() {
		int[][] board = { 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, -1, -1},
				{ 0, 0, 0, 0, 0, -1, -1}, 
				{ 0, 0, 0, 0, -1, 1, 1 }, 
				{ 0, 0, 0, 1, 1, 1, 1 } 
				};
		connectBoard.setBoard(board);
		assertEquals(connectBoard.isHorizontal(GameBoard.USER), true);
	}
	@Test
	public void test_isConnected_Horizontally_AGENT() {
		int[][] board = { 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 1, -1},
				{ 0, 0, 0, 0, -1, 1, 1}, 
				{ 0, 0, 0, 0, 1, 1, 1 }, 
				{ 0, 0, -1, -1, -1, -1, 1 } 
				};
		connectBoard.setBoard(board);
		assertEquals(connectBoard.isHorizontal(GameBoard.AGENT), true);
	}
	@Test
	public void test_isConnected_Vertically_USER() {
		int[][] board = { 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 1, 0, -1, -1},
				{ 0, 0, 0, 1, 0, -1, -1}, 
				{ 0, 0, -1, 1, -1, 1, 1 }, 
				{ 0, 0, -1, 1, 1, 1, -1 } 
				};
		connectBoard.setBoard(board);
		assertEquals(connectBoard.isVertical(GameBoard.USER),true);
	}
	@Test
	public void test_isConnected_Vertically_AGENT() {
		int[][] board = { 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, -1, 0, 0, 0, -1, -1},
				{ 0, -1, 0, 0, 0, -1, -1}, 
				{ 0, -1, 0, 1, -1, 1, 1 }, 
				{ 0, -1, 1, -1, 1, 1, 1 } 
				};
		connectBoard.setBoard(board);
		assertTrue(connectBoard.isVertical(GameBoard.AGENT)==true);
	}
	@Test
	public void test_gameisOver_true() {
		int[][] board = { 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 1, -1},
				{ 0, 0, 0, 0, -1, 1, 1}, 
				{ 0, 0, 0, 0, 1, 1, 1 }, 
				{ 0, 0, -1, -1, -1, -1, 1 } 
				};
		connectBoard.setBoard(board);
		assertTrue(connectBoard.gameOver() == true);
	}
	@Test
	public void test_gameisOver_false() {
		int[][] board = { 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, -1 }, 
				{ 0, 0, 0, 0, 0, 0, 1 } 
				};
		connectBoard.setBoard(board);
		assertFalse(connectBoard.gameOver()==true);
	}
}
