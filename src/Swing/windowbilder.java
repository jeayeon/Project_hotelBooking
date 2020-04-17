package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class windowbilder extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton reser ,back;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowbilder frame = new windowbilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public windowbilder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 0, 299, 277);
		contentPane.add(table);
		
		 back = new JButton("<이전");
		back.setBounds(12, 317, 97, 23);
		contentPane.add(back);
		
		JLabel lblNewLabel = new JLabel("<html>광고주를모십니다<br> 사용법: 방을클릭하면 현재 글이써져있는곳에<br> 사진이출력되고 예약하기버튼을누르시면됩니다</html>");
		lblNewLabel.setBounds(311, 10, 261, 267);
		contentPane.add(lblNewLabel);
		
		reser = new JButton("예약하기");
		reser.setBounds(397, 317, 97, 23);
		contentPane.add(reser);
	}
}
