package JDBC;

import java.net.Socket;
import java.util.ArrayList;

public class DAOCenter {
	
	public static DAOCenter dc = null;
	private ArrayList<DAOInterface> DAOlist = new ArrayList<>();
	private DAOInterface DAOin = null;
	
	
	
	private DAOCenter(){
		
	}
	
	public static DAOCenter getInstance() {
		if(dc==null) {
			dc = new DAOCenter();
		}
		return dc;
	}
	
	public ArrayList<Object> RoomList(String peo) {
		ArrayList<Object> rList = new ArrayList<>();
		RDAO dao = new RDAO();
		rList = dao.RoomList(peo);
		return rList;
	}
	
	public boolean idchk(String id) {
		MDAO dao = new MDAO();
		if(dao.chkId(id)==true) {//중복이면
			return true;
		}
		return false;
		
		
	}
	public void addinfo(String info) {
		MDAO dao = new MDAO();
		dao.insert(info);
		
		
	}
	public boolean Login(String same) {
		MDAO dao = new MDAO();
		
		if(dao.Login(same)==true) {// 로그인창에쓴 아이디와 비밀번호가 데이터베이스에 내용이저장되어있으면
			return true;
		}
		return false;
	}
	

}
