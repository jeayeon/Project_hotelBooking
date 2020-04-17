package Swing;

import java.util.ArrayList;
import java.util.Calendar;

import Client.Cconnect;
import JDBC.RDTO;

public class CompareDate {
	private Calendar chkin;
	private Calendar chkout;
	private Cconnect cc =null;
	private Reservation rv = null;
	private int child=0; //예약인원 성인
	private int adult=0; //예약인원 성인
	private ArrayList<RDTO> rList =null; //인원에 맞춘 방정보

	CompareDate(Calendar chkin, Calendar chkout, Cconnect cc, Reservation rv, int a, int c) {
		this.chkin = chkin;
		this.chkout = chkout;
		this.cc = cc;
		this.rv = rv;
		this.child=c;
		this.adult=a;
		
		roomchoice();// 리저베이션에서 검색버튼 누른후 인원수에 맞게 기본방정보에서 예약가능한방만 뜰수있도록
	}

	private void roomchoice() {
		cc.send("RoomList:"+(child+adult));
		//스레드슬립 걸어줘야한다 보내고 멀티스레드가 돌기전에 끝나버리면 어레이리스트에 내용 못넣음
		try {
			Thread.sleep(130);
			rList = cc.getrList();
			for(RDTO r : rList) {
				System.out.println(r.getRoom());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
