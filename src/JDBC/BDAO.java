package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BDAO implements DAOInterface {

	private Connect c = Connect.getInstance();
	private Connection conn = c.connect();
	private Statement stmt = null;
	private ResultSet rs = null;

	@Override
	public void insert(String info) {

	}

	public ArrayList<Object> BookingList() {
		ArrayList<Object> bList = new ArrayList<>();
		try {
			c.orclelode();
			String sql = "select * from booking";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BDTO d = new BDTO();
				d.setRenum(rs.getString("renum"));
				d.setId(rs.getString("id"));
				d.setName(rs.getString("name"));
				d.setRoom(rs.getString("room"));
				d.setPeople(rs.getInt("people"));
				d.setCheckin(rs.getString("checkin"));
				d.setCheckout(rs.getString("checkout"));
				d.setNight(rs.getInt("night"));
				d.setPrice(rs.getInt("price"));
				bList.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bList;

	}

	public void bookingadd(String binfo) {
		StringTokenizer st = new StringTokenizer(binfo);
		int count = st.countTokens();
		try {
			c.orclelode();
			String sql = "insert into booking values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(sql);

			if(count==9) {
				
				psmt.setString(1, st.nextToken());
				psmt.setString(2, st.nextToken());
				psmt.setString(3, st.nextToken());
				psmt.setString(4, st.nextToken());
				psmt.setInt(5, Integer.parseInt(st.nextToken()));
				psmt.setString(6, st.nextToken());
				psmt.setString(7, st.nextToken());
				psmt.setInt(8, Integer.parseInt(st.nextToken()));
				psmt.setInt(9, Integer.parseInt(st.nextToken()));
				psmt.executeUpdate();
			}else {
				psmt.setString(1, st.nextToken());
				psmt.setString(2, null);
				psmt.setString(3, st.nextToken());
				psmt.setString(4, st.nextToken());
				psmt.setInt(5, Integer.parseInt(st.nextToken()));
				psmt.setString(6, st.nextToken());
				psmt.setString(7, st.nextToken());
				psmt.setInt(8, Integer.parseInt(st.nextToken()));
				psmt.setInt(9, Integer.parseInt(st.nextToken()));
				psmt.executeUpdate();
			}
			System.out.println("예약내용 디비저장완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
