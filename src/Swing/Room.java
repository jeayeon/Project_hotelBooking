package Swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

public class Room extends JFrame {
	private JTable table = null;

	Room() {
		this.setLayout(new BorderLayout());
		this.setBounds(600, 200, 600, 400);

		tableset();
		Labelset(); 
		Buttonset();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	private void Buttonset() {
		// TODO Auto-generated method stub
		
	}

	private void Labelset() {
		// TODO Auto-generated method stub
		
	}

	private void tableset() {

	}

	public static void main(String[] args) {
		new Room();
	}

}
