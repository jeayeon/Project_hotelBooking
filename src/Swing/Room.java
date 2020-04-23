package Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Client.Cconnect;
import JDBC.MDTO;
import JDBC.RDTO;

public class Room extends JFrame {
	private Reservation rv = null; // 날짜 비교할때 사용할 클래스
	private Cconnect cc = null;
	private ArrayList<RDTO> rList = null;
	private ArrayList<MDTO> mInfo = null;
	private JTable table;
	private String id = "";
	private int row;
	private Hmain Hm = null;
	private JButton reser, back;
	private JLabel imageLabel;
	private JPanel TableP;
	private ImageIcon room1, room2, room3;
	private String[] hearder = { "방이름", "정원", "가격" };
//	private String[][] aa = { { "1번방", "2", "50000" }, { "2번방", "2", "50000" }, { "3번방", "2", "50000" },
//			{ "4번방", "2", "50000" }, { "5번방", "2", "50000" }, { "6번방", "2", "50000" }};
	private DefaultTableModel dt = new DefaultTableModel(null, hearder);
	// private String[][] aa = new String[10][6]; //값을 미리넣고싶을땐 DefaultTableModel의
	// null 대신 내용을집어넣고 이데이터를넣으면됨 null 로잡혀있을떄 나중에넣고싶을경우는 dt.addrow() 메소드사용

	Room(Reservation rv, Cconnect cc, ArrayList<RDTO> rList) {
		Hm = Hm.getInstance(cc);
		this.id = Hm.getId();
		this.rv = rv;
		this.cc = cc;
		this.rList = rList; //예약기 가능한 방들만있는 방정보

		this.setLayout(null);
		this.setBounds(600, 200, 600, 400);

		roomset();
		tableset();
		Labelset();
		Buttonset();
		imageset();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	private void roomset() {
		for (RDTO r : rList) {
			String[] room = new String[3];
			room[0] = r.getRoom();
			room[1] = r.getPeople() + "";
			room[2] = r.getPrice() + "";
			dt.addRow(room);
		}

	}

	private void imageset() {
		room1 = new ImageIcon("D:\\room1.jpg");
		Image hm1 = room1.getImage(); // 이미지아이콘을 이미지로 변경
		hm1 = hm1.getScaledInstance(260, 260, Image.SCALE_SMOOTH); // 이미지 사이즈 조절
		room1 = new ImageIcon(hm1); // 다시 이미지아이콘으로 변경

		room2 = new ImageIcon("D:\\room2.jpg");
		Image hm2 = room2.getImage(); // 이미지아이콘을 이미지로 변경
		hm2 = hm2.getScaledInstance(260, 260, Image.SCALE_SMOOTH); // 이미지 사이즈 조절
		room2 = new ImageIcon(hm2); // 다시 이미지아이콘으로 변경

		room3 = new ImageIcon("D:\\room3.jpg");
		Image hm3 = room3.getImage(); // 이미지아이콘을 이미지로 변경
		hm3 = hm3.getScaledInstance(260, 260, Image.SCALE_SMOOTH); // 이미지 사이즈 조절
		room3 = new ImageIcon(hm3); // 다시 이미지아이콘으로 변경

	}

	private void Buttonset() {
		back = new JButton("<이전");
		back.setBounds(12, 317, 97, 23);
		back.setFont(new Font("굴림", Font.PLAIN, 13));
		back.setBorderPainted(false); // 버튼 선 제거
		back.setContentAreaFilled(false); // 버튼 색 제거
		back.setFocusPainted(false); // 버튼 선택선 제거
		this.add(back);
		back.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {

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
				dispose();
			}
		});

		reser = new JButton("예약하기");
		reser.setBounds(397, 317, 97, 23);
		this.add(reser);
		reser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				getInfo();
				new Mbooking(rv,cc,rList,mInfo,row);//여기에 넣어주어야할정보는 rList,리저베이션에서오는 예약정보
				dispose();
			}

		});
	}

	private void getInfo() {
		cc.send("mInfo:" + id);
		try {
			Thread.sleep(150);
			mInfo = (ArrayList<MDTO>) cc.getrList();
			System.out.println("제발..."+mInfo.size());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	private void Labelset() {
		imageLabel = new JLabel("<html>광고주를모십니다<br> 사용법: 방을클릭하면 현재 글이써져있는곳에<br> 사진이출력되고 예약하기버튼을누르시면됩니다</html>");
		imageLabel.setBounds(311, 10, 261, 267);
		this.add(imageLabel);

	}

	private void tableset() {
		TableP = new JPanel();// 테이블을 담고있는 패널
		table = new JTable(dt);
		JScrollPane jp = new JScrollPane(table);// 테이블 스크롤 추가
		table.setRowHeight(35); // 테이블 행 조절
		jp.setPreferredSize(new Dimension(300, 300));// 테이블 크기 조절
		TableP.setBounds(0, 0, 300, 310);
		TableP.add(jp);
		this.add(TableP);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					row = table.getSelectedRow();// 선택한 god의 첫번쨰 인덱스값
//					int column = table.getSelectedColumn();// 선택한 열의 첫번쨰 인덱스값
//					int count = table.getRowCount();// 테이블행의 개수

					//방이름별 사진을넣기위한 소스
					if (dt.getValueAt(row, 0).equals("방1") || dt.getValueAt(row, 0).equals("방2")) {
						imageLabel.setIcon(room1);
					} else if (dt.getValueAt(row, 0).equals("방3") || dt.getValueAt(row, 0).equals("방4")) {
						imageLabel.setIcon(room2);
					} else if (dt.getValueAt(row, 0).equals("방5") || dt.getValueAt(row, 0).equals("방6")) {
						imageLabel.setIcon(room3);
					}

				}
				if (e.getClickCount() == 2) {// 얜 왜안될까?

				}
				if (e.getButton() == 3) {
					System.out.println("마우스 오른쪽클릭");
				}

			}

		});
	}

//	public static void main(String[] args) {
//		new Room();
//	}

}
