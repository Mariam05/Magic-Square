import java.util.ArrayList;
import java.util.List;

/**
 * A class implementing the logic of a magic square
 * 
 * @author Mariam Almalki 101073670
 *
 */
public class MagicSquare {

	/**
	 * The 'n' value. Could be changed
	 */
	public final int SIZE = 3;

	/**
	 * The magic number
	 */
	private final int LIMIT = SIZE * SIZE;

	/**
	 * The board/grid for the magic square
	 */
	private int[][] board;

	/**
	 * Whether or not the player has won, or is done
	 */
	private boolean won, done;

	/**
	 * The list of listeners to this model
	 */
	private List<MagicSquareListener> magicSquareListeners;

	public MagicSquare() {
		board = new int[SIZE][SIZE];
		magicSquareListeners = new ArrayList<>();
		won = false;
		done = false;

	}

	/**
	 * Add a listener to the magic square
	 * 
	 * @param m
	 */
	public void addMagicSquareListener(MagicSquareView m) {
		magicSquareListeners.add(m);
	}

	/**
	 * Player adds a number to a square on the grid
	 * 
	 * @param i   the row position
	 * @param j   the column position
	 * @param num the number added
	 */
	public void addNumber(int i, int j, int num) {

		if (num > LIMIT || num < 1)
			return; // input is out of range don't do anything

		for (int r = 0; r < SIZE; r++) { // check if number already exists in the square (i.e. already added)
			for (int c = 0; c < SIZE; c++) {
				if (board[r][c] == num)
					return;
			}
		}

		board[i][j] = num; // add the number to the grid

		done = checkFinished();

		if (done)
			won = checkWon(); // if the entire board is filled up check if the player won

		MagicSquareEvent e = new MagicSquareEvent(this, i, j, num, won, done); // create a new magic square event

		// make all the listeners handle the event
		for (MagicSquareListener m : magicSquareListeners) {
			m.handleMagicSquareEvent(e);
		}

	}
	
	
	public void resetGame() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = 0; // 0 is the default value for int (primitive)
			}
		}
		// make all the listeners handle the reset
		for (MagicSquareListener m : magicSquareListeners) {
			m.handleResetGame();
		}
		

	}

	/**
	 * Check if the player has finished inputting all numbers in the square
	 * 
	 * @return
	 */
	private boolean checkFinished() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == 0) // 0 is the default value for int (primitive)
					return false;
			}
		}
		
		

		return true;
	}

	/**
	 * Check if the player has won (i.e. achieved a magic square)
	 * 
	 * @return
	 */
	private boolean checkWon() {

		// check the two diagonals, set the first diagonal value as the magic number to compare against
		int diagSum = 0, magicNum = 0;

		// set the magic number as the sum of the first diagonal (from top left to bottom right)
		for (int i = 0; i < SIZE; i++) {
			magicNum += board[i][i];
			
		}

		// check bottom left to top right
		for (int i = SIZE - 1, j = 0; i >= 0; i--, j++) {
			diagSum += board[i][j];		
		}
		if (diagSum != magicNum)
			return false;

		// check each row
		int rowSum = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				rowSum += board[i][j];
			}

			if (rowSum != magicNum)
				return false;
			rowSum = 0;
		}

		// check each column
		int colSum = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				colSum += board[j][i];
			}
			if (colSum != magicNum)
				return false;
			colSum = 0;
		}

		return true;

	}

}
