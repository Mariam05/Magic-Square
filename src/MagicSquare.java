import java.util.ArrayList;
import java.util.List;

/**
 * A class implementing the logic of a magic square
 * @author Mariam Almalki 101073670
 *
 */
public class MagicSquare {

	/**
	 * The 'n' value. Could be changed
	 */
	public static final int SIZE = 3;

	/**
	 * The magic number
	 */
	private static final int MAGIC_NUMBER = SIZE * SIZE;

	/**
	 * The board/grid for the magic square
	 */
	private Integer[][] board;
	
	/**
	 * Whether or not the player has won
	 */
	private boolean won;
	
	/**
	 * The list of listeners to this model
	 */
	private List<MagicSquareListener> magicSquareListeners;

	public MagicSquare() {
		board = new Integer[SIZE][SIZE];
		magicSquareListeners = new ArrayList<>();
		won = false;

	}
	
	/**
	 * Add a listener to the magic square
	 * @param m
	 */
	public void addMagicSquareListener(MagicSquareView m) {
		magicSquareListeners.add(m);
	}

	/**
	 * Player adds a number to a square on the grid
	 * @param i the row position
	 * @param j the column position
	 * @param num the number added 
	 */
	public void addNumber(int i, int j, int num) {
		board[i][j] = num; //add the number to the grid
				
		if (checkFinished()) won = checkWon();
		
		MagicSquareEvent e = new MagicSquareEvent(this, i, j, won);
		
		for (MagicSquareListener m : magicSquareListeners) {
			m.handleMagicSquareEvent(e);
		}

	}

	public Integer[][] getBoard() {
		return board;
	}

	/**
	 * Check if the player has finished inputting all numbers in the square
	 * @return
	 */
	private boolean checkFinished() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == null)
					return false;
			}
		}

		return true;
	}

	/**
	 * Check if the player has won (i.e. achieved a magic square)
	 * @return
	 */
	private boolean checkWon() {
		
		//check each row
		int rowSum = 0;
		for (int i = 0; i < SIZE ; i ++) {
			for (int j = 0; j < SIZE ; j++) {
				rowSum += board[i][j];
			}
			
			if (rowSum != MAGIC_NUMBER) return false;
		}
		
		//check each column	
		int colSum = 0;
		for (int i = 0; i < SIZE ; i ++) {
			for (int j = 0; j < SIZE ; j++) {
				colSum += board[j][i];
			}
			
			if (colSum != MAGIC_NUMBER) return false;
		} 
		
		//check the two diagonals
		int diagSum = 0;
		
		//check top left to bottom right
		for (int i = 0; i < SIZE ; i++) { 
			diagSum += board[i][i];
		} 
		
		if (diagSum != MAGIC_NUMBER) return false;
		
		//check bottom left to top right
		diagSum = 0;
		int j = 0;
		for (int i = SIZE - 1; i >= 0 ; i--) {
			diagSum += board[i][j];
			j++;
		}
		
		if (diagSum != MAGIC_NUMBER) return false;
		
		return true;

	}

	public static void main(String[] args) {

		MagicSquare m = new MagicSquare();
		
	}

}
