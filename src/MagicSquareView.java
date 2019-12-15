import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MagicSquareView extends JFrame implements MagicSquareListener {

	
	private MagicSquare model;
	
	private JButton[][] buttons;
	
	private int size;
	
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
			
			JOptionPane.showMessageDialog(this, message);
				
		}
		
	}
	
	public static void main(String[] args) {
		MagicSquare m = new MagicSquare();
		new MagicSquareView(m);
	}
}
