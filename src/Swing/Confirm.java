package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import Client.Cconnect;

public class Confirm extends JFrame {

	private TitledBorder tborder;
	private JLabel fewday, dayin, dayout;
	private Choice ch = null;

	Confirm(Choice choice) {
		super("객실 예약");
		ch =choice;
		this.setLayout(null);
		this.setBounds(100, 100, 620, 320);

		Labelset();
		ButtonSet();
		ComboboxSet();

		this.setVisible(true);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

	}

	private void Labelset() {

		tborder = new TitledBorder(""); // 라벨에 테두리를 입히기위한 타이틀보더
		// tborder.setTitlePosition(TitledBorder.ABOVE_TOP);// 지정한 위치에 타이틀을 나타내주는
		// 보더(라벨위에 글씨를적을수있다)
		tborder.setTitleJustification(TitledBorder.CENTER);// 자리맞춤을 가운데로 지정...

		JLabel chkin = new JLabel("체크인");
		chkin.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		chkin.setBounds(47, 100, 57, 15);
		this.add(chkin);

		JLabel title = new JLabel("날짜 · 인원 선택");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		title.setBounds(250, 10, 154, 37);
		this.add(title);

		JLabel chkout = new JLabel("체크아웃");
		chkout.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		chkout.setBounds(242, 101, 57, 15);
		this.add(chkout);

		JLabel today = new JLabel("박");
		today.setFont(new Font("굴림", Font.PLAIN, 11));
		today.setBounds(444, 138, 19, 28);
		this.add(today);

		JLabel adult = new JLabel("성인");
		adult.setFont(new Font("굴림", Font.PLAIN, 12));
		adult.setBounds(471, 120, 26, 15);
		this.add(adult);

		JLabel child = new JLabel("아동");
		child.setFont(new Font("굴림", Font.PLAIN, 12));
		child.setBounds(520, 120, 26, 15);
		this.add(child);

		JButton search = new JButton("검 색");
		search.setFont(new Font("굴림", Font.PLAIN, 16));
		search.setBounds(250, 190, 138, 42);
		this.add(search);

		JLabel people1 = new JLabel("명"); // 성인
		people1.setFont(new Font("굴림", Font.PLAIN, 11));
		people1.setBounds(500, 138, 19, 28);
		this.add(people1);

		JLabel people2 = new JLabel("명"); // 아동
		people2.setFont(new Font("굴림", Font.PLAIN, 11));
		people2.setBounds(561, 138, 19, 28);
		this.add(people2);

		dayin = new JLabel(""); // 체크인 날짜 출력라벨
		dayin.setOpaque(true);
		dayin.setBackground(Color.WHITE);
		dayin.setBounds(47, 138, 145, 28);
		dayin.setBorder(tborder);
		this.add(dayin);

		dayout = new JLabel(""); // 체크아웃 날짜 출력라벨
		dayout.setOpaque(true);
		dayout.setBackground(Color.WHITE);
		dayout.setBounds(242, 137, 145, 29);
		dayout.setBorder(tborder);
		this.add(dayout);

		fewday = new JLabel(""); // 몇박인지 표시해줄 라벨
		fewday.setOpaque(true); // 라벨 테두리색 변경을위해 true로 라벨 기본은 투명색이다.
		fewday.setBackground(Color.WHITE);
		fewday.setBounds(411, 138, 26, 28);
		fewday.setBorder(tborder);
		this.add(fewday);

	}

	private void ButtonSet() {
		ImageIcon icon = new ImageIcon("D:\\calendar1.jpg");
		Image ImageSet = icon.getImage();
		ImageSet = ImageSet.getScaledInstance(35, 30, Image.SCALE_SMOOTH);
		icon = new ImageIcon(ImageSet);
		
		ImageIcon icon2 = new ImageIcon("D:\\calendar2.jpg");
		Image ImageSet2 = icon2.getImage();
		ImageSet2 = ImageSet2.getScaledInstance(35, 30, Image.SCALE_SMOOTH);
		icon2 = new ImageIcon(ImageSet2);

		JButton checkinB = new JButton(icon); // 체크인 달력버튼
		checkinB.setBounds(105, 97, 35, 30);
		checkinB.setRolloverIcon(icon2); // 버튼에 마우스 올렸을때 출력되는 이미지아이콘
		checkinB.setBorderPainted(false);
		checkinB.setContentAreaFilled(false);
		checkinB.setFocusPainted(false);
		this.add(checkinB);
		checkinB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});

		JButton checkoutB = new JButton(icon); // 체크아웃 달력버튼
		checkoutB.setBounds(305, 97, 35, 30);
		checkoutB.setRolloverIcon(icon2); // 버튼에 마우스 올렸을때 출력되는 이미지아이콘
		checkoutB.setBorderPainted(false);
		checkoutB.setContentAreaFilled(false);
		checkoutB.setFocusPainted(false);
		this.add(checkoutB);

		JButton back = new JButton("< 이 전");
		back.setBounds(29, 233, 92, 30);
		back.setFont(new Font("굴림", Font.PLAIN, 13));
		back.setBorderPainted(false); // 버튼 선 제거
		back.setContentAreaFilled(false); // 버튼 색 제거
		back.setFocusPainted(false); // 버튼 선택선 제거
		this.add(back);
		back.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				back.setBackground(Color.black);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				back.setBackground(Color.gray);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				ch.setVisibletrue();
				dispose();
				
			}
		});

	}

	private void ComboboxSet() {

		JComboBox comboBox = new JComboBox(); // 성인 인원체크
		comboBox.setBounds(462, 138, 35, 28);
		this.add(comboBox);

		JComboBox comboBox_1 = new JComboBox(); // 아동 인원체크
		comboBox_1.setBounds(520, 138, 35, 28);
		this.add(comboBox_1);

	}

}
