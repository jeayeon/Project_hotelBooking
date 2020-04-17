package Swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.Cconnect;
import JDBC.MDTO;

public class Hmain extends JFrame implements ActionListener {

	private JLabel imageL;
	private JPanel Sp = new JPanel();
	private JButton loginBtn, joinBtn, nonMBtn;
	private ImageIcon img = null;
	private Cconnect Ccon = null;
	private Join Join = null;
	private Login Login = null;
	public static Hmain Hm = null;

	private Hmain(Cconnect c) {
		super("호텔재해");
		this.Ccon = c;
		this.setLayout(new BorderLayout());// 기본 프레임레이아웃이 보더레이아웃이라 안해도됨
		this.setBounds(100, 200, 700, 600);
		this.setLocationRelativeTo(null); // 창 가운데로 오게하는 메소드
		North();
		Center();

		this.setVisible(true); // 창을열고닫고하게하는 메소드
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public static Hmain getInstance(Cconnect c) {
		if (Hm == null) {
			Hm = new Hmain(c);
		}
		return Hm;

	}

	private void North() {
		JPanel nP = new JPanel();
//		nP.setLayout(new FlowLayout(FlowLayout.RIGHT)); // 오른쪽 정렬
		nP.setLayout(new BorderLayout());
		JPanel hotelP = new JPanel();
		JLabel hotelLable = new JLabel("HOTEL");
		hotelLable.setFont(new Font("Serif", Font.BOLD, 40)); // 글씨체, 굵기, 크기
		hotelP.add(hotelLable);
		nP.add(hotelP, "Center");

		// 상단 로그인,회원가입,비회원예약 버튼
		JPanel loginP = new JPanel();
		loginP.setLayout(new FlowLayout(FlowLayout.RIGHT)); // 오른쪽정렬

		loginBtn = new JButton("로그인");
		loginBtn.setFont(new Font("돋움", Font.PLAIN, 12));
		loginBtn.setBorderPainted(false); // 버튼 선 제거
		loginBtn.setContentAreaFilled(false); // 버튼 색 제거
		loginBtn.setFocusPainted(false); // 버튼 선택선 제거
		loginP.add(loginBtn);

		joinBtn = new JButton("회원가입");
		joinBtn.setFont(new Font("돋움", Font.PLAIN, 12));
		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setFocusPainted(false);
		loginP.add(joinBtn);

		nonMBtn = new JButton("비회원예약");
		nonMBtn.setFont(new Font("돋움", Font.PLAIN, 12));
		nonMBtn.setBorderPainted(false);
		nonMBtn.setContentAreaFilled(false);
		nonMBtn.setFocusPainted(false);
		loginP.add(nonMBtn);
		nP.add(loginP, "North");

		loginBtn.addActionListener(this);
		joinBtn.addActionListener(this);
		nonMBtn.addActionListener(this);
		this.add(nP, "North");

	}

	private void Center() {

		img = new ImageIcon("D:\\hotel.jpg");
		Image hm = img.getImage(); // 이미지아이콘을 이미지로 변경
		hm = hm.getScaledInstance(700, 460, Image.SCALE_SMOOTH); // 이미지 사이즈 조절
		img = new ImageIcon(hm); // 다시 이미지아이콘으로 변경
		imageL = new JLabel(img);
		this.add(imageL, "Center");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object Click = e.getSource();
		if (Click.equals(loginBtn)) { // 로그인버튼 누를때
			Login = new Login(Ccon);
			this.setVisible(false);
		} else if (Click.equals(joinBtn)) { // 회원가입버튼 누를때
			Join = new Join(Ccon);
			this.setVisible(false);
		} else if (Click.equals(nonMBtn)) { // 비회원예약버튼 누를때
//			new Choice(Ccon);
			this.setVisible(false);
		}

	}

	public void setVisibertrue() {
		this.setVisible(true);
	}

	public void join(String msg) {
		this.Join.idchk(msg);

	}

	public void Login(String msg) {
		this.Login.send(msg);
	}

}
