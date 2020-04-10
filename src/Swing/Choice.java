package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Client.Cconnect;

public class Choice extends JFrame {
	private Cconnect cc = null;

	public Choice(Cconnect ccon) {
		this.cc = cc;
		this.setLayout(null); // 배치관리자 해제
		this.setBounds(0, 0, 250, 280);
		setLocationRelativeTo(null); // 바탕화면 한가운데 띄우기

		Buttonsetting();
		LabelSetting();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void LabelSetting() {
		ImageIcon hc1 = new ImageIcon("D:\\Chk1.jpg");
		Image hm1 = hc1.getImage(); // 이미지아이콘을 이미지로 변경
		hm1 = hm1.getScaledInstance(35, 35, Image.SCALE_SMOOTH); // 이미지 사이즈 조절
		hc1 = new ImageIcon(hm1); // 다시 이미지아이콘으로 변경

		JLabel labelImage1 = new JLabel(hc1);
		labelImage1.setBounds(50, 60, 35, 35);

		JLabel labelImage2 = new JLabel(hc1);
		labelImage2.setBounds(50, 120, 35, 35);

		this.add(labelImage1);
		this.add(labelImage2);
		
	}

	private void Buttonsetting() {
		JButton Booking = new JButton("객실 예약");
		Booking.setFont(new Font("돋움", Font.BOLD, 15));
		JButton Confirm = new JButton("예약 확인");
		Confirm.setFont(new Font("돋움", Font.BOLD, 15));

		Booking.setBorderPainted(false);
		Booking.setContentAreaFilled(false);
		Booking.setFocusPainted(false);

		Confirm.setBorderPainted(false);
		Confirm.setContentAreaFilled(false);
		Confirm.setFocusPainted(false);

		Booking.setBounds(72, 60, 120, 40);
		Confirm.setBounds(72, 120, 120, 40);

		this.add(Booking);
		this.add(Confirm);
		
		Booking.addMouseListener(new MouseListener() { // 객실 예약 버튼

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {//마우스를 땔때
				Booking.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {//마우스를 올려놨을때
				Booking.setForeground(Color.GRAY); //버튼색 바꾸게
			}

			@Override
			public void mouseClicked(MouseEvent e) {//마우스 클릭했을때
				newConfirm();
			}

		});
		
		

		Confirm.addMouseListener(new MouseListener() { // 예약 확인 버튼

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Confirm.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Confirm.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
//				new Reservation();
			}
		});
	}


	private void newConfirm() {
		this.setVisible(false);
		new Confirm(this);
		
	}

	public void setVisibletrue() {
		this.setVisible(true);
	}
//	public static void main(String[] args) {
//		new Choice();
//	}

}