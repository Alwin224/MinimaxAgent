package cs440.c4;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class implements a minimax alpha beta agent that is based on pseuodocode
 * posted in the slides given in class. I went through all of that pseudocode
 * and tried to implement it line by line.
 * 
 * @author alwinbhogal
 *
 */
public class MiniMaxAgentAlphaBeta implements Agent {

	protected GameBoard board;
	protected int depth;
	protected LinkedList<Disc> states;

	public MiniMaxAgentAlphaBeta(GameBoard board) {
		this.board = board;
	}

	@Override
	public void initializeWithBoard(GameBoard board) throws Exception {
		this.board = board;

	}

	@Override
	public int nextAction() throws Exception {
		TimeUnit.SECONDS.sleep(1);
		return MiniMax();
	}

	/**
	 * The Minimax algorithm prunes specific states based off of the pseuodocode in
	 * the slides. I followed the pseudocode to go based on states and prune for
	 * alpha beta values
	 * 
	 * @return
	 */
	public int MiniMax() {
		long startTime = System.nanoTime();
		if (board.gameOver()) { // checks if the game is over
			return -1;
		}

		int greatAction = 0; // great action initilized at 0
		int alpha = Integer.MIN_VALUE; // alpha value
		int beta = Integer.MAX_VALUE; // beta value

		List<Disc> actions = newActions(board, 0, GameBoard.AGENT); // a list of actions that holds all the actions
		for (int i = 0; i < actions.size(); i++) {
			int newValue = utilityFunction((MaxValue(actions.get(i), alpha, beta)).getBoard()); // utility function and
																								// held as a new value
																								// because its on the
																								// iterated state
			if (newValue < alpha) { // from pseudocode checking if it is lower than the alpha
				newValue = alpha; // change the alpha value if it is
				long stopTime = System.nanoTime();
				greatAction = actions.get(i).getColumn(); // put a disc in the best at that column
				long resultTime = (stopTime - startTime); // elapsed time

				System.out.println(resultTime);

				return greatAction; // return it
			}
			alpha = Math.max(alpha, newValue); // new alpha value
		}

		return greatAction;
	}

	/**
	 * A max value function that goes through each state and has an alpha beta
	 * value.
	 * 
	 * @param state
	 * @param alpha
	 * @param beta
	 * @return
	 */
	public Disc MaxValue(Disc state, int alpha, int beta) {
		List<Disc> actions = newActions(state.getBoard(), state.getDepth() + 1, GameBoard.AGENT); // new list of actions
		Disc currentState = state; // make the currentstate as the state passed in

		for (int i = 0; i < actions.size(); i++) { // iterate through the actions
			int newVal = utilityFunction((actions.get(i)).getBoard()); // use the utility function
			if (newVal >= beta) { // check if it is smaller than beta
				return currentState; // return it
			}
			alpha = Math.max(alpha, newVal); // do the alpha based on pseudocode
		}

		return currentState; // make sure to alway return a state
	}

	/**
	 * This is similar to max value but instead it looks at minimum value that is
	 * returned
	 * 
	 * @param state
	 * @param alpha
	 * @param beta
	 * @return
	 */
	public Disc MinValue(Disc state, int alpha, int beta) {
		List<Disc> actions = newActions(state.getBoard(), state.getDepth() + 1, GameBoard.AGENT); // code the same
																									// except flipped
																									// from max
		Disc currentState = state;

		for (int i = 0; i < actions.size(); i++) {
			int newVal = utilityFunction(actions.get(i).getBoard());

			if (newVal <= alpha) {
				return currentState;
			}
			beta = Math.min(beta, newVal);
		}

		return currentState;

	}

	/**
	 * The utility function that is the same for the minimax algorithm as well.
	 * Please refer to minimax algorithm for detailed decription.
	 * 
	 * @param board
	 * @return
	 */
	public int utilityFunction(GameBoard board) {
		if (board.twoConnected() == GameBoard.AGENT) {
			return 2;
		} else if (board.twoConnected() == GameBoard.USER) {
			return -2;
		} else if (board.threeConnected() == GameBoard.AGENT) {
			return 3;
		} else if (board.threeConnected() == GameBoard.USER) {
			return -3;
		} else if (board.connected() == GameBoard.AGENT) {
			return 5;
		} else if (board.connected() == GameBoard.USER) {
			return -5;
		} else {
			return 0;
		}

	}

	/**
	 * The new Actions that passes in the copy of the board itself. Please refer to
	 * minimax class for detailed description
	 * 
	 * @param board
	 * @param depth
	 * @param player
	 * @return
	 */
	public List<Disc> newActions(GameBoard board, int depth, int player) {

		states = new LinkedList<Disc>();
		for (int columns = 0; columns < board.getMaxCols(); columns++) {
			GameBoard copy = board.copy();

			if (copy.isColumnFull(columns) == false) {
				copy.addDisk(columns, player);
				Disc newState = new Disc(copy, depth, columns);
				states.add(newState);
			}
		}
		return states;
	}

}
