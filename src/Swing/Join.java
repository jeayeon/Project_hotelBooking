package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Client.Cconnect;

public class Join extends JFrame {

	private JLabel[] LabelList = new JLabel[8];
	private String[] LabelName = { "아이디", "비밀번호", "성명", "전화번호", "주소", "회원 정보 입력", "", "" };
	private JTextField[] TextList = new JTextField[5];
	private int[] y = { 100, 145, 190, 235, 280, 15, 123, 73 };
	private JButton add, idcheck;
	private Cconnect Ccon = null;
	private String idCheck = "";
	private Hmain Hm = null;

	Join(Cconnect c) {
		super("회원가입");
		this.Ccon = c;
		this.Hm = Hmain.getInstance(c);
		this.setLayout(null);// 기본 프레임레이아웃이 보더레이아웃이라 안해도됨
		this.setBounds(100, 200, 350, 440);
		this.setLocationRelativeTo(null); // 창 가운데로 오게하는 메소드

		LabelSet();
		TextField();
		ButtonSet();

		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true); // 창을열고닫고하게하는 메소드

	}

	private void ButtonSet() {
		idcheck = new JButton("중복 확인");
		idcheck.setBounds(220, 100, 90, 20);
		idcheck.setFont(new Font("돋움", Font.PLAIN, 12));
		this.add(idcheck);

		idcheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				idchecking();
			}
		});

		add = new JButton("가입 신청");
		add.setFont(new Font("돋움", Font.PLAIN, 12));
		add.setBounds(120, 340, 90, 25);
		this.add(add);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				idchecking();
				//System.out.println("같은데 가입신청버튼 "+idCheck);
				try {
					Thread.sleep(100);
					memberJoin();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

	}
	private void idchecking() {
		String id = new String("idchk:" + TextList[0].getText());
			Ccon.send(id);
			
	}

	private void memberJoin() {// 회원가입 메서드
		String sendinfo = "";
		String[] memberInfo = new String[7];
		for (int i = 0; i < memberInfo.length; i++) {
			if (i < 5) {
				memberInfo[i] = TextList[i].getText();
			} else if (i == 5) { // 포인트
				memberInfo[i] = "0";
			} else if (i == 6) {// 등급 회원은 2 비회원은 1로
				memberInfo[i] = "2";
			}
		}
		System.out.println(idCheck);
		if (idCheck.equals("@yes")) { // id 중복확인 완료가된후
			int id = memberInfo[0].length();
			if (id <= 15 && id >= 3) {// 아이디가 3~15글자사이
				sendinfo = sendinfo + memberInfo[0] + " ";
			}
		}
		if (memberInfo[1].length() <= 15 && memberInfo[1].length() >= 3) {// 비밀번호가 3~15글자사이

			sendinfo = sendinfo + memberInfo[1] + " ";
		}

		sendinfo = sendinfo + memberInfo[2] + " ";
		sendinfo = sendinfo + memberInfo[3] + " ";
		sendinfo = sendinfo + memberInfo[4] + " ";
		sendinfo = sendinfo + memberInfo[5] + " ";
		sendinfo = sendinfo + memberInfo[6] + " ";

		StringTokenizer st = new StringTokenizer(sendinfo);

//		System.out.println(idCheck);
		  
			if (idCheck.equals("@yes")) {
				if (st.countTokens() == 7) {
//					System.out.println("패스");
//					if (LabelList[6].getText().equals("회원가입이 가능한 아이디입니다")) {
						Ccon.send("info:" + sendinfo);
						JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						Hm.setVisibertrue();
//					} 
				}
			}else {
				LabelList[7].setText("중복확인 및 아이디,비밀번호 3~15글자사이로입력");
				LabelList[7].setForeground(Color.red);
			}

	}

	public void idchk(String msg) {
		if (msg.equals("@yes")) {// 중복이 아닐때
			LabelList[6].setText("회원가입이 가능한 아이디입니다");
			LabelList[6].setForeground(Color.blue);
			idCheck = msg;

		} else if (msg.equals("@no")) { // 중복일때
			LabelList[6].setText("아이디 중복입니다 ");
			LabelList[6].setForeground(Color.red);
			idCheck = msg;
		}
		//System.out.println("join idcheck 메소드 "+idCheck);

	}

	private void TextField() {
		for (int i = 0; i < TextList.length; i++) {
			if (i == 4) {
				TextList[i] = new JTextField(30);
				TextList[i].setBounds(100, y[i], 220, 20);
				this.add(TextList[i]);
			} else {
				TextList[i] = new JTextField(8);
//			TextList[i].setPreferredSize(new Dimension(30, 50)); 패널 로 작업시 크기변경
				TextList[i].setBounds(100, y[i], 100, 20);
				this.add(TextList[i]);

			}
		}
	}

	private void LabelSet() {
		JLabel labelLineS = new JLabel("──────────────────────");
		labelLineS.setBounds(25, 60, 320, 20);
		this.add(labelLineS);

		for (int i = 0; i < LabelList.length; i++) {
			LabelList[i] = new JLabel(LabelName[i]);
			LabelList[i].setBounds(20, y[i], 100, 20);

			if (i == 5) {
				LabelList[i] = new JLabel(LabelName[i]);
				LabelList[i].setBounds(100, y[i], 150, 30);
				LabelList[i].setFont(new Font("돋움", Font.BOLD, 17));
			} else if (i == 6) { // 아이디중복체크누르면 텍스트띄워주는라벨
				LabelList[i] = new JLabel(LabelName[i]);
				LabelList[i].setBounds(100, y[i], 220, 15);
				LabelList[i].setFont(new Font("돋움", Font.PLAIN, 9));

			} else if (i == 7) { // 가입시 잘못된거띄워주는
				LabelList[i] = new JLabel(LabelName[i]);
				LabelList[i].setBounds(50, y[i], 300, 15);
				LabelList[i].setFont(new Font("돋움", Font.PLAIN, 9));

			}

			this.add(LabelList[i]);
		}
	}

}
