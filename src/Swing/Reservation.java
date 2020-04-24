package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import Client.Cconnect;

public class Reservation extends JFrame {

	private TitledBorder tborder;
	private JLabel fewday, dayin, dayout;
	private Choice ch = null;
	private String[] peo = new String[5];
	private Cconnect cc = null;
	private String daychk = "";
	private JComboBox AcomboBox, CcomboBox;
	private JButton search;
	private ArrayList<String> bInfo = new ArrayList<>();

	private Calendar chkin = new GregorianCalendar();
	private Calendar chkout = new GregorianCalendar();

	Reservation(Choice choice, Cconnect cc) {
		super("객실 예약");
		ch = choice;
		this.cc = cc;
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

	public void daychk(String a, int year, int mon, int d) {// a = 선택한 전체날짜 , int형은 전체날짜 분할상테
//		System.out.println(daychk);
		if (daychk.equals("in")) {// chkin,out라벨에 날짜를 집어넣기위한 조건문
			dayin.setText(a);
			chkin.set(year, mon, d);
			System.out.println("chkin: "+ chkin.getTime());
		} else {
			dayout.setText(a);
			chkout.set(year, mon, d);
			System.out.println("chout: "+ chkout.getTime());
		}
		System.out.println("chkin:두번쨰 "+ chkin.getTime());
		System.out.println("chout:두번째 "+ chkout.getTime());
		int day2 = chkin.compareTo(chkout); // 리턴값이 -1이면 체크아웃 숫자가 더큰것
		System.out.println("리턴값: "+ day2);
		if (!dayin.getText().equals("") && !dayout.getText().equals("")) {
			if (day2 < 0) { // -1일때만 체크인 날짜보다 체크아웃날짜가 뒤에있는거다 같으면 대실인데 대실은빼자.
				// 시 *분*초*밀리언단위라 1000을곱해서 일로만듦
				long bak = (chkin.getTimeInMillis() - chkout.getTimeInMillis()) / (24 * 60 * 60 * 1000);
				bak = Math.abs(bak);
				String bak2 = bak + "";
				fewday.setText(bak2);
			} else {
				JOptionPane.showMessageDialog(null, "날짜 입력을 잘못했습니다");
				dayin.setText("");
				dayout.setText("");
				fewday.setText("");
			}

		}

	}

	private void newCalendar() {
		new CalendarData(this);
	}

	private void ButtonSet() {
		ImageIcon icon = new ImageIcon("D:\\java_src\\Project_hotelBooking\\calendar1.jpg");
		Image ImageSet = icon.getImage();
		ImageSet = ImageSet.getScaledInstance(35, 30, Image.SCALE_SMOOTH);
		icon = new ImageIcon(ImageSet);

		ImageIcon icon2 = new ImageIcon("D:\\java_src\\Project_hotelBooking\\calendar2.jpg");
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
				daychk = "in";
//				System.out.println(daychk);
				newCalendar();

			}
		});

		JButton checkoutB = new JButton(icon); // 체크아웃 달력버튼
		checkoutB.setBounds(305, 97, 35, 30);
		checkoutB.setRolloverIcon(icon2); // 버튼에 마우스 올렸을때 출력되는 이미지아이콘
		checkoutB.setBorderPainted(false);
		checkoutB.setContentAreaFilled(false);
		checkoutB.setFocusPainted(false);
		this.add(checkoutB);
		checkoutB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				daychk = "out";
//				System.out.println(daychk);
				newCalendar();
			}
		});

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

		search = new JButton("검 색");
		search.setFont(new Font("굴림", Font.PLAIN, 16));
		search.setBounds(250, 190, 138, 42);
		this.add(search);
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				cc.send("RoomList:");
//				try {
//					Thread.sleep(70);
					if (!dayin.getText().equals("") && !dayout.getText().equals("") && AcomboBox.getSelectedIndex() >= 1
							|| CcomboBox.getSelectedIndex() >= 1) {
						int adult =AcomboBox.getSelectedIndex();
						int child = CcomboBox.getSelectedIndex();
						bInfoadd(adult,child);
						Comparedate(adult,child);
						
					}

//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}

		});

	}
	private void bInfoadd(int adult, int child) {
		bInfo.add(dayin.getText()); 
		bInfo.add(dayout.getText());
		bInfo.add(fewday.getText());
		bInfo.add(adult+child+"");
		
	}
	
	private void Comparedate(int adult, int child) {
		String uchkin = dayin.getText();
		String uchkout = dayout.getText();
		new CompareDate(chkin,chkout,cc,this,adult,child,uchkin,uchkout);		
	}
	
	public ArrayList<String> getbInfo() {
		return bInfo;
	}

	private void ComboboxSet() {
		for (int i = 0; i < peo.length; i++) {
			peo[i] = i + "";
		}
		AcomboBox = new JComboBox(peo); // 성인 인원체크용
		AcomboBox.setBounds(462, 138, 35, 28);
		this.add(AcomboBox);

		CcomboBox = new JComboBox(peo); // 아동 인원체크용
		CcomboBox.setBounds(520, 138, 35, 28);
		this.add(CcomboBox);
		
		AcomboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int adult =AcomboBox.getSelectedIndex();
				int child = CcomboBox.getSelectedIndex();
				if(adult+child>4) {
					search.setEnabled(false);
				}else {
					search.setEnabled(true);
				}
				
			}
		});
		CcomboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int adult =AcomboBox.getSelectedIndex();
				int child = CcomboBox.getSelectedIndex();
				if(adult+child>4) {
					search.setEnabled(false);
				}else {
					search.setEnabled(true);
				}
			}
		});

	}

}
