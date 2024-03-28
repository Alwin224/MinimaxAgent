package cs440.c4;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class was made using pseudocode that was posted in the powerpoints. I
 * looked at the adversarial search agent pseudocode.
 * 
 * @author alwinbhogal
 *
 */
public class MiniMaxAgent implements Agent {

	protected GameBoard board;
	protected int depth;
	protected LinkedList<Disc> states;

	public MiniMaxAgent(GameBoard board) {
		this.board = board;
	}

	@Override
	public void initializeWithBoard(GameBoard board) throws Exception {
		this.board = board;

	}

	/**
	 * The nextAction method just calls the MiniMax algorithm so that it may run and
	 * waits one second to allow the agent think before it places a disc
	 */
	@Override
	public int nextAction() throws Exception {
		TimeUnit.SECONDS.sleep(1); // agent thinks
		return MiniMax();
	}

	public int MiniMax() {
		long startTime = System.nanoTime();
		if (board.gameOver()) { // if the first state is gameOver just return -1
			return -1;
		}

		int value = Integer.MIN_VALUE; // value set as minimum value from Java Integer class
		int greatAction = -2; // sets the great action as -2 because this is the one that is not -1,0, 1

		List<Disc> actions = newActions(board, 0, GameBoard.AGENT); // list of actions
		for (int i = 0; i < actions.size(); i++) {
			int newValue = utilityFunction((MinValue(actions.get(i))).getBoard()); // utility function called as min
																					// value of the action
			if (newValue > value) { // if it is greater then updated the value and return that action where it will
									// be placed
				value = newValue;
				greatAction = actions.get(i).getColumn();
			}

		}
		long stopTime = System.nanoTime();
		System.out.println(stopTime-startTime); //basing how fast it goes off of time
		return greatAction;
	}

	/**
	 * This is the MaxValue function that returns a newValue based on the disc state
	 * that is passed in which is where the disc was placed. This was done following
	 * the pseudocode posted in the powerpoint. At first an actions list is made to
	 * iterate through and see if the one picked is smaller than the next. If it is
	 * smaller than the state passed in, then it is set as the actions because it is
	 * updated and returned.
	 * 
	 * @param state
	 * @return
	 */
	public Disc MaxValue(Disc state) {
		List<Disc> actions = newActions(state.getBoard(), state.getDepth() + 1, GameBoard.AGENT); // list of actions

		for (int i = 0; i < actions.size(); i++) { // for all of the actions, see if that action is less than the state
													// passed in
			if (utilityFunction(actions.get(i).getBoard()) < utilityFunction(state.getBoard())) {
				state = actions.get(i); // update value if the utility passes a less than value
			}
		}
		return state;

	}

	/**
	 * This is the MinValue function that returns a newValue based on the disc state
	 * that is passed in which is where the disc was placed. This was done following
	 * the pseudocode posted in the powerpoint. At first an actions list is made to
	 * iterate through and see if the one picked is bigger than the next. If it is
	 * smaller than the state passed in, then it is set as the actions because it is
	 * updated and returned.
	 * 
	 * @param state
	 * @return
	 */
	public Disc MinValue(Disc state) {
		List<Disc> actions = newActions(state.getBoard(), state.getDepth() + 1, GameBoard.AGENT); // list of actions
		for (int i = 0; i < actions.size(); i++) {
			if (utilityFunction(actions.get(i).getBoard()) > utilityFunction(state.getBoard())) { // checking to see if
																									// the value is
																									// greater and if it
																									// is set the state
																									// as that action
																									// and return it
				state = actions.get(i);
			}
		}
		return state;

	}

	/**
	 * A Utility function that looks at different states of the disc that is put in.
	 * It evaluates to see first whether it is the agent or the user and makes a
	 * plan of what column to choose. The idea of doing two connected, three
	 * connected, and four connected was taken from
	 * https://www.youtube.com/watch?v=IpTFe0H52JM. He went through some theory.
	 * 
	 * @param board
	 * @return either 2, -2, 3, -3, 5, or -5 based on the conditions
	 */
	public int utilityFunction(GameBoard board) {
		if (board.twoConnected() == GameBoard.AGENT) { // checks if the agent is connected with two and then returns the
														// value to 2
			return 2;
		} else if (board.twoConnected() == GameBoard.USER) { // checks if the user is connected and returns the value to
																// -2... and is the same for the rest of the lines
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
	 * This algorithm is newActions that returns a list that is making a deep copy
	 * of the board. You can only add a disc if the column has slots available and
	 * if it does not, then you cannot add it. The new disc is added to states.
	 * 
	 * @param board
	 * @param depth
	 * @param player
	 * @return states (list of disc states)
	 */
	public List<Disc> newActions(GameBoard board, int depth, int player) {

		states = new LinkedList<Disc>(); // list of disc states made at the beginning
		for (int columns = 0; columns < board.getMaxCols(); columns++) { // checking through all of the columns of the
																			// board
			GameBoard copy = board.copy(); // makes a copy of the board as a deep copy
			if (copy.isColumnFull(columns) == false) { // makes sure that the column is not full
				copy.addDisk(columns, player); // adds a disk in the copy of the board
				Disc newState = new Disc(copy, depth, columns); // makes a new state that holds the copy and the column
																// it adds to
				states.add(newState); // adds it to the list

			}

		}
		return states;

	}

}
