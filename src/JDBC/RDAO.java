package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

public class RDAO implements DAOInterface {
	private Connect c = Connect.getInstance();
	private Connection conn = c.connect();
	private Statement stmt = null;
	private ResultSet rs = null;


	@Override
	public void insert(String info) {

	}

	public ArrayList<Object> RoomList(String peo) {
			c.orclelode();
			System.out.println("검색누르면 여기까진 온다");
			System.out.println("인원수:"+peo);
			ArrayList<Object> rList = new ArrayList<>();
		try {
			String sql = "select * from room where people>=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, peo);
			psmt.executeUpdate();
			rs = psmt.executeQuery(sql);
			while(rs.next()) {
				RDTO d =new RDTO();
				d.setRoom(rs.getString("room"));
				d.setPeople(rs.getInt("people"));
				d.setPrice(rs.getInt("price"));
				rList.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rList;
	}

}
