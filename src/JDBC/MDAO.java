package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class MDAO implements DAOInterface {

	private Connect c = Connect.getInstance();
	private Connection conn = c.connect();
	private Statement stmt = null;
	private ResultSet rs = null;

	@Override
	public void insert(String info) {
		StringTokenizer st = new StringTokenizer(info);
		String[] infoList = new String[7];
		for (int i = 0; i < infoList.length; i++) {
			infoList[i] = st.nextToken();
		}

		c.orclelode();
		try {
			String sql = "insert into member values(?,?,?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(sql);
			for (int i = 0; i < infoList.length; i++) {
				if (i <= 4) {
					psmt.setString(i + 1, infoList[i]);
				} else {
					psmt.setInt(i + 1, Integer.parseInt(infoList[i]));
				}
			}
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public boolean chkId(String id) {
		boolean idchk = false;
		try {
			c.orclelode();
			String sql = "select id from member";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String Id = rs.getString("id");
				if (id.equals(Id)) {
					// 중복이있는상태
					idchk = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idchk;
	}

	public boolean Login(String same) {
		boolean b = false;
		StringTokenizer st = new StringTokenizer(same);
		String Logid = st.nextToken();
		String Logpwd = st.nextToken();
//		System.out.println(Logid);
//		System.out.println(Logpwd);
		c.orclelode();
		try {
			String sql = "select id,pwd from member";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String dbid = rs.getString("id");
				String dbpwd = rs.getString("pwd");
//				System.out.println(dbid);
//				System.out.println(dbpwd);
				if (Logid.equals(dbid) && Logpwd.equals(dbpwd)) {// db에 있는 아이디비밀번호와 로그인한 아이디비밀번호가 같을때
					b = true;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;

	}

}
