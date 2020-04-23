package Swing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Client.Cconnect;
import JDBC.BDTO;
import JDBC.RDTO;

public class CompareDate {
	private Calendar chkin;
	private Calendar chkout;
	private final String uchkin; 
	private final String uchkout; 
	private Calendar Bchkin = Calendar.getInstance();
	private Calendar Bchkout = Calendar.getInstance();
	private Cconnect cc = null;
	private Reservation rv = null;
	private int child = 0; // 예약인원 성인
	private int adult = 0; // 예약인원 아동
	private ArrayList<RDTO> rList = null; // 인원에 맞춘 방정보
	private ArrayList<BDTO> bList = null;
	

	CompareDate(Calendar chkin, Calendar chkout, Cconnect cc, Reservation rv, int a, int c, String uchkin, String uchkout) {
		this.chkin = chkin;
		this.chkout = chkout;
		this.uchkin = uchkin;
		this.uchkout = uchkout;
		this.cc = cc;
		this.rv = rv;
		this.child = c;
		this.adult = a;

		init();// rList bList 객체 받아오는 메소드
		CompareDay();
		roomchoice();// 리저베이션에서 검색버튼 누른후 인원수에 맞게나온 기본방정보를 Room클래스로 보내주게
	}
	// 이거 지워지기전까지만 롤백하자
	private void CompareDay() {
		try {
			int n = 1;
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			Date uChkin = date.parse(uchkin);// 예약자 체크인날짜를 세팅해주기위한 Date
			Date uChkout = date.parse(uchkout);// 예약자 체크인날짜를 세팅해주기위한 Date
			System.out.println("두번돌렸을떄 체크인날짜비교 :" + uchkin);
			System.out.println(chkout.getTime());
			
			for (int i = 0; i < bList.size(); i++) {
				Date din = date.parse(bList.get(i).getCheckin()); // 예약자현황 체크인아웃 날짜 date형변한
				Date dout = date.parse(bList.get(i).getCheckout());
				Bchkin.setTime(din); // 체크인아웃 현황 날짜비교를위한 calendar 변환
				Bchkout.setTime(dout);

				while (Bchkin.compareTo(Bchkout) < 0) {
					String Bin = date.format(Bchkin.getTime()); // 년월일 비교를위해 스트링으로
					System.out.println("Bin :" + Bin);
					chkin.setTime(uChkin);
					chkout.setTime(uChkout);
					System.out.println(chkin.getTime());
//					System.out.println("체크아웃 날짜 : "+ chkout.getTime());
					while (chkin.compareTo(chkout) < 0) {
						String Cin = date.format(chkin.getTime());
//						System.out.println("Cin :" + Cin);
						if (Cin.equals(Bin)) {
							for (int g = 0; g < rList.size(); g++) {
								if (bList.get(i).getRoom().equals(rList.get(g).getRoom())) {
//									System.out.println("rList지워진인덱스번호: " + g);
									rList.remove(g);
								}
							}
//							System.out.println("겹치는방이 몇번방이냐: " + bList.get(i).getRoom());
						}
//						System.out.println("본인 날짜:" + Cin);
						chkin.add(Calendar.DATE, 1);
					}
//					System.out.println(n + "번 사람 체크인날짜: " + Bin);
					Bchkin.add(Calendar.DATE, 1); //
				}
				n++;
			}
			chkin.setTime(uChkin);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void CompareD() { // 체크인 날짜와 체크아웃날짜 사이값 구하는거 먼저 구해보자
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(chkout.get(Calendar.DATE));
//		while (chkin.compareTo(chkout) <= -1) {
//			String checkin = date.format(chkin.getTime());
//			System.out.println(checkin);
//			chkin.add(Calendar.DATE, 1);
//		}

	}

	private void init() { // roomList와 bookingList 받아오기위한 연결
		cc.send("RoomList:" + (child + adult));
		// 스레드슬립 걸어줘야한다 보내고 멀티스레드가 돌기전에 끝나버리면 어레이리스트에 내용 못넣음
		try {
			Thread.sleep(600);
			rList = (ArrayList<RDTO>) cc.getrList();
			System.out.println(rList.get(0).getRoom());
			cc.send("bookingList:");
			Thread.sleep(150);
			bList = (ArrayList<BDTO>) cc.getrList();

//			for(RDTO r : rList) {
//				System.out.println(r.getRoom());
//			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void roomchoice() {
		new Room(rv, cc, rList);

	}

}
