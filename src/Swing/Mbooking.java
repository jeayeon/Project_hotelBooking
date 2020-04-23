package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Client.Cconnect;
import JDBC.MDTO;
import JDBC.RDTO;

public class Mbooking extends JFrame implements ActionListener {
	private JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6,
			textField_7;
	private JButton button_1, button_2;
	private JLabel label, label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8, label_9, label_10,
			label_11, allCheckLabel;
	private Hmain Hm = null;
	private Reservation rv = null;
	private Cconnect cc = null;
	private String id = "";
	private String total = "";
	private String renum = "";
	private int row;
	private Random r = new Random();
	private ArrayList<MDTO> mInfo = null; // 로그인한 사람 정보
	private ArrayList<String> bInfo = null; // 체크인,체크아웃,박,인원 순으로 0~3인덱스
	private ArrayList<RDTO> rList = null; // 부킹리스트에 저장해야할 방정보 몇박인지에따른 숙박계산
											// 및 방이름 저장

	public Mbooking(Reservation rv, Cconnect cc, ArrayList<RDTO> rList, ArrayList<MDTO> mInfo, int row) {
		super("회원예약");

		Hm = Hm.getInstance(cc);
		this.cc = cc;
		this.mInfo = mInfo; // 로그인사람정보
		this.rList = rList; // 선택한 방정보(방이름,가격)
		this.row = row;// 부킹리스트에서 내가 선택한 인덱스
		this.rv = rv;
		this.id = Hm.getId(); // 0 1 2 3
		this.bInfo = rv.getbInfo(); // 리저베이션 텍스트필드에서 가져온 값 체크인,체크아웃,박,인원

		this.setLayout(null); // 배치관리자 해제
		this.setBounds(0, 0, 635, 385);
		setLocationRelativeTo(null); // 바탕화면 한가운데 띄우기

		labelSetting();
		textFieldSetting();
		buttonSetting();
		textFieldSettext();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private String bInfoset() {
		renum = r.nextInt(89999999) + 10000000 + "";
		System.out.println("예약번호:" + renum);
		String[] bbinfo = new String[9];
		bbinfo[0] = renum; // 예약번호
		bbinfo[1] = id; // 아이디
		bbinfo[2] = textField.getText(); // 이름
		bbinfo[3] = rList.get(row).getRoom();// 방이름
		bbinfo[4] = bInfo.get(3); // 인원
		bbinfo[5] = bInfo.get(0); // 체크인
		bbinfo[6] = bInfo.get(1); // 체크아웃
		bbinfo[7] = bInfo.get(2); // 몇박인지
		bbinfo[8] = total; // 토탈가격

		String sendinfo = "";
		for (int i = 0; i < bbinfo.length; i++) {
			sendinfo = sendinfo + bbinfo[i] + " ";
		}
		return sendinfo;
	}

	private void buttonSetting() {
		button_1 = new JButton("< 이전");
		button_1.setFont(new Font("돋움", Font.PLAIN, 12));
		button_1.setBorderPainted(false);
		button_1.setContentAreaFilled(false);
		button_1.setFocusPainted(false);
		button_1.setBounds(18, 304, 80, 23);
		this.add(button_1);

		button_1.addMouseListener(new MouseListener() { // 나가기 버튼

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button_1.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				button_1.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		button_2 = new JButton("예약하기");
		button_2.setFont(new Font("돋움", Font.PLAIN, 12));
		button_2.setFocusPainted(false);
		button_2.setBounds(514, 272, 90, 30);
		this.add(button_2);

		button_2.addActionListener(this); // 이 클래스 자체에서 액션리스너를 처리하도록

	}

	private void textFieldSettext() {
		String name = Hm.getId();
		int peo = Integer.parseInt(bInfo.get(2));
		int price = rList.get(row).getPrice();
		total = (peo * price) + "";

		if (name != "") {
			textField.setText(name);// 성명
			textField_1.setText(mInfo.get(0).getTel());// 전화번호
			textField_2.setText(mInfo.get(0).getAddr());// 주소
			textField_7.setText(total);
		} else {
			textField_7.setText(total);
		}

	}

	private void textFieldSetting() {
		textField = new JTextField();// 성명
		textField.setColumns(10);
		textField.setBounds(104, 92, 130, 30);
		this.add(textField);

		textField_1 = new JTextField();// 연락처
		textField_1.setColumns(10);
		textField_1.setBounds(104, 145, 130, 30);
		this.add(textField_1);

		textField_1.addKeyListener(new KeyAdapter() { // 연락처 글자수 제한
			public void keyTyped(KeyEvent k) {
				JTextField src = (JTextField) k.getSource();
				if (src.getText().length() >= 11) {
					k.consume();
				}
			}
		});

		textField_2 = new JTextField(); // 주소
		textField_2.setColumns(10);
		textField_2.setBounds(104, 198, 130, 30);
		this.add(textField_2);

		textField_3 = new JTextField();// 카드종류
		textField_3.setColumns(10);
		textField_3.setBounds(376, 92, 130, 30);
		this.add(textField_3);

		textField_4 = new JTextField();// 카드번호
		textField_4.setColumns(10);
		textField_4.setBounds(376, 145, 130, 30);
		this.add(textField_4);

		textField_4.addKeyListener(new KeyAdapter() { // 카드번호 글자수 제한
			public void keyTyped(KeyEvent k) {
				JTextField src = (JTextField) k.getSource();
				if (src.getText().length() >= 16) {
					k.consume();
				}
			}
		});

		textField_5 = new JTextField();// 유효기간 월
		textField_5.setColumns(10);
		textField_5.setBounds(376, 198, 30, 30);
		this.add(textField_5);

		textField_5.addKeyListener(new KeyAdapter() { // 유효기간 글자수 제한
			public void keyTyped(KeyEvent k) {
				JTextField src = (JTextField) k.getSource();
				if (src.getText().length() >= 2) {
					k.consume();
				}
			}
		});

		textField_6 = new JTextField();// 유효기간 일
		textField_6.setColumns(10);
		textField_6.setBounds(447, 198, 30, 30);
		this.add(textField_6);

		textField_6.addKeyListener(new KeyAdapter() { // 유효기간 글자수 제한
			public void keyTyped(KeyEvent k) {
				JTextField src = (JTextField) k.getSource();
				if (src.getText().length() >= 2) {
					k.consume();
				}
			}
		});

		textField_7 = new JTextField(); // 요금합계
		textField_7.setColumns(10);
		textField_7.setBounds(372, 273, 130, 30);
		this.add(textField_7);

	}

	private void labelSetting() {
		allCheckLabel = new JLabel(); // 필수기재사항 체크
		allCheckLabel.setFont(new Font("돋움", Font.PLAIN, 12));
		allCheckLabel.setForeground(Color.RED);
		allCheckLabel.setBounds(40, 10, 300, 20);
		this.add(allCheckLabel);

		label = new JLabel("고객정보");
		label.setFont(new Font("돋움", Font.PLAIN, 14));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(37, 28, 68, 33);
		this.add(label);

		label_1 = new JLabel("성   명");
		label_1.setFont(new Font("돋움", Font.PLAIN, 13));
		label_1.setHorizontalAlignment(JLabel.CENTER);
		label_1.setBounds(34, 92, 57, 33);
		this.add(label_1);

		label_2 = new JLabel("연락처");
		label_2.setFont(new Font("돋움", Font.PLAIN, 13));
		label_2.setHorizontalAlignment(JLabel.CENTER);
		label_2.setBounds(34, 145, 57, 33);
		this.add(label_2);

		label_3 = new JLabel("주   소");
		label_3.setFont(new Font("돋움", Font.PLAIN, 13));
		label_3.setHorizontalAlignment(JLabel.CENTER);
		label_3.setBounds(34, 198, 57, 33);
		this.add(label_3);

		label_4 = new JLabel("신용카드정보");
		label_4.setFont(new Font("돋움", Font.PLAIN, 14));
		label_4.setHorizontalAlignment(JLabel.CENTER);
		label_4.setBounds(301, 28, 105, 33);
		this.add(label_4);

		label_5 = new JLabel("카드종류");
		label_5.setFont(new Font("돋움", Font.PLAIN, 13));
		label_5.setHorizontalAlignment(JLabel.CENTER);
		label_5.setBounds(309, 92, 57, 33);
		this.add(label_5);

		label_6 = new JLabel("카드번호");
		label_6.setFont(new Font("돋움", Font.PLAIN, 13));
		label_6.setHorizontalAlignment(JLabel.CENTER);
		label_6.setBounds(309, 145, 57, 33);
		this.add(label_6);

		label_7 = new JLabel("유효기간");
		label_7.setFont(new Font("돋움", Font.PLAIN, 13));
		label_7.setHorizontalAlignment(JLabel.CENTER);
		label_7.setBounds(309, 198, 57, 33);
		this.add(label_7);

		label_8 = new JLabel("월");
		label_8.setFont(new Font("돋움", Font.PLAIN, 13));
		label_8.setHorizontalAlignment(JLabel.CENTER);
		label_8.setBounds(410, 198, 30, 33);
		this.add(label_8);

		label_9 = new JLabel("년");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("돋움", Font.PLAIN, 13));
		label_9.setBounds(480, 198, 30, 33);
		this.add(label_9);

		label_10 = new JLabel("요금합계");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("돋움", Font.PLAIN, 13));
		label_10.setBounds(308, 271, 57, 33);
		this.add(label_10);

		label_11 = new JLabel("*부가가치세 및 봉사금 포함");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("돋움", Font.PLAIN, 11));
		label_11.setBounds(316, 299, 240, 33);
		this.add(label_11);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object Click = e.getSource();
		if (Click.equals(button_2)) {// 예약하기 버튼 누를시
			System.out.println("나오긴하냐?");
			String binfo = bInfoset();
			cc.send("booadd:" + binfo);
			JOptionPane.showMessageDialog(null, "예약번호:" + renum + "\n예약이 완료되었습니다", "Message",
					JOptionPane.INFORMATION_MESSAGE);
			Hm.setVisibertrue();
			rv.dispose();
			dispose();
		}

	}

//	public static void main(String[] args) {
//		new Mbooking();
//	}

}
