package Server;

import java.net.Socket;
import java.util.ArrayList;

import JDBC.DAOCenter;
import JDBC.MDTO;
import JDBC.RDTO;

public class SCenter {

	private ArrayList<Sconnect> sList = new ArrayList<>();
	private ArrayList<MDTO> mList = new ArrayList<>();
	private ArrayList<RDTO> rList = new ArrayList<>();
	private DAOCenter dC = DAOCenter.getInstance();

	SCenter() {

	}

	public void addServer(Sconnect s) {
		sList.add(s);
	}

	// 내용에따라 DAO센터에서 내용 확인후 어레이리스트에있는 서버중 메세지보내준 서버와 같은곳으로 보내주기위한 매개변수 withClient가
	// 있다
	public void receive(String msg, Socket withClient, Socket withClient2) {
		int a = msg.indexOf(":");
		String compare = msg.substring(0, a + 1);
		System.out.println(compare);
		// System.out.println(msg.substring(6));
		if (compare.equals("idchk:")) { // 회원가입창에서 중복체크할떄
			String id = msg.substring(a+1);// 아이디만 자른변수
			idchk(id, withClient);

		} else if (compare.equals("info:")) { // 회원가입창에서 가입후 데이터베이스에 넣을때
			String info = msg.substring(a+1);
			dC.addinfo(info);

		} else if (compare.equals("same:")) { // 로그인 할시 데이터베이스 내용과 비교
			String same = msg.substring(5);
			// System.out.println(same);
			Login(same, withClient);
		}
		else if (compare.equals("RoomList:")) { // 체크인아웃 및 인원선택후 인원에맞는방 배열을 클라이언트로전달
			String people = msg.substring(9);
			RoomList(withClient2, people);

		} else if (compare.equals("bookingList:")) {// 날짜비교를위해 예약리스트 배열 전송
			BookingList(withClient2);

		} else if (compare.equals("mInfo:")) {
			String id = msg.substring(a+1);
			System.out.println("id:"+id);
			getInfo(id, withClient2);
		}else if (compare.equals("booadd:")) {
			String binfo = msg.substring(a+1);
			bookingadd(binfo);
		}

	}

//		ArrayList<RDTO> roomList = new ArrayList<>(); //Object 형변환 방법
//	for (int i=0;i<rList.size();i++) {
//		roomList.add((RDTO)rList.get(i));
//	}
//	RDTO dd = null;
//	for(Object o:rList) {
//		dd = (RDTO)o;
//		roomList.add(dd);
//		}

	private void bookingadd(String binfo) {
		dC.bookingadd(binfo);
		
	}

	private void getInfo(String id, Socket withClient2) {
		ArrayList<Object> Info = new ArrayList<>();
		Info = dC.getInfo(id);
		System.out.println("서버센터에서: "+Info.size());
		for (Sconnect s : sList) {
			s.SerializableSend(Info, withClient2);
		}

	}

	private void BookingList(Socket withClient2) {
		ArrayList<Object> bList = new ArrayList<>();
		bList = dC.BookingList();
		for (Sconnect s : sList) {
			s.SerializableSend(bList, withClient2);
		}

	}

	private void RoomList(Socket withClient2, String peo) {
		ArrayList<Object> rList = new ArrayList<>();
		rList = dC.RoomList(peo);
		for (Sconnect s : sList) {
			s.SerializableSend(rList, withClient2);
		}

	}

	private void Login(String same, Socket withClient) {
		String msg;
		if (dC.Login(same)) {// 아이디랑 비밀번호가 둘다 같으면
			msg = "!no"; // 로그인가능한
//			System.out.println(msg);
		} else {
			msg = "!yes"; // 아이디나 비번중에 하나는 틀린
//			System.out.println(msg);
		}

		for (Sconnect s : sList) {
			s.Send(msg, withClient);
		}
	}

	private void idchk(String id, Socket withClient) {
		String msg;
		if (dC.idchk(id)) {// 중복이면
			msg = "@no";
		} else {
			msg = "@yes";
		}
		// System.out.println("Scenter idchk: "+msg);

		for (Sconnect s : sList) {
			s.Send(msg, withClient);
		}

	}

}
