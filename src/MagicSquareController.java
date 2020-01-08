import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * This class handles a button press by the suer
 * @author tomar
 *
 */
public class MagicSquareController implements ActionListener {
	
	/**
	 * The model containing the game logic
	 */
	private MagicSquare model;
	
	/**
	 * Coordinates of the button
	 */
	private int i, j;
	
	/**
	 * New controller to listen to a button press
	 * @param i button row 
	 * @param j button col
	 * @param model
	 */
	public MagicSquareController(int i, int j, MagicSquare model) {
		this.model = model;
		this.i = i;
		this.j = j;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String num = JOptionPane.showInputDialog("Enter a number");
		try {
			int number = Integer.parseInt(num); //if not a number, it is invalide
			model.addNumber(i, j, number); //send to model for processing
			
		}catch (Exception exp) {
			//input was not a number (invalid) do nothing
		}

	}

}
