import java.util.EventObject;

public class MagicSquareEvent extends EventObject {
	
	private int i, j;
	private boolean won;
	
	public MagicSquareEvent(MagicSquare ms, int i, int j, boolean won) {
		super(ms);
		this.i = i;
		this.j = j;
		this.won = won;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public boolean getWon() {
		return won;
	}

}
