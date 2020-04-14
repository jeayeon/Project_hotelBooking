package Server;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import JDBC.DAOCenter;
import JDBC.MDTO;
import JDBC.RDTO;

public class SCenter   {

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
	public void receive(String msg, Socket withClient) {

		// System.out.println(msg.substring(6));
		if (msg.substring(0, 6).equals("idchk:")) { // 회원가입창에서 중복체크할떄
			String id = msg.substring(6);// 아이디만 자른변수
			idchk(id, withClient);

		} else if (msg.substring(0, 5).equals("info:")) { // 회원가입창에서 가입후 데이터베이스에 넣을때
			String info = msg.substring(5);
			dC.addinfo(info);

		} else if (msg.substring(0, 5).equals("same:")) { // 로그인 할시 데이터베이스 내용과 비교
			String same = msg.substring(5);
			//System.out.println(same);
			Login(same, withClient);
		}

	}

	private void Login(String same, Socket withClient) {
		String msg;
		if (dC.Login(same)) {// 아이디랑 비밀번호가 둘다 같으면
			msg = "!no"; // 로그인가능한
		} else {
			msg = "!yes"; //아이디나 비번중에 하나는 틀린
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
		System.out.println("Scenter idchk: "+msg);

		for (Sconnect s : sList) {
			s.Send(msg, withClient);
		}

	}

}
