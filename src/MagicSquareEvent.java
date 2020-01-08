import java.util.EventObject;

/**
 * Create an event that the model can use to send to all its listeners
 * @author Mariam Almalki 101073670
 *
 */
public class MagicSquareEvent extends EventObject {
	
	/**
	 * the row, col, and value of a square
	 */
	private int i, j, num;
	
	/**
	 * Whether the game is in winning or done state
	 */
	private boolean won, done;
	
	public MagicSquareEvent(MagicSquare ms, int i, int j, int num, boolean won, boolean done) {
		super(ms);
		this.i = i;
		this.j = j;
		this.num = num;
		this.won = won;
		this.done = done;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public int getNum() {
		return num;
	}
	
	public boolean getWon() {
		return won;
	}
	
	public boolean getDone() {
		return done;
	}

}
