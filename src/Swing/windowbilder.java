package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class windowbilder extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 303, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		

		
		JLabel renumList = new JLabel("예약리스트");
		renumList.setBounds(125, 0, 68, 15);
		contentPane.add(renumList);

		
		
		JLabel renum = new JLabel("예약번호:");
		renum.setBounds(45, 136, 68, 21);
		contentPane.add(renum);
		
		JLabel name = new JLabel("성      명:");
		name.setBounds(45, 167, 68, 21);
		contentPane.add(name);
		
		JLabel chkin = new JLabel("체 크  인:");
		chkin.setBounds(45, 198, 68, 21);
		contentPane.add(chkin);
		
		JLabel chkout = new JLabel("체크아웃:");
		chkout.setBounds(45, 229, 68, 21);
		contentPane.add(chkout);
		
		JLabel fewday = new JLabel("숙박기간:");
		fewday.setBounds(45, 260, 68, 21);
		contentPane.add(fewday);
		
		JLabel total = new JLabel("합      계:");
		total.setBounds(45, 291, 68, 21);
		contentPane.add(total);
		
		
		
		
		
		
	}
}
