import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is the view for the magic square and implements listener such that
 * when the model makes a change it can handle it. 
 * @author tomar
 *
 */
public class MagicSquareView extends JFrame implements MagicSquareListener {

	
	private MagicSquare model;
	
	private JButton[][] buttons;
	
	private int size;
	
	/**
	 * Create a new jframe
	 * @param model the MagicSquare logic
	 */
	public MagicSquareView(MagicSquare model) {
		super();
		this.model = model;
		size = model.SIZE;
		buttons = new JButton[size][size];
		model.addMagicSquareListener(this);
		
		setLayout(new GridLayout(size, size));
		setSize(size*100, size*100);
		setTitle("Magic Square");
		
		initializeButtons();
		
		setVisible(true);
	}
	
	/**
	 * Initialize all the JButtons
	 */
	private void initializeButtons() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				buttons[i][j] = new JButton("");
				buttons[i][j].addActionListener(new MagicSquareController(i, j, model));
				
				buttons[i][j].setVisible(true);
				add(buttons[i][j]);
			}
		}
		
	}


	/**
	 * Handle a notification from the model based on the state of the game at that point in time. 
	 */
	public void handleMagicSquareEvent(MagicSquareEvent e) {
	
		buttons[e.getI()][e.getJ()].setText(e.getNum() + ""); //set the text of that square
		
		String message = "";
		
		if (e.getDone()) {
			if (e.getWon()) {
				message += "Congratulations! You won!";
				
			} else {
				message += "Sorry, that's not the correct solution.";
			}
			
			message += "\n Would you like to play again?";
			
			int i = JOptionPane.showConfirmDialog(null, message, "MagicSquare",  JOptionPane.YES_NO_OPTION);
			if (i == 0) { //yes pressed
				model.resetGame();
			} else {
				dispose();
			}
			
		}
		
	}

	/**
	 * Reset the board by removing all values
	 */
	@Override
	public void handleResetGame() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				buttons[i][j].setText("");
			}
		}
		
	}
	
	public static void main(String[] args) {
		MagicSquare m = new MagicSquare();
		
		new MagicSquareView(m);
	}

}
