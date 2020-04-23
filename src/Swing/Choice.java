package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Client.Cconnect;
import JDBC.BDTO;
import JDBC.MDTO;
import JDBC.RDTO;

public class Choice extends JFrame {
	private Cconnect cc = null;
	private Reservation rs = null;
	private Hmain Hm = null;
	private ArrayList<BDTO> bList = new ArrayList<>();

	public Choice(Cconnect ccon) {
		Hm = Hm.getInstance(ccon);
		this.cc = ccon;
		this.setLayout(null); // 배치관리자 해제
		this.setBounds(0, 0, 250, 280);
		setLocationRelativeTo(null); // 바탕화면 한가운데 띄우기

		bListset();
		Buttonsetting();
		LabelSetting();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void bListset() {
		try {
			cc.send("bookingList:");
			Thread.sleep(400);
			bList = (ArrayList<BDTO>) cc.getrList();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void LabelSetting() {
		ImageIcon hc1 = new ImageIcon("D:\\Chk1.jpg");
		Image hm1 = hc1.getImage(); // 이미지아이콘을 이미지로 변경
		hm1 = hm1.getScaledInstance(35, 35, Image.SCALE_SMOOTH); // 이미지 사이즈 조절
		hc1 = new ImageIcon(hm1); // 다시 이미지아이콘으로 변경

		JLabel labelImage1 = new JLabel(hc1);
		labelImage1.setBounds(50, 50, 35, 35);

		JLabel labelImage2 = new JLabel(hc1);
		labelImage2.setBounds(50, 100, 35, 35);

		JLabel labelImage3 = new JLabel(hc1);
		labelImage3.setBounds(50, 150, 35, 35);

		this.add(labelImage1);
		this.add(labelImage2);
		this.add(labelImage3);

	}

	private void Buttonsetting() {
		JButton Booking = new JButton("회원 예약");
		Booking.setFont(new Font("돋움", Font.BOLD, 15));

		JButton Booking2 = new JButton("비회원 예약");
		Booking.setFont(new Font("돋움", Font.BOLD, 15));
		JButton Confirm = new JButton("예약 확인");
		Confirm.setFont(new Font("돋움", Font.BOLD, 15));

		Booking.setBorderPainted(false);
		Booking.setContentAreaFilled(false);
		Booking.setFocusPainted(false);

		Booking2.setBorderPainted(false);
		Booking2.setContentAreaFilled(false);
		Booking2.setFocusPainted(false);

		Confirm.setBorderPainted(false);
		Confirm.setContentAreaFilled(false);
		Confirm.setFocusPainted(false);

		Booking.setBounds(72, 50, 120, 40);
		Booking2.setBounds(72, 100, 120, 40);
		Confirm.setBounds(72, 150, 120, 40);

		this.add(Booking);
		this.add(Booking2);
		this.add(Confirm);

		Booking.addMouseListener(new MouseListener() { // 회원 예약 버튼

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우스를 땔때
				Booking.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {// 마우스를 올려놨을때
				Booking.setForeground(Color.GRAY); // 버튼색 바꾸게
			}

			@Override
			public void mouseClicked(MouseEvent e) {// 마우스 클릭했을때
				if (!Hm.getId().equals("")) {
					newReservation();
				} else {
					JOptionPane.showMessageDialog(null, "회원전용이니 로그인 ㄱㄱ", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});
		Booking2.addMouseListener(new MouseListener() { // 비회원 예약 버튼

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우스를 땔때
				Booking2.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {// 마우스를 올려놨을때
				Booking2.setForeground(Color.GRAY); // 버튼색 바꾸게
			}

			@Override
			public void mouseClicked(MouseEvent e) {// 마우스 클릭했을때
				if (Hm.getId().equals("")) {
					newReservation();
				} else {
					JOptionPane.showMessageDialog(null, "회원이시니 회원예약", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
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
				if (!Hm.getId().equals("")) {// 회원이면
					 new mConfirm(Hm,bList);
					dispose();

				} else {
					new bConfirm(bList);
					dispose();
				}
			}
		});
	}

	private void newReservation() {
		this.setVisible(false);
		rs = new Reservation(this, cc);

	}

	public void setVisibletrue() {
		this.setVisible(true);
	}
//	public static void main(String[] args) {
//		new Choice();
//	}

}