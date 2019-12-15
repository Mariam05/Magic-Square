import java.util.EventObject;

public class MagicSquareEvent extends EventObject {
	
	private int i, j, num;
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
